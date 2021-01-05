package org.scalax.binding.slick

import slick.ast.Node
import slick.compiler.CompilerState

trait MysqlDBProfile extends DBProfile {

  import slick.jdbc.MySQLProfile.api._

  override def bindSqlUtilsComponent: BindSqlUtilsComponent                            = BindSqlUtilsComponentHelper.helper
  override def bindCreateQueryBuilder(n: Node, state: CompilerState): BindQueryBuilder = BindQueryBuilderHelper.helper(state)
  override def bindCapabilities: BindCapabilities                                      = BindCapabilitiesHelper.helper
  override def bindJdbcModelComponent: BindJdbcModelComponent                          = BindJdbcModelComponentHelper.helper
  override def bindImplicitColumnTypes: BindImplicitColumnTypes                        = BindImplicitColumnTypesHelper.helper
  type BindingAPI = BindJdbcImplicitColumnTypes with BindDeleteOrUpdateActionExtensionMethods with BindColumnExtensionMethods
  val api: BindingAPI = new BindJdbcImplicitColumnTypesHelper.BindJdbcImplicitColumnTypesImpl
  with BindDeleteOrUpdateActionExtensionMethodsHelper.BindDeleteOrUpdateActionExtensionMethodsImpl with BindColumnExtensionMethodsHelper.BindColumnExtensionMethodsImpl {
    override val profile = implicitly
  }
  /*override def bindJdbcImplicitColumnTypes: BindJdbcImplicitColumnTypes = new BindJdbcImplicitColumnTypesHelper.BindJdbcImplicitColumnTypesImpl {
    override val profile = implicitly
  }*/
  override def bindJdbcTypes: BindJdbcTypes = BindJdbcTypesHelper.helper
  // override def bindDeleteOrUpdateActionExtensionMethods: BindDeleteOrUpdateActionExtensionMethods = BindDeleteOrUpdateActionExtensionMethodsHelper.helper
  /*override def bindJdbcActionExtensionMethods: BindJdbcActionExtensionMethods = new BindJdbcActionExtensionMethodsHelper.BindJdbcActionExtensionMethodsImpl {
    override val profile = implicitly
  }*/
  // override def bindActionBasedSQLInterpolation: BindActionBasedSQLInterpolation = new BindActionBasedSQLInterpolation {}

}

object MysqlDBProfile extends MysqlDBProfile {

  override val bindSqlUtilsComponent: BindSqlUtilsComponent     = super.bindSqlUtilsComponent
  override val bindCapabilities: BindCapabilities               = super.bindCapabilities
  override val bindJdbcModelComponent: BindJdbcModelComponent   = super.bindJdbcModelComponent
  override val bindImplicitColumnTypes: BindImplicitColumnTypes = super.bindImplicitColumnTypes
  // override val bindJdbcImplicitColumnTypes: BindJdbcImplicitColumnTypes                           = super.bindJdbcImplicitColumnTypes
  override val bindJdbcTypes: BindJdbcTypes = super.bindJdbcTypes
  // override val bindDeleteOrUpdateActionExtensionMethods: BindDeleteOrUpdateActionExtensionMethods = super.bindDeleteOrUpdateActionExtensionMethods
  // override val bindJdbcActionExtensionMethods: BindJdbcActionExtensionMethods                     = super.bindJdbcActionExtensionMethods
  // override val bindActionBasedSQLInterpolation: BindActionBasedSQLInterpolation                   = super.bindActionBasedSQLInterpolation

}
