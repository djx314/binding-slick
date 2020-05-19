package org.scalax.bindin.slick

import slick.dbio.DBIO
import slick.jdbc.JdbcModelBuilder
import slick.jdbc.meta.MTable
import slick.model.Model

import scala.concurrent.ExecutionContext

trait BindJdbcModelComponent {

  def defaultTables(implicit ec: ExecutionContext): DBIO[Seq[MTable]]
  def createModel(tables: Option[DBIO[Seq[MTable]]] = None, ignoreInvalidDefaults: Boolean = true)(implicit ec: ExecutionContext): DBIO[Model]
  def createModelBuilder(tables: Seq[MTable], ignoreInvalidDefaults: Boolean)(implicit ec: ExecutionContext): JdbcModelBuilder

}
