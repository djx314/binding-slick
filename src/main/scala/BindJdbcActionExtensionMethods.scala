package org.scalax.binding.slick

import slick.dbio.{DBIOAction, Effect, NoStream}

trait BindJdbcActionExtensionMethods {
  implicit def bindJdbcActionExtensionMethods[E <: Effect, R, S <: NoStream](a: DBIOAction[R, S, E]): BindJdbcActionContent[E, R, S]
}
