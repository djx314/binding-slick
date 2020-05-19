package org.scalax.bindin.slick

import slick.ast.{BaseTypedType, NumericTypedType}
import slick.jdbc.{JdbcProfile, JdbcType}
import slick.lifted.Isomorphism

import scala.reflect.ClassTag

object BindImplicitColumnTypesHelper {

  def helper(
    implicit profile: JdbcProfile,
    booleanColumnType1: JdbcType[Boolean] with BaseTypedType[Boolean],
    bigDecimalColumnType1: JdbcType[BigDecimal] with BaseTypedType[BigDecimal] with NumericTypedType,
    byteColumnType1: JdbcType[Byte] with BaseTypedType[Byte] with NumericTypedType,
    charColumnType1: JdbcType[Char] with BaseTypedType[Char],
    doubleColumnType1: JdbcType[Double] with BaseTypedType[Double] with NumericTypedType,
    floatColumnType1: JdbcType[Float] with BaseTypedType[Float] with NumericTypedType,
    intColumnType1: JdbcType[Int] with BaseTypedType[Int] with NumericTypedType,
    longColumnType1: JdbcType[Long] with BaseTypedType[Long] with NumericTypedType,
    shortColumnType1: JdbcType[Short] with BaseTypedType[Short] with NumericTypedType,
    stringColumnType1: JdbcType[String] with BaseTypedType[String]
  ): BindImplicitColumnTypes = new BindImplicitColumnTypes {
    override implicit def isomorphicType[A, B](
      implicit iso: Isomorphism[A, B],
      ct: ClassTag[A],
      jt: JdbcType[B] with BaseTypedType[B]
    ): JdbcType[A] with BaseTypedType[A] =
      profile.MappedColumnType.base[A, B](iso.map, iso.comap)
    override implicit def booleanColumnType: JdbcType[Boolean] with BaseTypedType[Boolean]                                = booleanColumnType1
    override implicit def bigDecimalColumnType: JdbcType[BigDecimal] with BaseTypedType[BigDecimal] with NumericTypedType = bigDecimalColumnType1
    override implicit def byteColumnType: JdbcType[Byte] with BaseTypedType[Byte] with NumericTypedType                   = byteColumnType1
    override implicit def charColumnType: JdbcType[Char] with BaseTypedType[Char]                                         = charColumnType1
    override implicit def doubleColumnType: JdbcType[Double] with BaseTypedType[Double] with NumericTypedType             = doubleColumnType1
    override implicit def floatColumnType: JdbcType[Float] with BaseTypedType[Float] with NumericTypedType                = floatColumnType1
    override implicit def intColumnType: JdbcType[Int] with BaseTypedType[Int] with NumericTypedType                      = intColumnType1
    override implicit def longColumnType: JdbcType[Long] with BaseTypedType[Long] with NumericTypedType                   = longColumnType1
    override implicit def shortColumnType: JdbcType[Short] with BaseTypedType[Short] with NumericTypedType                = shortColumnType1
    override implicit def stringColumnType: JdbcType[String] with BaseTypedType[String]                                   = stringColumnType1
  }

}
