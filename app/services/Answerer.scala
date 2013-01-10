package services

import play.api.mvc._
import play.api.mvc.Results._
import scala._
import collection.mutable
import scala.Some
import play.api.Logger
import java.util.regex.Pattern

/**
 * User: cgatay
 * Date: 05/01/13
 * Time: 11:23
 */
object Answerer {
  def answerQuery(query: Option[Seq[String]]) : Result = {
    val queryContent : Seq[String]= query.getOrElse(Seq())
    val firstQueryContent = queryContent.headOption.getOrElse("")
    val OuiNonPattern = ".*OUI/NON\\)$".r
    firstQueryContent match {
      case "Quelle est ton adresse email"
        => Ok("cedric@gatay.fr")
      case OuiNonPattern()
        => Ok("OUI")
      case _ => {
        Logger.error("No match : " + query)
        NotFound
      }
    }
  }
}
