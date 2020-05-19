package org.scalax.bindin.slick

import slick.dbio.DBIO
import slick.jdbc.{JdbcModelBuilder, JdbcProfile}
import slick.jdbc.meta.MTable
import slick.model.Model

import scala.concurrent.ExecutionContext

object BindImplicitColumnTypesHelper {

  def helper(implicit profile: JdbcProfile): BindJdbcModelComponent = new BindJdbcModelComponent {
    override def defaultTables(implicit ec: ExecutionContext): DBIO[Seq[MTable]] = profile.defaultTables(ec)
    override def createModel(tables: Option[DBIO[Seq[MTable]]], ignoreInvalidDefaults: Boolean)(implicit ec: ExecutionContext): DBIO[Model] =
      profile.createModel(tables = tables, ignoreInvalidDefaults = ignoreInvalidDefaults)(ec)
    override def createModelBuilder(tables: Seq[MTable], ignoreInvalidDefaults: Boolean)(implicit ec: ExecutionContext): JdbcModelBuilder =
      profile.createModelBuilder(tables = tables, ignoreInvalidDefaults = ignoreInvalidDefaults)(ec)
  }

}
