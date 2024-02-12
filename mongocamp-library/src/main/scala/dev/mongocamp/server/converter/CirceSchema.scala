package dev.mongocamp.server.converter

import dev.mongocamp.server.model.MongoCampConfiguration
import io.circe.Decoder.Result
import io.circe.{ Decoder, Encoder, HCursor, Json }
import org.bson.types.ObjectId
import org.joda.time.DateTime
import org.mongodb.scala.Document

import java.util.Date

trait CirceSchema {

  implicit val DateFormat: Encoder[Date] with Decoder[Date] = new Encoder[Date] with Decoder[Date] {
    override def apply(a: Date): Json = Encoder.encodeString.apply(a.toInstant.toString)

    override def apply(c: HCursor): Result[Date] = Decoder.decodeString
      .map(
        s => new DateTime(s).toDate
      )
      .apply(c)
  }

  implicit val DateTimeFormat: Encoder[DateTime] with Decoder[DateTime] = new Encoder[DateTime] with Decoder[DateTime] {
    override def apply(a: DateTime): Json = Encoder.encodeString.apply(a.toInstant.toString)

    override def apply(c: HCursor): Result[DateTime] = Decoder.decodeString
      .map(
        s => new DateTime(s)
      )
      .apply(c)
  }

  implicit val ObjectIdFormat: Encoder[ObjectId] with Decoder[ObjectId] = new Encoder[ObjectId] with Decoder[ObjectId] {
    override def apply(a: ObjectId): Json = Encoder.encodeString.apply(a.toHexString)

    override def apply(c: HCursor): Result[ObjectId] = Decoder.decodeString
      .map(
        s => new ObjectId(s)
      )
      .apply(c)
  }

  implicit val MapStringAnyFormat: Encoder[Map[String, Any]] with Decoder[Map[String, Any]] = new Encoder[Map[String, Any]] with Decoder[Map[String, Any]] {
    override def apply(a: Map[String, Any]): Json = encodeMapStringAny(a)

    override def apply(c: HCursor): Result[Map[String, Any]] = Decoder.decodeMap[String, Any].apply(c)
  }

  implicit val AnyFormat: Encoder[Any] with Decoder[Any] = new Encoder[Any] with Decoder[Any] {
    override def apply(a: Any): Json = encodeAnyToJson(a)

    override def apply(c: HCursor): Result[Any] = {
      Decoder.decodeJson
        .map(
          a => decodeFromJson(a)
        )
        .apply(c)
    }
  }

  implicit val ConfigFormat: Encoder[MongoCampConfiguration] = new Encoder[MongoCampConfiguration] {
    override def apply(a: MongoCampConfiguration): Json = encodeAnyToJson(a)
  }

  def encodeMapStringAny(a: Map[String, Any]): Json = {
    Json.obj(
      a.keySet
        .map(
          key => (key, encodeAnyToJson(a(key)))
        )
        .toList: _*
    )
  }

  def decodeFromJson(json: Json): Any = {
    json match {
      case a if a.isNumber =>
        val value = a.asNumber.get
        val long  = value.toLong
        if (long.isDefined) {
          long.get
        }
        else {
          value.toDouble
        }
      case a if a.isString =>
        val string = a.asString.get
        if (string.length == 24 && string.substring(10, 11).equals("T") && string.endsWith("Z")) {
          try {
            val date = new DateTime(string)
            date
          }
          catch {
            case _: Exception => string
          }
        }
        else {
          string
        }
      case a if a.isBoolean => a.asBoolean.getOrElse(false)
      case a if a.isArray =>
        a.asArray.get.toList.map(
          e => decodeFromJson(e)
        )
      case a if a.isObject =>
        a.asObject.get.toMap.map(
          e => (e._1, decodeFromJson(e._2))
        )
      case a if a.isNull => null
      case _             => null
    }
  }

  def encodeAnyToJson(a: Any, deepth: Int = 0): Json = {
    a match {
      case s: String         => Json.fromString(s)
      case b: Boolean        => Json.fromBoolean(b)
      case l: Long           => Json.fromLong(l)
      case i: Int            => Json.fromInt(i)
      case bi: BigInt        => Json.fromBigInt(bi)
      case bd: BigDecimal    => Json.fromBigDecimal(bd)
      case d: Double         => Json.fromDoubleOrNull(d)
      case f: Float          => Json.fromFloatOrNull(f)
      case d: Date           => Encoder.encodeString.apply(d.toInstant.toString)
      case d: DateTime       => Encoder.encodeString.apply(d.toInstant.toString)
      case o: ObjectId       => Encoder.encodeString.apply(o.toHexString)
      case m: Map[String, _] => encodeMapStringAny(m)
      case seq: Seq[_] =>
        Json.arr(
          seq.map(
            e => encodeAnyToJson(e, deepth)
          ): _*
        )
      case set: Set[_] =>
        Json.arr(
          set
            .map(
              e => encodeAnyToJson(e, deepth)
            )
            .toList: _*
        )
      case product: Product =>
        val productElementNames = product.productElementNames.toList
        val fieldMap = productElementNames
          .map(
            key => {
              val index = productElementNames.indexOf(key)
              (key, product.productElement(index))
            }
          )
          .toMap
        encodeAnyToJson(fieldMap)
      case r: Document => encodeAnyToJson(r.toMap)
      case any: Any =>
        if (deepth < 256) {
          encodeAnyToJson(any, deepth + 1)
        }
        else {
          Json.Null
        }
      case _ =>
        Json.Null
    }
  }

}
