package dev.baesangwoo.sampler.autoconfigure

import com.typesafe.config.ConfigFactory

class UserProperties {

  private val config = ConfigFactory.defaultApplication()
  private val userConfig = config.getConfig("user")

  val name: String = userConfig.getString("name")
  val email: String = userConfig.getString("email")
  val phone: String = userConfig.getString("phone")


  override def toString = s"UserProperties($name, $email, $phone)"
}
