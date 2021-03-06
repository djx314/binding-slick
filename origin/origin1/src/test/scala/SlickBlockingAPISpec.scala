package com.github.takezoe.slick.blocking

import org.scalatest.{Assertion, FunSuite}
import slick.jdbc.meta.MTable
import doobie._
import cats._
import cats.effect._
import doobie.h2._

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration.Duration

class SlickBlockingAPISpec extends FunSuite {

  lazy val transactor: Resource[IO, H2Transactor[IO]] = {
    import cats.implicits._

    implicit val cs = IO.contextShift(ExecutionContext.global)

    for {
      ce <- ExecutionContexts.fixedThreadPool[IO](32) // our connect EC
      te <- ExecutionContexts.cachedThreadPool[IO]    // our transaction EC
      xa <- H2Transactor.newH2Transactor[IO](
          "jdbc:h2:mem:test;TRACE_LEVEL_FILE=1", // connect URL
        "", // username
        "", // password
        ce, // await connection here
        te // execute JDBC operations here
      )
    } yield xa

  }

  object Tables extends {
    val profile = BlockingH2Driver
  } with models.Tables
  import BlockingH2Driver.blockingApi._
  import Tables._

  private val db = Database.forURL(url = "jdbc:h2:mem:test;TRACE_LEVEL_FILE=1", user = "", password = "", driver = "org.h2.Driver")

  test("CRUD operation") {
    import doobie.implicits._
    import cats.implicits._

    db.withSession { implicit session =>
      //Tables.schema.create

      val insert = Users.doobieInsert

      // Insert

      val rows: Int = transactor.use { xa =>
        (for {
          (_: Int) <- Tables.schema.doobieCreate
          i1       <- insert.run(UsersRow(1, "takezoe", None))
          i2       <- insert.run(UsersRow(2, "chibochibo", None))
          i3       <- insert.run(UsersRow(3, "tanacasino", None))
          count1   <- Query(Users.length).doobieQuery.unique
          result1  <- Users.sortBy(_.id).doobieQuery.to[List]
        } yield {
          assert(count1 == 3)
          assert(result1.length == 3)
          assert(result1(0) == UsersRow(1, "takezoe", None))
          assert(result1(1) == UsersRow(2, "chibochibo", None))
          assert(result1(2) == UsersRow(3, "tanacasino", None))
          i1 + i2 + i3
        }).transact(xa)
      }.unsafeRunSync

      /*val count1 = transactor.use { xa =>
        Query(Users.length).doobieQuery.unique.transact(xa)
      }.unsafeRunSync
      assert(count1 == 3)

      val result1 = //Users.sortBy(_.id).list
        transactor.use { xa =>
          Users.sortBy(_.id).doobieQuery.to[List].transact(xa)
        }.unsafeRunSync
      assert(result1.length == 3)
      assert(result1(0) == UsersRow(1, "takezoe", None))
      assert(result1(1) == UsersRow(2, "chibochibo", None))
      assert(result1(2) == UsersRow(3, "tanacasino", None))*/

      // Update
      Users.filter(_.id === 1L.bind).map(_.name).update("naoki")

      val result2 = //Users.filter(_.id === 1L.bind).first
        transactor.use { xa =>
          Users.filter(_.id === 1L.bind).doobieQuery.unique.transact(xa)
        }.unsafeRunSync
      assert(result2 == UsersRow(1, "naoki", None))

      // Delete
      Users.filter(_.id === 1L.bind).delete

      val result3 = //Users.filter(_.id === 1L.bind).firstOption
        transactor.use { xa =>
          Users.filter(_.id === 1L.bind).doobieQuery.option.transact(xa)
        }.unsafeRunSync
      assert(result3.isEmpty)

      val count2 = //Query(Users.length).first
        transactor.use { xa =>
          Query(Users.length).doobieQuery.unique.transact(xa)
        }.unsafeRunSync
      assert(count2 == 2)

      //Tables.schema.remove

      transactor.use { xa =>
        Tables.schema.doobieDrop.transact(xa)
      }.unsafeRunSync
    }
  }

  test("Plain SQL") {
    db.withSession { implicit session => //Tables.schema.create

    {
      import doobie.implicits._
      import cats.implicits._

      transactor.use { xa =>
        Tables.schema.doobieCreate.transact(xa)
      }.unsafeRunSync
    }

    // plain sql
    val id1     = 1
    val name1   = "takezoe"
    val insert1 = sqlu"INSERT INTO USERS (ID, NAME) VALUES (${id1}, ${name1})"
    insert1.execute

    val query  = sql"SELECT COUNT(*) FROM USERS".as[Int]
    val count1 = query.first
    assert(count1 == 1)

    val id2     = 2
    val name2   = "chibochibo"
    val insert2 = sqlu"INSERT INTO USERS (ID, NAME) VALUES (${id2}, ${name2})"
    insert2.execute

    val count2 = query.first
    assert(count2 == 2)

    //Tables.schema.remove

    import doobie.implicits._
    import cats.implicits._

    transactor.use { xa =>
      Tables.schema.doobieDrop.transact(xa)
    }.unsafeRunSync
    }
  }

