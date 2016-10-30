import Dependency._
import sbt._
import Keys._
import play.sbt.PlayImport._
import play.sbt.PlayScala

object Dependency {
  val mysql = "mysql" % "mysql-connector-java" % "5.1.37"
  val joda = "joda-time" % "joda-time" % "2.9.4"
  val lang3 = "org.apache.commons" % "commons-lang3" % "3.4"
  val aws = "com.amazonaws" % "aws-java-sdk" % "1.10.27" exclude("commons-logging", "commons-logging")
  val httpClient = "org.apache.httpcomponents" % "httpclient" % "4.5.2"
  val config = "com.typesafe" % "config" % "1.2.1"
  val c3p0 = "com.mchange" % "c3p0" % "0.9.5.1"
  val shinestCommon = "shine.st" %% "common" % "1.0.2.SNAPSHOT"
}

/**
  * 自定 Builder.
  * 在 SBT 定義中，project 的 scala 程式(Common.scala)，
  * 是專案目錄下 build.sbt (eg: dashboard/build.sbt 的 meta 檔。
  * 也就是說是 build.sbt 的描述檔。
  *
  * 為了讓所有子專案都可以使用到父專案的值，因此將專案的設定改到這。
  */
object ShineStBuild extends Build {

  lazy val commonSettings = Seq(
    organization := "shine.st",
    scalaVersion := "2.11.8"
  )


  lazy val rootThirdPartyDependency = Seq(
    specs2 % Test,
    mysql,
    aws,
    joda,
    lang3,
    c3p0,
    shinestCommon
  )

  lazy val commonDependency = Seq(
    jdbc,
    cache,
    ws,
    "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
    "org.scala-lang" % "scala-reflect" % "2.11.8",
    "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.4",
    "org.scalatest" %% "scalatest" % "2.2.4" % "test"
  )


  lazy val root = (project in file(".")).enablePlugins(PlayScala).settings(commonSettings: _*).settings(libraryDependencies ++= (commonDependency ++ rootThirdPartyDependency))
}
