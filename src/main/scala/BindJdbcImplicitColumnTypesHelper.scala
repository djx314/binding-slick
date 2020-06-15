package org.scalax.binding.slick

import java.sql.{Blob, Clob, Date, Time, Timestamp}
import java.time.{Instant, LocalDate, LocalDateTime, LocalTime, OffsetDateTime, OffsetTime, ZonedDateTime}
import java.util.UUID

import slick.ast.{BaseTypedType, NumericTypedType}
import slick.jdbc.{JdbcProfile, JdbcType}

import scala.reflect.ClassTag

object BindJdbcImplicitColumnTypesHelper {

  trait BindJdbcImplicitColumnTypesImpl extends BindJdbcImplicitColumnTypes {
    val profile: JdbcProfile
    import profile.api._

    override def isomorphicTypeBinding[A, B](
      implicit iso: Isomorphism[A, B],
      ct: ClassTag[A],
      jt: JdbcType[B] with BaseTypedType[B]
    ): JdbcType[A] with BaseTypedType[A]                                                                                = implicitly
    override def booleanColumnTypeBinding: JdbcType[Boolean] with BaseTypedType[Boolean]                                = implicitly
    override def blobColumnTypeBinding: JdbcType[Blob] with BaseTypedType[Blob]                                         = implicitly
    override def byteColumnTypeBinding: JdbcType[Byte] with BaseTypedType[Byte] with NumericTypedType                   = implicitly
    override def byteArrayColumnTypeBinding: JdbcType[Array[Byte]] with BaseTypedType[Array[Byte]]                      = implicitly
    override def charColumnTypeBinding: JdbcType[Char] with BaseTypedType[Char]                                         = implicitly
    override def clobColumnTypeBinding: JdbcType[Clob] with BaseTypedType[Clob]                                         = implicitly
    override def dateColumnTypeBinding: JdbcType[Date] with BaseTypedType[Date]                                         = implicitly
    override def doubleColumnTypeBinding: JdbcType[Double] with BaseTypedType[Double] with NumericTypedType             = implicitly
    override def floatColumnTypeBinding: JdbcType[Float] with BaseTypedType[Float] with NumericTypedType                = implicitly
    override def intColumnTypeBinding: JdbcType[Int] with BaseTypedType[Int] with NumericTypedType                      = implicitly
    override def longColumnTypeBinding: JdbcType[Long] with BaseTypedType[Long] with NumericTypedType                   = implicitly
    override def shortColumnTypeBinding: JdbcType[Short] with BaseTypedType[Short] with NumericTypedType                = implicitly
    override def stringColumnTypeBinding: JdbcType[String] with BaseTypedType[String]                                   = implicitly
    override def timeColumnTypeBinding: JdbcType[Time] with BaseTypedType[Time]                                         = implicitly
    override def timestampColumnTypeBinding: JdbcType[Timestamp] with BaseTypedType[Timestamp]                          = implicitly
    override def uuidColumnTypeBinding: JdbcType[UUID] with BaseTypedType[UUID]                                         = implicitly
    override def bigDecimalColumnTypeBinding: JdbcType[BigDecimal] with BaseTypedType[BigDecimal] with NumericTypedType = implicitly
    override def offsetDateTimeColumnTypeBinding: JdbcType[OffsetDateTime] with BaseTypedType[OffsetDateTime]           = implicitly
    override def zonedDateTimeColumnTypeBinding: JdbcType[ZonedDateTime] with BaseTypedType[ZonedDateTime]              = implicitly
    override def localTimeColumnTypeBinding: JdbcType[LocalTime] with BaseTypedType[LocalTime]                          = implicitly
    override def localDateColumnTypeBinding: JdbcType[LocalDate] with BaseTypedType[LocalDate]                          = implicitly
    override def localDateTimeColumnTypeBinding: JdbcType[LocalDateTime] with BaseTypedType[LocalDateTime]              = implicitly
    override def offsetTimeColumnTypeBinding: JdbcType[OffsetTime] with BaseTypedType[OffsetTime]                       = implicitly
    override def instantColumnTypeBinding: JdbcType[Instant] with BaseTypedType[Instant]                                = implicitly
  }

}
