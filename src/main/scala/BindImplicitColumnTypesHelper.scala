package org.scalax.binding.slick

import slick.ast.{BaseTypedType, NumericTypedType}
import slick.jdbc.{JdbcProfile, JdbcType}

import scala.reflect.ClassTag

object BindImplicitColumnTypesHelper {

  def helper(
    implicit profile: JdbcProfile
  ): BindImplicitColumnTypes = {
    import profile.api._
    new BindImplicitColumnTypes {
      override def isomorphicTypeBinding[A, B](
        implicit iso: Isomorphism[A, B],
        ct: ClassTag[A],
        jt: JdbcType[B] with BaseTypedType[B]
      ): JdbcType[A] with BaseTypedType[A]                                                                                         = implicitly
      override implicit def booleanColumnTypeBinding: JdbcType[Boolean] with BaseTypedType[Boolean]                                = implicitly
      override implicit def bigDecimalColumnTypeBinding: JdbcType[BigDecimal] with BaseTypedType[BigDecimal] with NumericTypedType = implicitly
      override implicit def byteColumnTypeBinding: JdbcType[Byte] with BaseTypedType[Byte] with NumericTypedType                   = implicitly
      override implicit def charColumnTypeBinding: JdbcType[Char] with BaseTypedType[Char]                                         = implicitly
      override implicit def doubleColumnTypeBinding: JdbcType[Double] with BaseTypedType[Double] with NumericTypedType             = implicitly
      override implicit def floatColumnTypeBinding: JdbcType[Float] with BaseTypedType[Float] with NumericTypedType                = implicitly
      override implicit def intColumnTypeBinding: JdbcType[Int] with BaseTypedType[Int] with NumericTypedType                      = implicitly
      override implicit def longColumnTypeBinding: JdbcType[Long] with BaseTypedType[Long] with NumericTypedType                   = implicitly
      override implicit def shortColumnTypeBinding: JdbcType[Short] with BaseTypedType[Short] with NumericTypedType                = implicitly
      override implicit def stringColumnTypeBinding: JdbcType[String] with BaseTypedType[String]                                   = implicitly
    }
  }

}
