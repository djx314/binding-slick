package org.scalax.binding.slick

import java.sql.{Blob, Clob, Date, Time, Timestamp}
import java.time.{Instant, LocalDate, LocalDateTime, LocalTime, OffsetDateTime, OffsetTime, ZonedDateTime}
import java.util.UUID

import slick.ast.{BaseTypedType, NumericTypedType}
import slick.jdbc.JdbcType

trait BindJdbcImplicitColumnAbs extends BindImplicitColumnAbs {

  override implicit def booleanColumnTypeBinding: JdbcType[Boolean] with BaseTypedType[Boolean]
  implicit def blobColumnTypeBinding: JdbcType[Blob] with BaseTypedType[Blob]
  override implicit def byteColumnTypeBinding: JdbcType[Byte] with BaseTypedType[Byte] with NumericTypedType
  implicit def byteArrayColumnTypeBinding: JdbcType[Array[Byte]] with BaseTypedType[Array[Byte]]
  override implicit def charColumnTypeBinding: JdbcType[Char] with BaseTypedType[Char]
  implicit def clobColumnTypeBinding: JdbcType[Clob] with BaseTypedType[Clob]
  implicit def dateColumnTypeBinding: JdbcType[Date] with BaseTypedType[Date]
  override implicit def doubleColumnTypeBinding: JdbcType[Double] with BaseTypedType[Double] with NumericTypedType
  override implicit def floatColumnTypeBinding: JdbcType[Float] with BaseTypedType[Float] with NumericTypedType
  override implicit def intColumnTypeBinding: JdbcType[Int] with BaseTypedType[Int] with NumericTypedType
  override implicit def longColumnTypeBinding: JdbcType[Long] with BaseTypedType[Long] with NumericTypedType
  override implicit def shortColumnTypeBinding: JdbcType[Short] with BaseTypedType[Short] with NumericTypedType
  override implicit def stringColumnTypeBinding: JdbcType[String] with BaseTypedType[String]
  implicit def timeColumnTypeBinding: JdbcType[Time] with BaseTypedType[Time]
  implicit def timestampColumnTypeBinding: JdbcType[Timestamp] with BaseTypedType[Timestamp]
  implicit def uuidColumnTypeBinding: JdbcType[UUID] with BaseTypedType[UUID]
  override implicit def bigDecimalColumnTypeBinding: JdbcType[BigDecimal] with BaseTypedType[BigDecimal] with NumericTypedType
  implicit def offsetDateTimeColumnTypeBinding: JdbcType[OffsetDateTime] with BaseTypedType[OffsetDateTime]
  implicit def zonedDateTimeColumnTypeBinding: JdbcType[ZonedDateTime] with BaseTypedType[ZonedDateTime]
  implicit def localTimeColumnTypeBinding: JdbcType[LocalTime] with BaseTypedType[LocalTime]
  implicit def localDateColumnTypeBinding: JdbcType[LocalDate] with BaseTypedType[LocalDate]
  implicit def localDateTimeColumnTypeBinding: JdbcType[LocalDateTime] with BaseTypedType[LocalDateTime]
  implicit def offsetTimeColumnTypeBinding: JdbcType[OffsetTime] with BaseTypedType[OffsetTime]
  implicit def instantColumnTypeBinding: JdbcType[Instant] with BaseTypedType[Instant]

}
