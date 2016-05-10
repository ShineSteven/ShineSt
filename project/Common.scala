import sbt._
import Keys._
import play.sbt.PlayScala

object Dependency {
  val mysql = "mysql" % "mysql-connector-java" % "5.1.37"
  val joda = "joda-time" % "joda-time" % "2.9"
  val lang3 = "org.apache.commons" % "commons-lang3" % "3.4"
  val aws = "com.amazonaws" % "aws-java-sdk" % "1.10.27" exclude("commons-logging", "commons-logging")
//  val jsoup = "org.jsoup" % "jsoup" % "1.8.3"
val httpClient=  "org.apache.httpcomponents" % "httpclient" % "4.5.2"

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

  lazy val batchDependency = Seq(
    Dependency.joda,
    Dependency.httpClient
  )


  //  lazy val batch = (project in file("batch-task")).settings(commonSettings: _*).dependsOn(common)
  lazy val batch = (project in file("batch")).settings(commonSettings: _*).settings(
    libraryDependencies ++= batchDependency
  )

  //  lazy val root = (project in file(".")).enablePlugins(PlayScala).aggregate(common, batch, akka_compute).settings(commonSettings: _*).dependsOn(common).dependsOn(akka_compute)
  lazy val root = (project in file(".")).enablePlugins(PlayScala).aggregate(batch).settings(commonSettings: _*)


}