package com.gu.scalaschool.class2

object Race {
  implicit val jsonWrites: JsonWrites[Race] = new JsonWrites[Race] {
    override def toJson(r: Race): JsonValue = JString(camelCase(r.toString))
  }
}

sealed trait Race

case object Human extends Race
case object Dwarf extends Race
case object Elf extends Race

object Profession {
  implicit val jsonWrites: JsonWrites[Profession] = new JsonWrites[Profession] {
    override def toJson(p: Profession): JsonValue = JString(camelCase(p.toString))
  }
}

sealed trait Profession

case object Fighter extends Profession
case object Mage extends Profession
case object Thief extends Profession
case object ProductManager extends Profession

object Item {
  implicit val jsonWrites: JsonWrites[Item] = new JsonWrites[Item] {
    override def toJson(i: Item): JsonValue = JString(camelCase(i.toString))
  }
}

sealed trait Item

case object MrPointy extends Item
case object SkybreakSpatterlight extends Item

object Player {
  implicit val jsonWrites: JsonWrites[Player] = new JsonWrites[Player] {
    override def toJson(p: Player): JsonValue = JObject(Seq(
      ("name", JSON.toJson(p.name)),
      ("race", JSON.toJson(p.race)),
      ("profession", JSON.toJson(p.profession)),
      ("goldPieces", JSON.toJson(p.goldPieces)),
      ("inventory", JSON.toJson(p.inventory))
    ))
  }
}

case class Player(
  name: String,
  race: Race,
  profession: Profession,
  goldPieces: Int,
  inventory: Seq[Item]
)
