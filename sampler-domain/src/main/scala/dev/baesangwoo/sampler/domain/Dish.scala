package dev.baesangwoo.sampler.domain

import slick.jdbc.MySQLProfile.api._

case class Dish(id: Option[Long], recipeId: Long, userId: Option[Long], key: String)

class Dishes(tag: Tag) extends Table[Dish](tag, "dishes") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def recipeId = column[Long]("recipe_id")
  def userId = column[Long]("user_id")
  def key = column[String]("key")

  override def * = (id.?, recipeId, userId.?, key) <> (Dish.tupled, Dish.unapply)
}

trait DishTable {
  val dishes = TableQuery[Dishes]
}
