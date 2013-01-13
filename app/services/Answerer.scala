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
    val firstQueryContent = queryContent.headOption.getOrElse("").replaceAll(" ","+")
    val OuiNonPattern = ".*OUI/NON\\)$".r
    val SumPattern = "([0-9,]+)([\\+\\-\\*/])([0-9,]+)".r
    firstQueryContent match {
      case "Quelle+est+ton+adresse+email"
        => Ok("cedric@gatay.fr")
      case "Es+tu+abonne+a+la+mailing+list(OUI/NON)"
        => Ok("OUI")
      case "Es+tu+heureux+de+participer(OUI/NON)"
        => Ok("OUI")
      case "Es+tu+pret+a+recevoir+une+enonce+au+format+markdown+par+http+post(OUI/NON)"
        => Ok("OUI")
      case "As+tu+bien+recu+le+premier+enonce(OUI/NON)"
        => Ok("OUI")
      case OuiNonPattern()
        => Ok("NON")
      case SumPattern(a,o,b) => {
        val sumResult = (a.toInt + b.toInt).toString
        Ok(sumResult)
      }
      case _ => {
        Logger.error("No match : " + query)
        NotFound
      }
    }
  }
}