  test("exists") {
    import doobie.implicits._
    import cats.implicits._

    //db.withSession { implicit session =>
    //Tables.schema.create

    /*transactor.use { xa =>
      Tables.schema.doobieCreate.transact(xa)
    }.unsafeRunSync*/

    val insert = Users.doobieInsert

    transactor.use { xa =>
      val action1 = Tables.schema.doobieCreate.transact(xa)
      val assertion2: IO[Assertion] = Users.filter(_.id === 1L.bind).filter(_.name === "takezoe".bind).exists.doobieQuery.unique.transact(xa) >>= {
        (exists1: Boolean) =>
          IO.pure(assert(exists1 == false))
      }

      val action3 = insert.run(UsersRow(1, "takezoe", None)).transact(xa)
      val assertion4: IO[Assertion] = Users.filter(_.id === 1L.bind).filter(_.name === "takezoe".bind).exists.doobieQuery.unique.transact(xa) >>= { exists2 =>
        IO.pure(assert(exists2 == true))
      }
      val action6 = Tables.schema.doobieDrop.transact(xa)

      action1 >> assertion2 >> action3 >> assertion4 >> action6
    }.unsafeRunSync

    //Users.insert(UsersRow(1, "takezoe", None))

    // Insert

    /*val rows: Int = transactor.use { xa =>
        (for {
          i1 <- insert.run(UsersRow(1, "takezoe", None))
        } yield {
          i1
        }).transact(xa)
      }.unsafeRunSync

      val exists2 = transactor.use { xa =>
        Users.filter(_.id === 1L.bind).filter(_.name === "takezoe".bind).exists.doobieQuery.unique.transact(xa)
      }.unsafeRunSync
      assert(exists2 == true)*/

    //Tables.schema.remove

    /*transactor.use { xa =>
        Tables.schema.doobieDrop.transact(xa)
      }.unsafeRunSync*/
    //}
  }

  test("sum") {
    db.withSession { implicit session =>
      import doobie.implicits._

      //Tables.schema.create

      transactor.use { xa =>
        Tables.schema.doobieCreate.transact(xa)
      }.unsafeRunSync

      val sum = transactor.use { xa =>
        Users.map(_.id).sum.doobieQuery.unique.transact(xa)
      }.unsafeRunSync
      assert(sum == None)

      //Tables.schema.remove

      transactor.use { xa =>
        Tables.schema.doobieDrop.transact(xa)
      }.unsafeRunSync
    }
  }

  test("run") {
    db.withSession { implicit session =>
      //Tables.schema.create
      import doobie.implicits._

      transactor.use { xa =>
        Tables.schema.doobieCreate.transact(xa)
      }.unsafeRunSync
      assert(transactor.use { xa =>
        Users.doobieQuery.to[List].map(_.length).transact(xa)
      }.unsafeRunSync == 0)
    }
  }

  test("insertAll") {
    import doobie.implicits._
    import cats.implicits._

    db.withSession { implicit session =>
      //Tables.schema.create

      transactor.use { xa =>
        Tables.schema.doobieCreate.transact(xa)
      }.unsafeRunSync

      val users = List(
          UsersRow(1, "takezoe", None)
        , UsersRow(2, "chibochibo", None)
        , UsersRow(3, "tanacasino", None)
      )

      val count1 = //Query(Users.length).first
        transactor.use { xa =>
          for {
            (_: Int) <- Users.doobieInsert.updateMany(users).transact(xa)
            num      <- Query(Users.length).doobieQuery.unique.transact(xa)
          } yield num
        }.unsafeRunSync
      assert(count1 == 3)

      //Users ++= users
      val count2 = //Query(Users.length).first
        transactor.use { xa =>
          for {
            (_: Int) <- Users.doobieInsert.updateMany(users).transact(xa)
            num      <- Query(Users.length).doobieQuery.unique.transact(xa)
          } yield num
        }.unsafeRunSync
      assert(count2 == 6)
    }
  }

