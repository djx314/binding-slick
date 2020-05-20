package org.scalax.binding.slick

import java.sql.{Blob, Clob, Date, Time, Timestamp}
import java.time.{Instant, LocalDate, LocalDateTime, LocalTime, OffsetDateTime, OffsetTime, ZonedDateTime}
import java.util.UUID

import slick.ast.{BaseTypedType, NumericTypedType}
import slick.jdbc.JdbcType

trait BindJdbcTypes extends BindJdbcImplicitColumnAbs {

  override def booleanColumnType: JdbcType[Boolean] with BaseTypedType[Boolean]
  override def blobColumnType: JdbcType[Blob] with BaseTypedType[Blob]
  override def byteColumnType: JdbcType[Byte] with BaseTypedType[Byte] with NumericTypedType
  override def byteArrayColumnType: JdbcType[Array[Byte]] with BaseTypedType[Array[Byte]]
  override def charColumnType: JdbcType[Char] with BaseTypedType[Char]
  override def clobColumnType: JdbcType[Clob] with BaseTypedType[Clob]
  override def dateColumnType: JdbcType[Date] with BaseTypedType[Date]
  override def doubleColumnType: JdbcType[Double] with BaseTypedType[Double] with NumericTypedType
  override def floatColumnType: JdbcType[Float] with BaseTypedType[Float] with NumericTypedType
  override def intColumnType: JdbcType[Int] with BaseTypedType[Int] with NumericTypedType
  override def longColumnType: JdbcType[Long] with BaseTypedType[Long] with NumericTypedType
  override def shortColumnType: JdbcType[Short] with BaseTypedType[Short] with NumericTypedType
  override def stringColumnType: JdbcType[String] with BaseTypedType[String]
  override def timeColumnType: JdbcType[Time] with BaseTypedType[Time]
  override def timestampColumnType: JdbcType[Timestamp] with BaseTypedType[Timestamp]
  override def uuidColumnType: JdbcType[UUID] with BaseTypedType[UUID]
  override def bigDecimalColumnType: JdbcType[BigDecimal] with BaseTypedType[BigDecimal] with NumericTypedType
  override def offsetDateTimeColumnType: JdbcType[OffsetDateTime] with BaseTypedType[OffsetDateTime]
  override def zonedDateTimeColumnType: JdbcType[ZonedDateTime] with BaseTypedType[ZonedDateTime]
  override def localTimeColumnType: JdbcType[LocalTime] with BaseTypedType[LocalTime]
  override def localDateColumnType: JdbcType[LocalDate] with BaseTypedType[LocalDate]
  override def localDateTimeColumnType: JdbcType[LocalDateTime] with BaseTypedType[LocalDateTime]
  override def offsetTimeColumnType: JdbcType[OffsetTime] with BaseTypedType[OffsetTime]
  override def instantColumnType: JdbcType[Instant] with BaseTypedType[Instant]
  def nullJdbcType: JdbcType[Null] with BaseTypedType[Null]

}
