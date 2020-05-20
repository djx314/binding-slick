package org.scalax.binding.slick

import java.sql.{Blob, Clob, Date, Time, Timestamp}
import java.time.{Instant, LocalDate, LocalDateTime, LocalTime, OffsetDateTime, OffsetTime, ZonedDateTime}
import java.util.UUID

import slick.ast.{BaseTypedType, NumericTypedType}
import slick.jdbc.{JdbcProfile, JdbcType}

object BindJdbcTypesHelper {

  def helper(implicit profile: JdbcProfile): BindJdbcTypes = new BindJdbcTypes {
    override def booleanColumnType: JdbcType[Boolean] with BaseTypedType[Boolean]                                = profile.columnTypes.booleanJdbcType
    override def blobColumnType: JdbcType[Blob] with BaseTypedType[Blob]                                         = profile.columnTypes.blobJdbcType
    override def byteColumnType: JdbcType[Byte] with BaseTypedType[Byte] with NumericTypedType                   = profile.columnTypes.byteJdbcType
    override def byteArrayColumnType: JdbcType[Array[Byte]] with BaseTypedType[Array[Byte]]                      = profile.columnTypes.byteArrayJdbcType
    override def charColumnType: JdbcType[Char] with BaseTypedType[Char]                                         = profile.columnTypes.charJdbcType
    override def clobColumnType: JdbcType[Clob] with BaseTypedType[Clob]                                         = profile.columnTypes.clobJdbcType
    override def dateColumnType: JdbcType[Date] with BaseTypedType[Date]                                         = profile.columnTypes.dateJdbcType
    override def doubleColumnType: JdbcType[Double] with BaseTypedType[Double] with NumericTypedType             = profile.columnTypes.doubleJdbcType
    override def floatColumnType: JdbcType[Float] with BaseTypedType[Float] with NumericTypedType                = profile.columnTypes.floatJdbcType
    override def intColumnType: JdbcType[Int] with BaseTypedType[Int] with NumericTypedType                      = profile.columnTypes.intJdbcType
    override def longColumnType: JdbcType[Long] with BaseTypedType[Long] with NumericTypedType                   = profile.columnTypes.longJdbcType
    override def shortColumnType: JdbcType[Short] with BaseTypedType[Short] with NumericTypedType                = profile.columnTypes.shortJdbcType
    override def stringColumnType: JdbcType[String] with BaseTypedType[String]                                   = profile.columnTypes.stringJdbcType
    override def timeColumnType: JdbcType[Time] with BaseTypedType[Time]                                         = profile.columnTypes.timeJdbcType
    override def timestampColumnType: JdbcType[Timestamp] with BaseTypedType[Timestamp]                          = profile.columnTypes.timestampJdbcType
    override def uuidColumnType: JdbcType[UUID] with BaseTypedType[UUID]                                         = profile.columnTypes.uuidJdbcType
    override def bigDecimalColumnType: JdbcType[BigDecimal] with BaseTypedType[BigDecimal] with NumericTypedType = profile.columnTypes.bigDecimalJdbcType
    override def offsetDateTimeColumnType: JdbcType[OffsetDateTime] with BaseTypedType[OffsetDateTime]           = profile.columnTypes.offsetDateTimeType
    override def zonedDateTimeColumnType: JdbcType[ZonedDateTime] with BaseTypedType[ZonedDateTime]              = profile.columnTypes.zonedDateType
    override def localTimeColumnType: JdbcType[LocalTime] with BaseTypedType[LocalTime]                          = profile.columnTypes.localTimeType
    override def localDateColumnType: JdbcType[LocalDate] with BaseTypedType[LocalDate]                          = profile.columnTypes.localDateType
    override def localDateTimeColumnType: JdbcType[LocalDateTime] with BaseTypedType[LocalDateTime]              = profile.columnTypes.localDateTimeType
    override def offsetTimeColumnType: JdbcType[OffsetTime] with BaseTypedType[OffsetTime]                       = profile.columnTypes.offsetTimeType
    override def instantColumnType: JdbcType[Instant] with BaseTypedType[Instant]                                = profile.columnTypes.instantType
    override def nullJdbcType: JdbcType[Null] with BaseTypedType[Null]                                           = profile.columnTypes.nullJdbcType
  }

}