  test("insert returning") {
    db.withSession { implicit session =>
      import doobie.implicits._
      //Tables.schema.create

      transactor.use { xa =>
        Tables.schema.doobieCreate.transact(xa)
      }.unsafeRunSync

      val id = Users.returning(Users.map(_.id)) insert UsersRow(1, "takezoe", None)
      assert(id == 1)
      assert(transactor.use { xa =>
        Users.doobieQuery.to[List].map(_.length).transact(xa)
      }.unsafeRunSync == 1)
      val u = (Users.returning(Users.map(_.id)).into((u, id) => u.copy(id = id))) insert UsersRow(2, "takezoe", None)
      assert(u.id == 2)
      assert(transactor.use { xa =>
        Users.doobieQuery.to[List].map(_.length).transact(xa)
      }.unsafeRunSync == 2)
    }

  }

  test("insert multiple returning") {
    db.withSession { implicit session =>
      import doobie.implicits._
      //Tables.schema.create

      transactor.use { xa =>
        Tables.schema.doobieCreate.transact(xa)
      }.unsafeRunSync

      val id = Users.returning(Users.map(_.id)) insertAll (UsersRow(1, "takezoe", None), UsersRow(2, "mrfyda", None))
      assert(id == List(1, 2))
      assert(transactor.use { xa =>
        Users.doobieQuery.to[List].map(_.length).transact(xa)
      }.unsafeRunSync == 2)
      val u = (Users.returning(Users.map(_.id)).into((u, id) => u.copy(id = id))) insertAll (UsersRow(3, "takezoe", None), UsersRow(4, "mrfyda", None))
      assert(u.map(_.id) == List(3, 4))
      assert(transactor.use { xa =>
        Users.doobieQuery.to[List].map(_.length).transact(xa)
      }.unsafeRunSync == 4)
    }
  }

  test("insert insertOrUpdate") {
    db.withSession { implicit session =>
      import doobie.implicits._
      //Tables.schema.create

      transactor.use { xa =>
        Tables.schema.doobieCreate.transact(xa)
      }.unsafeRunSync

      Users.insertOrUpdate(UsersRow(1, "takezoe", None))
      assert(transactor.use { xa =>
        Users.doobieQuery.to[List].map(_.length).transact(xa)
      }.unsafeRunSync == 1)
      Users.insertOrUpdate(UsersRow(1, "joao", None))
      assert(transactor.use { xa =>
        Users.doobieQuery.to[List].map(_.length).transact(xa)
      }.unsafeRunSync == 1)
    }
  }

  test("withTransaction Query") {
    import doobie.implicits._

    val insert = Users.doobieInsert

    //TODO
    /*withTransaction(
        u =>
        s => {
          transactor.use { xa =>
            insert.run(u).transact(xa)
          }.unsafeRunSync
      } //Users.insert(u)(s)
      , id => s => Users.filter(_.id === id.bind).exists.run(s)
    )*/
  }

  test("withTransaction Action") {
    withTransaction(
        u => s => sqlu"insert into users values (${u.id}, ${u.name}, ${u.companyId})".execute(s)
      , id => s => sql"select exists (select * from users where id = $id)".as[Boolean].first(s)
    )
  }

  private def withTransaction(
      insertUser: UsersRow => Session => Int
    , existsUser: Long => Session => Boolean
  ) = {
    db.withSession { implicit session =>
      //Tables.schema.create
      import doobie.implicits._

      transactor.use { xa =>
        Tables.schema.doobieCreate.transact(xa)
      }.unsafeRunSync

      { // rollback
        session.withTransaction {
          insertUser(UsersRow(1, "takezoe", None))(session)
          val exists = existsUser(1)(session)
          assert(exists == true)
          session.conn.rollback()
        }
        val exists = existsUser(1)(session)
        assert(exists == false)
      }

      { // ok
        session.withTransaction {
          insertUser(UsersRow(2, "takezoe", None))(session)
          val exists = existsUser(2)(session)
          assert(exists == true)
        }
        val exists = existsUser(2)(session)
        assert(exists == true)
      }

      { // nest (rollback)
        session.withTransaction {
          insertUser(UsersRow(3, "takezoe", None))(session)
          assert(existsUser(3)(session) == true)
          session.withTransaction {
            insertUser(UsersRow(4, "takezoe", None))(session)
            assert(existsUser(4)(session) == true)
            session.conn.rollback()
          }
        }
        assert(existsUser(3)(session) == false)
        assert(existsUser(4)(session) == false)
      }

      { // nest (ok)
        session.withTransaction {
          insertUser(UsersRow(5, "takezoe", None))(session)
          assert(existsUser(5)(session) == true)
          session.withTransaction {
            insertUser(UsersRow(6, "takezoe", None))(session)
            assert(existsUser(6)(session) == true)
          }
        }
        assert(existsUser(5)(session) == true)
        assert(existsUser(6)(session) == true)
      }
    }
  }

  test("MTable support") {
    db.withSession { implicit session =>
      //Tables.schema.create
      import doobie.implicits._

      transactor.use { xa =>
        Tables.schema.doobieCreate.transact(xa)
      }.unsafeRunSync

      assert(MTable.getTables.list.length == 2)
    }
  }

