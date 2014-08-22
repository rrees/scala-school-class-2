package com.gu.scalaschool.class2

trait JsonWrites[A] {
  def toJson(a: A): JsonValue
  def camelCase(s: String) = s.charAt(0).toLower + s.substring(1)
}

object JsonWrites {
  implicit val boolJsonWrites = new JsonWrites[Boolean] {
    override def toJson(a: Boolean): JsonValue = JBool(a)
  }

  implicit val intJsonWrites = new JsonWrites[Int] {
    override  def toJson(a: Int) = JNumber(a)
  }

  implicit val doubleJsonWrites: JsonWrites[Double] = new JsonWrites[Double] {
    override def toJson(d: Double): JsonValue = JNumber(d)
  }

  implicit val stringJsonWrites: JsonWrites[String] = new JsonWrites[String] {
    override def toJson(s: String): JsonValue = JString(s)
  }

  implicit def seqJsonWrites[A](implicit jsonWrites: JsonWrites[A]): JsonWrites[Seq[A]] = new JsonWrites[Seq[A]] {
    override def toJson(seq: Seq[A]): JsonValue = JArray(seq.map(el => JSON.toJson(el)))
  }

  implicit def mapJsonWrites[V](implicit jsonWrites: JsonWrites[V]): JsonWrites[Map[String, V]]
    = new JsonWrites[Map[String, V]] {
    override def toJson(m: Map[String, V]): JsonValue = JObject(m.map({case (k, v) => (k, JSON.toJson(v))}).toSeq)
  }
}
