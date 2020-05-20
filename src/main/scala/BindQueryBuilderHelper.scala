package org.scalax.binding.slick

import slick.ast.SymbolNamer
import slick.compiler.CompilerState
import slick.jdbc.JdbcProfile

object BindQueryBuilderHelper {

  def helper(state: CompilerState)(implicit profile: JdbcProfile): BindQueryBuilder = new BindQueryBuilder {
    override protected def symbolName: SymbolNamer = new profile.QuotingSymbolNamer(Some(state.symbolNamer))
  }

}
