package dev.baesangwoo.sampler.plain.study.chaper26

object Email {

  private val at = "@"

  def apply(user: String, domain: String) = s"$user$at$domain"

  def unapply(str: String): Option[(String, String)] = {
    val parts = str split at
    if (parts.length == 2) {
      Some(parts(0), parts(1))
    } else {
      None
    }
  }
}
