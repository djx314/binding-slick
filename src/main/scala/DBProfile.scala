package org.scalax.binding.slick

import slick.ast.Node
import slick.compiler.CompilerState

trait DBProfile {

  def bindSqlUtilsComponent: BindSqlUtilsComponent
  def bindCreateQueryBuilder(n: Node, state: CompilerState): BindQueryBuilder
  def bindCapabilities: BindCapabilities
  def bindJdbcModelComponent: BindJdbcModelComponent
  def bindImplicitColumnTypes: BindImplicitColumnTypes

}
