package controllers

import play.api.mvc._
import services.{ScalaSkel, Answerer}
import play.api.data._
import play.api.data.Forms._
import play.api.Logger
import play.api.libs.json.Json
import util.parsing.json.JSONArray

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

  def enonce(value : String) = Action(parse.tolerantText){
    implicit request =>
      request.method match {
        case "POST" => {
          val encoded = request.body
          Logger.info("Posted the following data : %s".format(encoded))
          Status(201)
        }
      }
  }

  def scalaskel(value : Int) = Action{
    implicit request =>
      if (value < 0 || value > 100){
        Status(403)
      }else{
        val change = ScalaSkel.gimmeChange(value)
        val preparedMap = change.map(combination => combination.map(innerTuple => (innerTuple._1.name.toLowerCase, innerTuple._2)))
        Ok(Json.toJson(preparedMap))
      }
  }

}