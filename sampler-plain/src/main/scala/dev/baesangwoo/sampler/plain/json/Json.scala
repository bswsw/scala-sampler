package dev.baesangwoo.sampler.plain.json

sealed trait Json

final case class JsObject(get: Map[String, Json]) extends Json
final case class JsString(get: String) extends Json
final case class JsNumber(get: Double) extends Json
case object JsNull extends Json

// 타입 클래스
trait JsonWriter[A] {
  def write(value: A): Json
}

final case class Person(name: String, email: String)
final case class Animal(name: String, age: Int)

// 타입 클래스 인스턴스
object JsonWriterInstances {
  implicit val stringWriter: JsonWriter[String] =
    (value: String) => JsString(value)

  implicit val personWriter: JsonWriter[Person] =
    (value: Person) =>
      JsObject(
        Map(
          "name" -> JsString(value.name),
          "email" -> JsString(value.email)
        )
      )
}

// 타입 클래스 인터페이스
object Json {
  def toJson[A](value: A)(implicit writer: JsonWriter[A]): Json = {
    writer.write(value)
  }
}

object JsonSyntax {
  implicit class JsonWriterOps[A](value: A) {
    def toJson(implicit w: JsonWriter[A]): Json =
      w.write(value)
  }
}

object JsonApp extends App {
  import JsonSyntax._
  import JsonWriterInstances._

  val personJson = Json.toJson(Person("sangwoo", "sangwoo@gmail.com"))
//  val animalJson = Json.toJson(Animal("dog", 18))

  Person("alan", "alan@gmail.com").toJson

//  val stringWriter = implicitly[JsonWriter[String]]
//  val animalWriter = implicitly[JsonWriter[Animal]]
}
