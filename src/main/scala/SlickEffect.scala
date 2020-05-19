package org.scalax.bindin.slick

import slick.jdbc.JdbcProfile

trait QuoteIdentifier {
  def quoteIdentifier(name: String): String
}

object SlickQuoteIdentifierHelper {

  def helper(implicit profile: JdbcProfile): QuoteIdentifier = new QuoteIdentifier {
    override def quoteIdentifier(name: String): String = profile.quoteIdentifier(name)
  }

}

trait DBProfile {

  def quoteIdentifier: QuoteIdentifier

}

trait MysqlDBProfile extends DBProfile {

  import slick.jdbc.MySQLProfile.api._

  override def quoteIdentifier: QuoteIdentifier = SlickQuoteIdentifierHelper.helper

}

object MysqlDBProfile extends MysqlDBProfile {

  override val quoteIdentifier: QuoteIdentifier = super.quoteIdentifier

}
