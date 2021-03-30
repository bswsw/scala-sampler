package dev.baesangwoo.sampler.domain

import slick.jdbc.JdbcBackend.Database
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global

object DomainApp extends App with DishTable with RecipeTable with UserTable {

  println("DomainApp Start!")

  val db = Database.forConfig("db")
  val dishesWithRecipeWithOptionalUser = for {
    ((d, r), u) <- dishes join recipes on (_.recipeId === _.id) joinLeft users on (_._1.userId === _.id)
  } yield (d, r, u)

  val dishesWithRecipeWithUser = for {
    ((d, r), u) <- dishes join recipes on (_.recipeId === _.id) join users on (_._1.userId === _.id)
  } yield (d, r, u)

  def query1(dishKey: Option[String], recipeKey: Option[String], payAccountId: Option[Long]) = {
    dishesWithRecipeWithOptionalUser
      .filterOpt(dishKey)(_._1.key === _)
      .filterOpt(recipeKey)(_._2.key === _)
      .filterOpt(payAccountId)(_._3.map(_.payAccountId) === _)
      .sortBy(_._1.id.desc)
      .drop(0)
      .take(20)
      .result
  }

  def query2(dishKey: Option[String], recipeKey: Option[String], payAccountId: Option[Long]) = {
    dishesWithRecipeWithUser
      .filterOpt(dishKey)(_._1.key === _)
      .filterOpt(recipeKey)(_._2.key === _)
      .filterOpt(payAccountId)(_._3.payAccountId === _)
      .sortBy(_._1.id.desc)
      .drop(0)
      .take(20)
      .result
  }

  db.run(query2(Some("ASdasdad"), None, None)).value
  println("1=1=1=1=1=1=11=1=1=1=1=1=1=")

  db.run(query2(None, Some("ASdasdad"), None)).value
  println("2=-2=2=2=2=2=2=2=22=2=2=2=2=2=2=2")

  db.run(query2(None, None, Some(12313))).value
  println("3=3=3=3=3-3-3=3=3=3=3=3=33")

  sql"""asdasd"""
}
