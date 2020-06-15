package org.scalax.binding.slick

import java.sql.Statement

import slick.dbio.{DBIOAction, Effect, NoStream}
import slick.jdbc.{ResultSetConcurrency, ResultSetHoldability, ResultSetType, TransactionIsolation}

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
