package org.scalax.bindin.slick

import slick.ast.SymbolNamer
import slick.basic.Capability
import slick.compiler.CompilerState
import slick.jdbc.JdbcProfile

object BindCapabilitiesHelper {

  def helper(implicit profile: JdbcProfile): BindCapabilities = new BindCapabilities {
    override protected def capabilities: Set[Capability] = profile.capabilities
  }

}
