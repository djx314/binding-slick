package org.scalax.bindin.slick

import slick.basic.Capability

trait BindCapabilities {

  protected def capabilities: Set[Capability]

}
