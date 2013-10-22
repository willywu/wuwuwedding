import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "wuwuwedding"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      // dependencies defined by group, artifact, and revision
      "com.newrelic.agent.java" % "newrelic-agent" % "3.0.1"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
      // Add your own project settings here
    )

}
