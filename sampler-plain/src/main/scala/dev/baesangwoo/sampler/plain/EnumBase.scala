package dev.baesangwoo.sampler.plain

import scala.reflect.ClassTag
import scala.reflect.runtime.universe.runtimeMirror

abstract class EnumBase[Enum: ClassTag] { self =>
  val values: Iterable[Enum] = {
    val mirror = runtimeMirror(self.getClass.getClassLoader)
    val classSymbol = mirror.classSymbol(self.getClass)

    classSymbol.toType.members
      .filter(_.isModule)
      .map(symbol => mirror.reflectModule(symbol.asModule).instance)
      .collect { case v: Enum => v }
  }

  def withNameOption(name: String): Option[Enum] = values.find(_.name == name)

  def withName(name: String): Enum =
    withNameOption(name).getOrElse(throw new IllegalStateException(s"[CRITICAL] invalid enum string name=$name"))

  implicit class RichardEnum(enum: Enum) {
    def name: String = enum.toString
  }
}
