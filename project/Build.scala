import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "codestory"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      "org.specs2" %% "specs2" % "1.12.3" % "test"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      resolvers ++= Seq("specs2snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
        "specs2releases"  at "http://oss.sonatype.org/content/repositories/releases")
    )

}
