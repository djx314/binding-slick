package org.scalax.binding.slick

import slick.dbio.{Effect, NoStream}
import slick.jdbc.JdbcProfile
import slick.lifted.{Query, RunnableCompiled}
import slick.relational.RelationalProfile
import slick.sql.FixedSqlAction

object BindDeleteOrUpdateActionExtensionMethodsHelper {

  def helper(implicit profile: JdbcProfile): BindDeleteOrUpdateActionExtensionMethods = {
    new BindDeleteOrUpdateActionExtensionMethods {
      override def queryDeleteActionExtensionMethodsImpl[C[_]](
        q: Query[_ <: RelationalProfile#Table[_], _, C]
      ): FixedSqlAction[Int, NoStream, Effect.Write] = profile.api.queryDeleteActionExtensionMethods(q).delete
      override def runnableCompiledDeleteActionExtensionMethodsImpl[RU, C[_]](
        c: RunnableCompiled[_ <: Query[_, _, C], C[RU]]
      ): FixedSqlAction[Int, NoStream, Effect.Write] = profile.api.runnableCompiledDeleteActionExtensionMethods(c).delete
      override def runnableCompiledUpdate[RU, C[_]](c: RunnableCompiled[_ <: Query[_, _, C], C[RU]]): RU => FixedSqlAction[Int, NoStream, Effect.Write] = {
        val i = profile.api.runnableCompiledUpdateActionExtensionMethods(c)
        data => i.update(data)
      }
      override def runnableCompiledUpdateSql[RU, C[_]](c: RunnableCompiled[_ <: Query[_, _, C], C[RU]]): String =
        profile.api.runnableCompiledUpdateActionExtensionMethods(c).updateStatement
    }
  }

}
