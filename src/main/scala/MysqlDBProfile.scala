package org.scalax.bindin.slick

trait MysqlDBProfile extends DBProfile {

  import slick.jdbc.MySQLProfile.api._

  override def bindSqlUtilsComponent: BindSqlUtilsComponent = BindSqlUtilsComponentHelper.helper

}

object MysqlDBProfile extends MysqlDBProfile {

  override val bindSqlUtilsComponent: BindSqlUtilsComponent = super.bindSqlUtilsComponent

}
