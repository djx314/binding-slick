package org.scalax.binding.slick

import java.sql.{Blob, Clob, Date, Time, Timestamp}
import java.time.{Instant, LocalDate, LocalDateTime, LocalTime, OffsetDateTime, OffsetTime, ZonedDateTime}
import java.util.UUID

import slick.ast.BaseTypedType
import slick.jdbc.JdbcType

trait BindJdbcImplicitColumnAbs extends BindImplicitColumnAbs {

  //override implicit def booleanColumnType: JdbcType[Boolean] with BaseTypedType[Boolean]
  implicit def blobColumnType: JdbcType[Blob] with BaseTypedType[Blob]
  //override implicit def byteColumnType: JdbcType[Byte] with BaseTypedType[Byte] with NumericTypedType
  implicit def byteArrayColumnType: JdbcType[Array[Byte]] with BaseTypedType[Array[Byte]]
  //override implicit def charColumnType: JdbcType[Char] with BaseTypedType[Char]
  implicit def clobColumnType: JdbcType[Clob] with BaseTypedType[Clob]
  implicit def dateColumnType: JdbcType[Date] with BaseTypedType[Date]
  //override implicit def doubleColumnType: JdbcType[Double] with BaseTypedType[Double] with NumericTypedType
  //override implicit def floatColumnType: JdbcType[Float] with BaseTypedType[Float] with NumericTypedType
  //override implicit def intColumnType: JdbcType[Int] with BaseTypedType[Int] with NumericTypedType
  //override implicit def longColumnType: JdbcType[Long] with BaseTypedType[Long] with NumericTypedType
  //override implicit def shortColumnType: JdbcType[Short] with BaseTypedType[Short] with NumericTypedType
  //override implicit def stringColumnType: JdbcType[String] with BaseTypedType[String]
  implicit def timeColumnType: JdbcType[Time] with BaseTypedType[Time]
  implicit def timestampColumnType: JdbcType[Timestamp] with BaseTypedType[Timestamp]
  implicit def uuidColumnType: JdbcType[UUID] with BaseTypedType[UUID]
  //override implicit def bigDecimalColumnType: JdbcType[BigDecimal] with BaseTypedType[BigDecimal] with NumericTypedType
  implicit def offsetDateTimeColumnType: JdbcType[OffsetDateTime] with BaseTypedType[OffsetDateTime]
  implicit def zonedDateTimeColumnType: JdbcType[ZonedDateTime] with BaseTypedType[ZonedDateTime]
  implicit def localTimeColumnType: JdbcType[LocalTime] with BaseTypedType[LocalTime]
  implicit def localDateColumnType: JdbcType[LocalDate] with BaseTypedType[LocalDate]
  implicit def localDateTimeColumnType: JdbcType[LocalDateTime] with BaseTypedType[LocalDateTime]
  implicit def offsetTimeColumnType: JdbcType[OffsetTime] with BaseTypedType[OffsetTime]
  implicit def instantColumnType: JdbcType[Instant] with BaseTypedType[Instant]

}
