package org.scalax.bindin.slick

import slick.ast.TableNode

trait BindSqlUtilsComponent {

  def quoteIdentifier(id: String): String
  def quoteTableName(t: TableNode): String
  def likeEncode(s: String): String

}
