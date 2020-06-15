package org.scalax.binding.slick

import java.sql.{Blob, Clob, Date, Time, Timestamp}
import java.time.{Instant, LocalDate, LocalDateTime, LocalTime, OffsetDateTime, OffsetTime, ZonedDateTime}
import java.util.UUID

import slick.ast.{BaseTypedType, NumericTypedType}
import slick.jdbc.{JdbcProfile, JdbcType}

object BindJdbcTypesHelper {

  def helper(implicit profile: JdbcProfile): BindJdbcTypes = new BindJdbcTypes {
    override def booleanColumnTypeBinding: JdbcType[Boolean] with BaseTypedType[Boolean]                                = profile.columnTypes.booleanJdbcType
    override def blobColumnTypeBinding: JdbcType[Blob] with BaseTypedType[Blob]                                         = profile.columnTypes.blobJdbcType
    override def byteColumnTypeBinding: JdbcType[Byte] with BaseTypedType[Byte] with NumericTypedType                   = profile.columnTypes.byteJdbcType
    override def byteArrayColumnTypeBinding: JdbcType[Array[Byte]] with BaseTypedType[Array[Byte]]                      = profile.columnTypes.byteArrayJdbcType
    override def charColumnTypeBinding: JdbcType[Char] with BaseTypedType[Char]                                         = profile.columnTypes.charJdbcType
    override def clobColumnTypeBinding: JdbcType[Clob] with BaseTypedType[Clob]                                         = profile.columnTypes.clobJdbcType
    override def dateColumnTypeBinding: JdbcType[Date] with BaseTypedType[Date]                                         = profile.columnTypes.dateJdbcType
    override def doubleColumnTypeBinding: JdbcType[Double] with BaseTypedType[Double] with NumericTypedType             = profile.columnTypes.doubleJdbcType
    override def floatColumnTypeBinding: JdbcType[Float] with BaseTypedType[Float] with NumericTypedType                = profile.columnTypes.floatJdbcType
    override def intColumnTypeBinding: JdbcType[Int] with BaseTypedType[Int] with NumericTypedType                      = profile.columnTypes.intJdbcType
    override def longColumnTypeBinding: JdbcType[Long] with BaseTypedType[Long] with NumericTypedType                   = profile.columnTypes.longJdbcType
    override def shortColumnTypeBinding: JdbcType[Short] with BaseTypedType[Short] with NumericTypedType                = profile.columnTypes.shortJdbcType
    override def stringColumnTypeBinding: JdbcType[String] with BaseTypedType[String]                                   = profile.columnTypes.stringJdbcType
    override def timeColumnTypeBinding: JdbcType[Time] with BaseTypedType[Time]                                         = profile.columnTypes.timeJdbcType
    override def timestampColumnTypeBinding: JdbcType[Timestamp] with BaseTypedType[Timestamp]                          = profile.columnTypes.timestampJdbcType
    override def uuidColumnTypeBinding: JdbcType[UUID] with BaseTypedType[UUID]                                         = profile.columnTypes.uuidJdbcType
    override def bigDecimalColumnTypeBinding: JdbcType[BigDecimal] with BaseTypedType[BigDecimal] with NumericTypedType = profile.columnTypes.bigDecimalJdbcType
    override def offsetDateTimeColumnTypeBinding: JdbcType[OffsetDateTime] with BaseTypedType[OffsetDateTime]           = profile.columnTypes.offsetDateTimeType
    override def zonedDateTimeColumnTypeBinding: JdbcType[ZonedDateTime] with BaseTypedType[ZonedDateTime]              = profile.columnTypes.zonedDateType
    override def localTimeColumnTypeBinding: JdbcType[LocalTime] with BaseTypedType[LocalTime]                          = profile.columnTypes.localTimeType
    override def localDateColumnTypeBinding: JdbcType[LocalDate] with BaseTypedType[LocalDate]                          = profile.columnTypes.localDateType
    override def localDateTimeColumnTypeBinding: JdbcType[LocalDateTime] with BaseTypedType[LocalDateTime]              = profile.columnTypes.localDateTimeType
    override def offsetTimeColumnTypeBinding: JdbcType[OffsetTime] with BaseTypedType[OffsetTime]                       = profile.columnTypes.offsetTimeType
    override def instantColumnTypeBinding: JdbcType[Instant] with BaseTypedType[Instant]                                = profile.columnTypes.instantType
    override def nullJdbcTypeBinding: JdbcType[Null] with BaseTypedType[Null]                                           = profile.columnTypes.nullJdbcType
  }

}
