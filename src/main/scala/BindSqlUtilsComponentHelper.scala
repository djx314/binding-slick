package org.scalax.binding.slick

import slick.ast.TableNode
import slick.jdbc.JdbcProfile

object BindSqlUtilsComponentHelper {

  def helper(implicit profile: JdbcProfile): BindSqlUtilsComponent = new BindSqlUtilsComponent {
    override def quoteIdentifier(name: String): String = profile.quoteIdentifier(name)
    override def quoteTableName(t: TableNode)          = profile.quoteTableName(t)
    override def likeEncode(s: String): String         = profile.likeEncode(s)
  }

}
