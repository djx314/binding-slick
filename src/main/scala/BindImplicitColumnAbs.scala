package org.scalax.binding.slick

import slick.ast.{BaseTypedType, NumericTypedType}
import slick.jdbc.JdbcType

trait BindImplicitColumnAbs {

  implicit def booleanColumnType: JdbcType[Boolean] with BaseTypedType[Boolean]
  implicit def bigDecimalColumnType: JdbcType[BigDecimal] with BaseTypedType[BigDecimal] with NumericTypedType
  implicit def byteColumnType: JdbcType[Byte] with BaseTypedType[Byte] with NumericTypedType
  implicit def charColumnType: JdbcType[Char] with BaseTypedType[Char]
  implicit def doubleColumnType: JdbcType[Double] with BaseTypedType[Double] with NumericTypedType
  implicit def floatColumnType: JdbcType[Float] with BaseTypedType[Float] with NumericTypedType
  implicit def intColumnType: JdbcType[Int] with BaseTypedType[Int] with NumericTypedType
  implicit def longColumnType: JdbcType[Long] with BaseTypedType[Long] with NumericTypedType
  implicit def shortColumnType: JdbcType[Short] with BaseTypedType[Short] with NumericTypedType
  implicit def stringColumnType: JdbcType[String] with BaseTypedType[String]

}
