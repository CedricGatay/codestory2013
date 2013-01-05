package controllers

import play.api.mvc._
import scala._
import scala.Some

object Application extends Controller {

  def index = Action {
    implicit request =>
      answerQuery(request.queryString.get("q"))
  }


  def answerQuery(query: Option[Seq[String]]) : Result = {
    query match {
      case Some(Seq("Quelle est ton adresse email")) => Ok("cedric@gatay.fr")
      case _ => NotFound
    }
  }
}