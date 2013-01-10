package services

import play.api.mvc._
import play.api.mvc.Results._
import scala._
import collection.mutable
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
      case Some(Seq("Es tu abonne a la mailing list(OUI/NON)")) => Ok("OUI")
      case Some(Seq("Es tu heureux de participer(OUI/NON)")) => Ok("OUI")
      case _ => {
        Logger.error("No match : " + query)
        NotFound
      }
    }
  }
}
