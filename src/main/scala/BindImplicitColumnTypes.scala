package org.scalax.bindin.slick

import slick.ast.NumericTypedType
import slick.jdbc.JdbcProfile
import slick.lifted.Isomorphism

import scala.reflect.ClassTag

trait BindImplicitColumnTypes {

  val profile: JdbcProfile

  implicit def isomorphicType[A, B](implicit iso: Isomorphism[A, B], ct: ClassTag[A], jt: profile.BaseColumnType[B]): profile.BaseColumnType[A] =
    profile.MappedColumnType.base[A, B](iso.map, iso.comap)
  implicit def booleanColumnType: profile.BaseColumnType[Boolean]                             = profile.api.booleanColumnType
  implicit def bigDecimalColumnType: profile.BaseColumnType[BigDecimal] with NumericTypedType = profile.api.bigDecimalColumnType
  implicit def byteColumnType: profile.BaseColumnType[Byte] with NumericTypedType             = profile.api.byteColumnType
  implicit def charColumnType: profile.BaseColumnType[Char]                                   = profile.api.charColumnType
  implicit def doubleColumnType: profile.BaseColumnType[Double] with NumericTypedType         = profile.api.doubleColumnType
  implicit def floatColumnType: profile.BaseColumnType[Float] with NumericTypedType           = profile.api.floatColumnType
  implicit def intColumnType: profile.BaseColumnType[Int] with NumericTypedType               = profile.api.intColumnType
  implicit def longColumnType: profile.BaseColumnType[Long] with NumericTypedType             = profile.api.longColumnType
  implicit def shortColumnType: profile.BaseColumnType[Short] with NumericTypedType           = profile.api.shortColumnType
  implicit def stringColumnType: profile.BaseColumnType[String]                               = profile.api.stringColumnType

}
