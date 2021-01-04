package org.scalax.binding.slick

import slick.jdbc.ActionBasedSQLInterpolation

trait BindActionBasedSQLInterpolation {
  def actionBasedSQLInterpolation(s: StringContext): ActionBasedSQLInterpolation = new ActionBasedSQLInterpolation(s)
}
