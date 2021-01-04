package org.scalax.binding.slick

import java.sql.{Blob, Clob, Date, Time, Timestamp}
import java.time.{Instant, LocalDate, LocalDateTime, LocalTime, OffsetDateTime, OffsetTime, ZonedDateTime}
import java.util.UUID

import slick.ast.{BaseTypedType, NumericTypedType}
import slick.jdbc.JdbcType

trait BindJdbcTypes extends BindJdbcImplicitColumnAbs {

  override def booleanColumnTypeBinding: JdbcType[Boolean] with BaseTypedType[Boolean]
  override def blobColumnTypeBinding: JdbcType[Blob] with BaseTypedType[Blob]
  override def byteColumnTypeBinding: JdbcType[Byte] with BaseTypedType[Byte] with NumericTypedType
  override def byteArrayColumnTypeBinding: JdbcType[Array[Byte]] with BaseTypedType[Array[Byte]]
  override def charColumnTypeBinding: JdbcType[Char] with BaseTypedType[Char]
  override def clobColumnTypeBinding: JdbcType[Clob] with BaseTypedType[Clob]
  override def dateColumnTypeBinding: JdbcType[Date] with BaseTypedType[Date]
  override def doubleColumnTypeBinding: JdbcType[Double] with BaseTypedType[Double] with NumericTypedType
  override def floatColumnTypeBinding: JdbcType[Float] with BaseTypedType[Float] with NumericTypedType
  override def intColumnTypeBinding: JdbcType[Int] with BaseTypedType[Int] with NumericTypedType
  override def longColumnTypeBinding: JdbcType[Long] with BaseTypedType[Long] with NumericTypedType
  override def shortColumnTypeBinding: JdbcType[Short] with BaseTypedType[Short] with NumericTypedType
  override def stringColumnTypeBinding: JdbcType[String] with BaseTypedType[String]
  override def timeColumnTypeBinding: JdbcType[Time] with BaseTypedType[Time]
  override def timestampColumnTypeBinding: JdbcType[Timestamp] with BaseTypedType[Timestamp]
  override def uuidColumnTypeBinding: JdbcType[UUID] with BaseTypedType[UUID]
  override def bigDecimalColumnTypeBinding: JdbcType[BigDecimal] with BaseTypedType[BigDecimal] with NumericTypedType
  override def offsetDateTimeColumnTypeBinding: JdbcType[OffsetDateTime] with BaseTypedType[OffsetDateTime]
  override def zonedDateTimeColumnTypeBinding: JdbcType[ZonedDateTime] with BaseTypedType[ZonedDateTime]
  override def localTimeColumnTypeBinding: JdbcType[LocalTime] with BaseTypedType[LocalTime]
  override def localDateColumnTypeBinding: JdbcType[LocalDate] with BaseTypedType[LocalDate]
  override def localDateTimeColumnTypeBinding: JdbcType[LocalDateTime] with BaseTypedType[LocalDateTime]
  override def offsetTimeColumnTypeBinding: JdbcType[OffsetTime] with BaseTypedType[OffsetTime]
  override def instantColumnTypeBinding: JdbcType[Instant] with BaseTypedType[Instant]
  def nullJdbcTypeBinding: JdbcType[Null] with BaseTypedType[Null]

}
