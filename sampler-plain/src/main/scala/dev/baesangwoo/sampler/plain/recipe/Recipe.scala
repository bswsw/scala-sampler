package dev.baesangwoo.sampler.plain.recipe

import com.google.inject.multibindings.MapBinder
import com.google.inject.{AbstractModule, Guice}
import dev.baesangwoo.sampler.plain.EnumBase

import javax.inject.{Inject, Singleton}
import scala.jdk.CollectionConverters.MapHasAsScala

// common
trait Trigger

trait Action

trait Recipe {
  type T <: Trigger
  type A <: Action

  def run(param: T): A
}

// recipe name
sealed abstract class RecipeName

object RecipeName extends EnumBase[RecipeName] {
  case object FIRST_RECIPE extends RecipeName
  case object SECOND_RECIPE extends RecipeName
}

// first recipe
case class FirstRecipeTrigger(name: String) extends Trigger
object FirstRecipeTrigger {
  def apply(params: Seq[String]): FirstRecipeTrigger = {
    println(s"FirstRecipeTrigger.apply : $params")

    params match {
      case firstP :: _ => FirstRecipeTrigger(firstP)
      case _           => FirstRecipeTrigger("None name")
    }
  }
}

case class FirstRecipeAction(result: String) extends Action

class FirstRecipe @Inject() (service: LogService) extends Recipe {
  override type T = FirstRecipeTrigger
  override type A = FirstRecipeAction

  override def run(param: FirstRecipeTrigger): FirstRecipeAction = {
    service.log(param.name)
    FirstRecipeAction(s"FirstRecipeAction : ${param.name}")
  }
}

// second recipe
case class SecondRecipeTrigger(name: String) extends Trigger
object SecondRecipeTrigger {
  def apply(params: Seq[String]): SecondRecipeTrigger = {
    println(s"SecondRecipeTrigger.apply : $params")

    params match {
      case firstP :: _ => SecondRecipeTrigger(firstP)
      case _           => SecondRecipeTrigger("None name")
    }
  }
}

case class SecondRecipeAction(result: String) extends Action

class SecondRecipe extends Recipe {
  override type T = SecondRecipeTrigger
  override type A = SecondRecipeAction

  override def run(param: SecondRecipeTrigger): SecondRecipeAction = {
    println(param.name)
    SecondRecipeAction(s"SecondRecipeAction : ${param.name}")
  }
}

// guice module
class RecipeModule extends AbstractModule {

  val recipeMap: Map[RecipeName, Class[_ <: Recipe]] = Map(
    (RecipeName.FIRST_RECIPE: RecipeName) -> classOf[FirstRecipe],
    (RecipeName.SECOND_RECIPE: RecipeName) -> classOf[SecondRecipe]
  )

  override def configure(): Unit = {
//    val recipeBinder = MapBinder.newMapBinder(binder(), classOf[RecipeName], classOf[Recipe])
//    recipeBinder.addBinding(RecipeName.FIRST_RECIPE).to(classOf[FirstRecipe])
//    recipeBinder.addBinding(RecipeName.SECOND_RECIPE).to(classOf[SecondRecipe])

    bindRecipes(recipeMap)
  }

  private def bindRecipes(recipeMap: Map[RecipeName, Class[_ <: Recipe]]) = {
    val recipeBinder = MapBinder.newMapBinder(binder(), classOf[RecipeName], classOf[Recipe])

    recipeMap.map { case (name, recipe) =>
      recipeBinder.addBinding(name).to(recipe)
    }
  }
}

@Singleton
class RecipeRunner @Inject() (private val recipes: java.util.Map[RecipeName, Recipe]) {

  def run(args: Seq[String]): Unit = {
    (for {
      jobOptions <- buildJobOptions(args)
      jobName = jobOptions._1
      jobParameterList = jobOptions._2
      recipe <- recipes.asScala.get(jobName).toRight("job find fail")
    } yield (recipe, jobParameterList)) match {
      case Right((recipe, jobParam)) =>
        recipe match {
          case r: FirstRecipe  => r.run(FirstRecipeTrigger(jobParam))
          case r: SecondRecipe => r.run(SecondRecipeTrigger(jobParam))
        }

      case Left(value) => throw new RuntimeException(value)
    }
  }

  private def buildJobOptions(args: Seq[String]): Either[String, (RecipeName, Seq[String])] = {
    args match {
      case jobName :: jobParameters =>
        RecipeName
          .withNameOption(jobName)
          .map(recipeName => (recipeName, jobParameters))
          .toRight("invalid recipe name")

      case _ => Left("invalid job params")
    }
  }
}

@Singleton
class LogService {
  def log(message: String): Unit = {
    println(message)
  }
}

object RecipeApp extends App {
  val injector = Guice.createInjector(new RecipeModule)
  val runner = injector.getInstance(classOf[RecipeRunner])

  runner.run(Seq("FIRST_RECIPE", "recipe trigger name 1"))
  runner.run(Seq("SECOND_RECIPE", "recipe trigger name 2"))
//  runner.run("dish1")
}
