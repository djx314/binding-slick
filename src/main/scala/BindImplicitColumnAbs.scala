package org.scalax.binding.slick

import slick.ast.{BaseTypedType, NumericTypedType}
import slick.jdbc.JdbcType

trait BindImplicitColumnAbs {

  def booleanColumnTypeBinding: JdbcType[Boolean] with BaseTypedType[Boolean]
  def bigDecimalColumnTypeBinding: JdbcType[BigDecimal] with BaseTypedType[BigDecimal] with NumericTypedType
  def byteColumnTypeBinding: JdbcType[Byte] with BaseTypedType[Byte] with NumericTypedType
  def charColumnTypeBinding: JdbcType[Char] with BaseTypedType[Char]
  def doubleColumnTypeBinding: JdbcType[Double] with BaseTypedType[Double] with NumericTypedType
  def floatColumnTypeBinding: JdbcType[Float] with BaseTypedType[Float] with NumericTypedType
  def intColumnTypeBinding: JdbcType[Int] with BaseTypedType[Int] with NumericTypedType
  def longColumnTypeBinding: JdbcType[Long] with BaseTypedType[Long] with NumericTypedType
  def shortColumnTypeBinding: JdbcType[Short] with BaseTypedType[Short] with NumericTypedType
  def stringColumnTypeBinding: JdbcType[String] with BaseTypedType[String]

}
