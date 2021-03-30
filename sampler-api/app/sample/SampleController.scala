package sample

import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.{Inject, Singleton}

@Singleton
class SampleController @Inject() (cc: ControllerComponents) extends AbstractController(cc) {

  def index() = Action { implicit request =>
    Ok(s"sample controller Ok : ${request.body.asText}")
  }
}
