package services

import play.api.mvc._
import play.api.mvc.Results._
import scala._
import scala.Some
import play.api.Logger

/**
 * User: cgatay
 * Date: 05/01/13
 * Time: 11:23
 */
object Answerer {
  def answerQuery(query: Option[Seq[String]]) : Result = {
    query match {
      case Some(Seq("Quelle est ton adresse email")) => Ok("cedric@gatay.fr")
      case _ => {
        Logger.error("No match : " + query)
        NotFound
      }
    }
  }
}
