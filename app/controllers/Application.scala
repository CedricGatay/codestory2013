package controllers

import play.api.mvc._
import services.Answerer
import play.api.data._
import play.api.data.Forms._

object Application extends Controller {
  val postForm = Form("q" -> text)

  def index = Action {
    implicit request =>
      postForm.bindFromRequest.fold(
        error => {
          Answerer.answerQuery(request.queryString.get("q"))
        },
        success => {
          Answerer.answerQuery(Some(Seq(success)))
        }
      )
  }

}