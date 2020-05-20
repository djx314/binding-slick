package org.scalax.binding.slick

import slick.basic.Capability

trait BindCapabilities {

  protected def capabilities: Set[Capability]

}
