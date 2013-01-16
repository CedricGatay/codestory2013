package controllers

import play.api.mvc._
import services.{Jajascript, ScalaSkel, Answerer}
import play.api.data._
import play.api.data.Forms._
import play.api.Logger
import play.api.libs.json.{JsObject, JsArray, Json}
import util.parsing.json.JSONArray
import models.Flight

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

  def solutions = Action(parse.tolerantJson){
    implicit request =>
      val body = request.body
      Logger.info("Current body is %s".format(body))
      val objects = body.as[Array[JsObject]]
      val solution = Jajascript.getSolution(Jajascript.parseJsonToFlights(objects))
      val map = solution._2.map {
        s => s.name
      }
      Ok(Json.toJson(Map( "gain" -> Json.toJson(solution._1), "path" -> Json.toJson(map))))
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