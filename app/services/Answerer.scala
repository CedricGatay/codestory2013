package services

import play.api.mvc._
import play.api.mvc.Results._
import scala._
import collection.mutable
import scala.Some
import play.api.Logger
import java.util.regex.Pattern
import groovy.lang.GroovyShell

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
      case v => {
        try {
          val evaluate = new GroovyShell().evaluate(v.replaceAll(",", ".")).toString
          val formattedResult = evaluate.replaceAll("\\.", ",")
          Logger.info("Got %s, formatted to %s".format(evaluate, formattedResult))
          Ok(formattedResult)
        }catch {
          case _: Throwable =>
            Logger.error("No match : " + query)
            NotFound
        }

      }
    }
  }
}
