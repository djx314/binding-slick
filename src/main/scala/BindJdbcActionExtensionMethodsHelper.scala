package org.scalax.binding.slick

import java.sql.Statement

import slick.jdbc.{JdbcProfile, ResultSetConcurrency, ResultSetHoldability, ResultSetType, TransactionIsolation}

object BindJdbcActionExtensionMethodsHelper {

  trait BindJdbcActionExtensionMethodsImpl extends BindJdbcActionExtensionMethods {
    val profile: JdbcProfile
    import profile.api._
    override def bindJdbcActionExtensionMethods[E <: Effect, R, S <: NoStream](a: DBIOAction[R, S, E]): BindJdbcActionContent[E, R, S] =
      new BindJdbcActionContent[E, R, S] {
        override def transactionally: DBIOAction[R, S, E with Effect.Transactional]          = a.transactionally
        override def withTransactionIsolation(ti: TransactionIsolation): DBIOAction[R, S, E] = a.withTransactionIsolation(ti)
        override def withStatementParameters(
          rsType: ResultSetType,
          rsConcurrency: ResultSetConcurrency,
          rsHoldability: ResultSetHoldability,
          statementInit: Statement => Unit,
          fetchSize: Int
        ): DBIOAction[R, S, E] = {
          a.withStatementParameters(rsType = rsType, rsConcurrency = rsConcurrency, rsHoldability = rsHoldability, statementInit = statementInit, fetchSize = fetchSize)
        }
      }
  }

}