  test("Transaction support with Query SELECT FOR UPDATE") {
    //TODO
    /*testTransactionWithSelectForUpdate { implicit session =>
      transactor.use { xa =>
        Users.map(_.id).forUpdate.doobieQuery.to[List].transact(xa)
      }.unsafeRunSync
    //Users.map(_.id).forUpdate.list
    }*/
  }

  test("Transaction support with Action SELECT FOR UPDATE") {
    testTransactionWithSelectForUpdate { implicit session =>
      sql"select id from USERS for update".as[Long].list
    }
  }

  private def testTransactionWithSelectForUpdate(selectForUpdate: Session => Seq[Long]) = {
    import scala.concurrent.ExecutionContext.Implicits.global
    db.withSession { implicit session =>
      //Tables.schema.create

      import doobie.implicits._

      transactor.use { xa =>
        Tables.schema.doobieCreate.transact(xa)
      }.unsafeRunSync

      val insert = Users.doobieInsert

      // Insert
      transactor.use { xa =>
        insert.run(UsersRow(1, "takezoe", None)).transact(xa)
      }.unsafeRunSync: Int
      //Users.insert(UsersRow(1, "takezoe", None))

      //concurrently do a select for update
      val f1 = Future {
        db.withTransaction { implicit session =>
          val l = selectForUpdate(session).length
          //default h2 lock timeout is 1000ms
          Thread.sleep(3000L)
          l
        }
      }

      //and try to update a row
      val f2 = Future {
        db.withTransaction { implicit session =>
          Thread.sleep(500L)
          Users.filter(_.id === 1L).map(_.name).update("João")
        }
      }

      assert(Await.result(f1, Duration.Inf) == 1)
      assertThrows[Exception](Await.result(f2, Duration.Inf))
    }
  }

  test("compiled support") {
    import doobie.implicits._

    val insert = Users.doobieInsert

    db.withSession { implicit session =>
      //Tables.schema.create

      transactor.use { xa =>
        Tables.schema.doobieCreate.transact(xa)
      }.unsafeRunSync

      val compiled = Compiled { i: Rep[Long] =>
        Users.filter(_.id === i)
      }
      assert(transactor.use { xa =>
        compiled(1L).doobieQuery.to[List].map(_.length).transact(xa)
      }.unsafeRunSync === 0)

      // Insert
      //val insertCompiled = Users.insertInvoker

      transactor.use { xa =>
        insert.run(UsersRow(1, "takezoe", None)).transact(xa)
      }.unsafeRunSync: Int

      assert(transactor.use { xa =>
        compiled(1L).doobieQuery.to[List].map(_.length).transact(xa)
      }.unsafeRunSync === 1)

      //update
      val compiledUpdate = Compiled { n: Rep[String] =>
        Users.filter(_.name === n).map(_.name)
      }
      compiledUpdate("takezoe").update("João")

      //delete
      compiledUpdate("João").delete

      assert(transactor.use { xa =>
        compiled(1L).doobieQuery.to[List].map(_.length).transact(xa)
      }.unsafeRunSync === 0)
    }
  }

  test("Plain SQL chained together") {
    db.withSession { implicit session => //Tables.schema.create
      implicit val ctx = ExecutionContext.global

      {
        import doobie.implicits._

        transactor.use { xa =>
          Tables.schema.doobieCreate.transact(xa)
        }.unsafeRunSync
      }

      // plain sql
      val id1   = 1
      val id2   = 2
      val name1 = "takezoe"
      val name2 = "chibochibo"
      val insert1 = sqlu"INSERT INTO USERS (ID, NAME) VALUES (${id1}, ${name1})" andThen
        sqlu"INSERT INTO USERS (ID, NAME) VALUES (${id2}, ${name2})"
      insert1.run

      val query = for {
        count <- sql"SELECT COUNT(*) FROM USERS".as[Int].head
        max   <- sql"SELECT MAX(ID) FROM USERS".as[Int].head
      } yield (count, max)
      val (count1, max1) = query.run
      assert(count1 == 2)
      assert(max1 == 2)

      val id3   = 3
      val name3 = "drapp"
      val insert2 = sqlu"INSERT INTO USERS (ID, NAME) VALUES (${id3}, ${name3})" andThen
        sqlu"DELETE FROM USERS WHERE ID=${id1}"
      insert2.run

      val count2 = query.run
      assert(count2 == (2, 3))

      //Tables.schema.remove
      import doobie.implicits._

      transactor.use { xa =>
        Tables.schema.doobieDrop.transact(xa)
      }.unsafeRunSync
    }
  }
}
