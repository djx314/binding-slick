package org.scalax.binding.slick

import java.sql.{Blob, Clob, Date, Time, Timestamp}
import java.time.{Instant, LocalDate, LocalDateTime, LocalTime, OffsetDateTime, OffsetTime, ZonedDateTime}
import java.util.UUID

import slick.ast.{BaseTypedType, NumericTypedType}
import slick.jdbc.JdbcType
import slick.lifted.Isomorphism

import scala.reflect.ClassTag

object BindJdbcImplicitColumnTypesHelper {

  def helper(
    implicit isomorphicTypeImplicitFetch: IsomorphicTypeImplicitFetch,
    booleanColumnType1: JdbcType[Boolean] with BaseTypedType[Boolean],
    blobColumnType1: JdbcType[Blob] with BaseTypedType[Blob],
    byteColumnType1: JdbcType[Byte] with BaseTypedType[Byte] with NumericTypedType,
    byteArrayColumnType1: JdbcType[Array[Byte]] with BaseTypedType[Array[Byte]],
    charColumnType1: JdbcType[Char] with BaseTypedType[Char],
    clobColumnType1: JdbcType[Clob] with BaseTypedType[Clob],
    dateColumnType1: JdbcType[Date] with BaseTypedType[Date],
    doubleColumnType1: JdbcType[Double] with BaseTypedType[Double] with NumericTypedType,
    floatColumnType1: JdbcType[Float] with BaseTypedType[Float] with NumericTypedType,
    intColumnType1: JdbcType[Int] with BaseTypedType[Int] with NumericTypedType,
    longColumnType1: JdbcType[Long] with BaseTypedType[Long] with NumericTypedType,
    shortColumnType1: JdbcType[Short] with BaseTypedType[Short] with NumericTypedType,
    stringColumnType1: JdbcType[String] with BaseTypedType[String],
    timeColumnType1: JdbcType[Time] with BaseTypedType[Time],
    timestampColumnType1: JdbcType[Timestamp] with BaseTypedType[Timestamp],
    uuidColumnType1: JdbcType[UUID] with BaseTypedType[UUID],
    bigDecimalColumnType1: JdbcType[BigDecimal] with BaseTypedType[BigDecimal] with NumericTypedType,
    offsetDateTimeColumnType1: JdbcType[OffsetDateTime] with BaseTypedType[OffsetDateTime],
    zonedDateTimeColumnType1: JdbcType[ZonedDateTime] with BaseTypedType[ZonedDateTime],
    localTimeColumnType1: JdbcType[LocalTime] with BaseTypedType[LocalTime],
    localDateColumnType1: JdbcType[LocalDate] with BaseTypedType[LocalDate],
    localDateTimeColumnType1: JdbcType[LocalDateTime] with BaseTypedType[LocalDateTime],
    offsetTimeColumnType1: JdbcType[OffsetTime] with BaseTypedType[OffsetTime],
    instantColumnType1: JdbcType[Instant] with BaseTypedType[Instant]
  ): BindJdbcImplicitColumnTypes = new BindJdbcImplicitColumnTypes {
    override implicit def isomorphicTypeImplicit[A, B](
      implicit iso: Isomorphism[A, B],
      ct: ClassTag[A],
      jt: JdbcType[B] with BaseTypedType[B]
    ): JdbcType[A] with BaseTypedType[A]                                                                                  = isomorphicTypeImplicitFetch.isomorphicTypeImplicit(iso, ct, jt)
    override implicit def booleanColumnType: JdbcType[Boolean] with BaseTypedType[Boolean]                                = booleanColumnType1
    override implicit def blobColumnType: JdbcType[Blob] with BaseTypedType[Blob]                                         = blobColumnType1
    override implicit def byteColumnType: JdbcType[Byte] with BaseTypedType[Byte] with NumericTypedType                   = byteColumnType1
    override implicit def byteArrayColumnType: JdbcType[Array[Byte]] with BaseTypedType[Array[Byte]]                      = byteArrayColumnType1
    override implicit def charColumnType: JdbcType[Char] with BaseTypedType[Char]                                         = charColumnType1
    override implicit def clobColumnType: JdbcType[Clob] with BaseTypedType[Clob]                                         = clobColumnType1
    override implicit def dateColumnType: JdbcType[Date] with BaseTypedType[Date]                                         = dateColumnType1
    override implicit def doubleColumnType: JdbcType[Double] with BaseTypedType[Double] with NumericTypedType             = doubleColumnType1
    override implicit def floatColumnType: JdbcType[Float] with BaseTypedType[Float] with NumericTypedType                = floatColumnType1
    override implicit def intColumnType: JdbcType[Int] with BaseTypedType[Int] with NumericTypedType                      = intColumnType1
    override implicit def longColumnType: JdbcType[Long] with BaseTypedType[Long] with NumericTypedType                   = longColumnType1
    override implicit def shortColumnType: JdbcType[Short] with BaseTypedType[Short] with NumericTypedType                = shortColumnType1
    override implicit def stringColumnType: JdbcType[String] with BaseTypedType[String]                                   = stringColumnType1
    override implicit def timeColumnType: JdbcType[Time] with BaseTypedType[Time]                                         = timeColumnType1
    override implicit def timestampColumnType: JdbcType[Timestamp] with BaseTypedType[Timestamp]                          = timestampColumnType1
    override implicit def uuidColumnType: JdbcType[UUID] with BaseTypedType[UUID]                                         = uuidColumnType1
    override implicit def bigDecimalColumnType: JdbcType[BigDecimal] with BaseTypedType[BigDecimal] with NumericTypedType = bigDecimalColumnType1
    override implicit def offsetDateTimeColumnType: JdbcType[OffsetDateTime] with BaseTypedType[OffsetDateTime]           = offsetDateTimeColumnType1
    override implicit def zonedDateTimeColumnType: JdbcType[ZonedDateTime] with BaseTypedType[ZonedDateTime]              = zonedDateTimeColumnType1
    override implicit def localTimeColumnType: JdbcType[LocalTime] with BaseTypedType[LocalTime]                          = localTimeColumnType1
    override implicit def localDateColumnType: JdbcType[LocalDate] with BaseTypedType[LocalDate]                          = localDateColumnType1
    override implicit def localDateTimeColumnType: JdbcType[LocalDateTime] with BaseTypedType[LocalDateTime]              = localDateTimeColumnType1
    override implicit def offsetTimeColumnType: JdbcType[OffsetTime] with BaseTypedType[OffsetTime]                       = offsetTimeColumnType1
    override implicit def instantColumnType: JdbcType[Instant] with BaseTypedType[Instant]                                = instantColumnType1
  }

}
