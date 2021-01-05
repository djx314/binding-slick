package org.scalax.binding.slick

import slick.dbio.{DBIOAction, Effect, NoStream}
import slick.jdbc.{ActionBasedSQLInterpolation, JdbcProfile, ResultSetConcurrency, ResultSetHoldability, ResultSetType, TransactionIsolation}
import slick.lifted.{Query, RunnableCompiled}
import slick.relational.RelationalProfile
import slick.sql.FixedSqlAction

import java.sql.Statement

object BindDeleteOrUpdateActionExtensionMethodsHelper {

  trait BindDeleteOrUpdateActionExtensionMethodsImpl extends BindDeleteOrUpdateActionExtensionMethods {

    val profile: JdbcProfile

    override implicit def queryDeleteActionExtensionMethods[C[_]](
      q: Query[_ <: RelationalProfile#Table[_], _, C]
    ): BindDeleteOrUpdateActionExtensionMethods.DeleteActionExtensionMethods = new BindDeleteOrUpdateActionExtensionMethods.DeleteActionExtensionMethods {
      override def delete = profile.api.queryDeleteActionExtensionMethods(q).delete
    }

    override implicit def runnableCompiledDeleteActionExtensionMethods[RU, C[_]](
      c: RunnableCompiled[_ <: Query[_, _, C], C[RU]]
    ): BindDeleteOrUpdateActionExtensionMethods.DeleteActionExtensionMethods = new BindDeleteOrUpdateActionExtensionMethods.DeleteActionExtensionMethods {
      override def delete = profile.api.runnableCompiledDeleteActionExtensionMethods(c).delete
    }

    override implicit def runnableCompiledUpdateActionExtensionMethods[RU, C[_]](
      c: RunnableCompiled[_ <: Query[_, _, C], C[RU]]
    ): BindDeleteOrUpdateActionExtensionMethods.UpdateActionExtensionMethods[RU] = {
      val updateS = profile.api.runnableCompiledUpdateActionExtensionMethods(c)
      new BindDeleteOrUpdateActionExtensionMethods.UpdateActionExtensionMethods[RU] {
        override def update(a: RU): FixedSqlAction[Int, NoStream, Effect.Write] = updateS.update(a)
        override def updateStatement: String                                    = updateS.updateStatement
      }
    }

    override implicit def queryUpdateActionExtensionMethods[U, C[_]](c: Query[_, U, C]): BindDeleteOrUpdateActionExtensionMethods.UpdateActionExtensionMethods[U] = {
      val i = profile.api.queryUpdateActionExtensionMethods(c)
      new BindDeleteOrUpdateActionExtensionMethods.UpdateActionExtensionMethods[U] {
        override def update(a: U): FixedSqlAction[Int, NoStream, Effect.Write] = i.update(a)
        override def updateStatement: String                                   = i.updateStatement
      }
    }

    override def jdbcActionExtensionMethods[E <: Effect, R, S <: NoStream](
      a: DBIOAction[R, S, E]
    ): BindDeleteOrUpdateActionExtensionMethods.BindJdbcActionContent[E, R, S] = {
      val dbioS = profile.api.jdbcActionExtensionMethods(a)
      new BindDeleteOrUpdateActionExtensionMethods.BindJdbcActionContent[E, R, S] {
        override def transactionally: DBIOAction[R, S, E with Effect.Transactional]          = dbioS.transactionally
        override def withTransactionIsolation(ti: TransactionIsolation): DBIOAction[R, S, E] = dbioS.withTransactionIsolation(ti)
        override def withStatementParameters(
          rsType: ResultSetType,
          rsConcurrency: ResultSetConcurrency,
          rsHoldability: ResultSetHoldability,
          statementInit: Statement => Unit,
          fetchSize: Int
        ): DBIOAction[R, S, E] = {
          dbioS.withStatementParameters(
            rsType = rsType,
            rsConcurrency = rsConcurrency,
            rsHoldability = rsHoldability,
            statementInit = statementInit,
            fetchSize = fetchSize
          )
        }
      }
    }

    override implicit def actionBasedSQLInterpolation(s: StringContext): ActionBasedSQLInterpolation = new ActionBasedSQLInterpolation(s)

  }

}
