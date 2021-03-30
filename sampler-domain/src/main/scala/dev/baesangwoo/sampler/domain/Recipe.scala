package dev.baesangwoo.sampler.domain

import slick.jdbc.MySQLProfile.api._

case class Recipe(id: Option[Long], key: String)

class Recipes(tag: Tag) extends Table[Recipe](tag, "recipes") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def key = column[String]("key")

  override def * = (id.?, key) <> (Recipe.tupled, Recipe.unapply)
}

trait RecipeTable {
  val recipes = TableQuery[Recipes]
}
