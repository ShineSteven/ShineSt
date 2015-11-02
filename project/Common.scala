import sbt._
import Keys._

object Dependency {
  val mysql = "mysql" % "mysql-connector-java" % "5.1.37"
  val joda = "joda-time" % "joda-time" % "2.9"
  val lang3 = "org.apache.commons" % "commons-lang3" % "3.4"
  val aws = "com.amazonaws" % "aws-java-sdk" % "1.10.27" exclude("commons-logging","commons-logging")
}