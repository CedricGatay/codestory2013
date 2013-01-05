package controllers

import play.api.mvc._
import scala._
import scala.Some
import services.Answerer

object Application extends Controller {

  def index = Action {
    implicit request =>
      Answerer.answerQuery(request.queryString.get("q"))
  }

}