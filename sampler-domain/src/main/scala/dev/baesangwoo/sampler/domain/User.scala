package dev.baesangwoo.sampler.domain

import slick.jdbc.MySQLProfile.api._

case class User(id: Option[Long], payAccountId: Long)

class Users(tag: Tag) extends Table[User](tag, "users") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def payAccountId = column[Long]("pay_account_id")

  override def * = (id.?, payAccountId) <> (User.tupled, User.unapply)
}

trait UserTable {
  val users = TableQuery[Users]
}
