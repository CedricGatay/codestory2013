package controllers

import play.api.mvc._
import services.Answerer
import play.api.data._
import play.api.data.Forms._
import play.api.Logger

object Application extends Controller {

  def index = Action {
    implicit request =>
      request.method match {
        case "GET" => Answerer.answerQuery(request.queryString.get("q"))
        case "POST" => {
          val encoded = request.body.asFormUrlEncoded
          Logger.info("Posted the following data : %s".format(encoded))
          Answerer.answerQuery(encoded.getOrElse(Map()).get("q"))
          Status(201)
        }
      }
  }

  def enonce(value : String) = Action{
    implicit request =>
      request.method match {
        case "POST" => {
          val encoded = request.body
          Logger.info("Posted the following data : %s".format(encoded))
          Status(201)
        }
      }
  }

}