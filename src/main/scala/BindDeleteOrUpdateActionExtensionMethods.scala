package org.scalax.binding.slick

import slick.dbio.{DBIOAction, Effect, NoStream}
import slick.jdbc.{ActionBasedSQLInterpolation, ResultSetConcurrency, ResultSetHoldability, ResultSetType, TransactionIsolation}
import slick.lifted.{Query, RunnableCompiled}
import slick.relational.RelationalProfile
import slick.sql.FixedSqlAction

import java.sql.Statement

trait BindDeleteOrUpdateActionExtensionMethods extends BindDeleteOrUpdateActionExtensionMethods.LowPriorityAPI {

  implicit def queryDeleteActionExtensionMethods[C[_]](
    q: slick.lifted.Query[_ <: RelationalProfile#Table[_], _, C]
  ): BindDeleteOrUpdateActionExtensionMethods.DeleteActionExtensionMethods
  implicit def runnableCompiledDeleteActionExtensionMethods[RU, C[_]](
    c: slick.lifted.RunnableCompiled[_ <: Query[_, _, C], C[RU]]
  ): BindDeleteOrUpdateActionExtensionMethods.DeleteActionExtensionMethods

  implicit def runnableCompiledUpdateActionExtensionMethods[RU, C[_]](
    c: RunnableCompiled[_ <: Query[_, _, C], C[RU]]
  ): BindDeleteOrUpdateActionExtensionMethods.UpdateActionExtensionMethods[RU]

  implicit def jdbcActionExtensionMethods[E <: Effect, R, S <: NoStream](
    a: DBIOAction[R, S, E]
  ): BindDeleteOrUpdateActionExtensionMethods.BindJdbcActionContent[E, R, S]

  implicit def actionBasedSQLInterpolation(s: StringContext): ActionBasedSQLInterpolation

}

object BindDeleteOrUpdateActionExtensionMethods {

  trait LowPriorityAPI {
    implicit def queryUpdateActionExtensionMethods[U, C[_]](c: Query[_, U, C]): BindDeleteOrUpdateActionExtensionMethods.UpdateActionExtensionMethods[U]
  }

  trait DeleteActionExtensionMethods {
    def delete: FixedSqlAction[Int, NoStream, Effect.Write]
  }

  trait UpdateActionExtensionMethods[A] {
    def update(a: A): FixedSqlAction[Int, NoStream, Effect.Write]
    def updateStatement: String
  }

  trait BindJdbcActionContent[E <: Effect, R, S <: NoStream] {

    def transactionally: DBIOAction[R, S, E with Effect.Transactional]
    def withTransactionIsolation(ti: TransactionIsolation): DBIOAction[R, S, E]
    def withStatementParameters(
      rsType: ResultSetType = null,
      rsConcurrency: ResultSetConcurrency = null,
      rsHoldability: ResultSetHoldability = null,
      statementInit: Statement => Unit = null,
      fetchSize: Int = 0
    ): DBIOAction[R, S, E]

  }

}
