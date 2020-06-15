package org.scalax.binding.slick

import slick.dbio.{Effect, NoStream}
import slick.lifted.{Query, RunnableCompiled}
import slick.relational.RelationalProfile
import slick.sql.FixedSqlAction

trait BindDeleteOrUpdateActionExtensionMethods {

  def queryDeleteActionExtensionMethodsImpl[C[_]](
    q: slick.lifted.Query[_ <: RelationalProfile#Table[_], _, C]
  ): FixedSqlAction[Int, NoStream, Effect.Write]
  def runnableCompiledDeleteActionExtensionMethodsImpl[RU, C[_]](
    c: slick.lifted.RunnableCompiled[_ <: Query[_, _, C], C[RU]]
  ): FixedSqlAction[Int, NoStream, Effect.Write]

  def runnableCompiledUpdate[RU, C[_]](c: RunnableCompiled[_ <: Query[_, _, C], C[RU]]): RU => FixedSqlAction[Int, NoStream, Effect.Write]
  def runnableCompiledUpdateSql[RU, C[_]](c: RunnableCompiled[_ <: Query[_, _, C], C[RU]]): String

  def queryUpdate[U, C[_]](c: Query[_, U, C]): U => FixedSqlAction[Int, NoStream, Effect.Write]
  def queryUpdateSql[U, C[_]](c: Query[_, U, C]): String

}
