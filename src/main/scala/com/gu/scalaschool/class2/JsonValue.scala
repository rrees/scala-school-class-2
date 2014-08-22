package com.gu.scalaschool.class2

sealed trait JsonValue

case class JString(get: String) extends JsonValue

case class JNumber(get: Double) extends JsonValue

case class JBool(get: Boolean) extends JsonValue

case class JArray(get: Seq[JsonValue]) extends JsonValue

case class JObject(get: Seq[(String, JsonValue)]) extends JsonValue

object JSON {

  val doubleQuote ="\""
  /** Turn jsonValue into a compact String (no unnecessary whitespace) */
  def stringify(jsonValue: JsonValue): String = {
    jsonValue match {
      case jstring : JString => doubleQuote +jstring.get.replace(doubleQuote, "\\\"") + doubleQuote
      case jnum : JNumber => jnum.get.toString
      case jbool : JBool => jbool.get.toString
      case jarr : JArray => "[" + jarr.get.map(jv => stringify(jv)).mkString(",") + "]"
      case jobj : JObject => "{" + jobj.get.map({case (k, v) => { s"$doubleQuote$k$doubleQuote:${stringify(v)}" }}).mkString(",") + "}"
    }
  }

  def toJson[A](a: A)(implicit writes: JsonWrites[A]): JsonValue =
    writes.toJson(a)
}
