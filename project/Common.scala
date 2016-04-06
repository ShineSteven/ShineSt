import sbt._
import Keys._
import play.sbt.PlayScala

object Dependency {
  val mysql = "mysql" % "mysql-connector-java" % "5.1.37"
  val joda = "joda-time" % "joda-time" % "2.9"
  val lang3 = "org.apache.commons" % "commons-lang3" % "3.4"
  val aws = "com.amazonaws" % "aws-java-sdk" % "1.10.27" exclude("commons-logging","commons-logging")
}

/**
  * 自定 Builder.
  * 在 SBT 定義中，project 的 scala 程式(Common.scala)，
  * 是專案目錄下 build.sbt (eg: dashboard/build.sbt 的 meta 檔。
  * 也就是說是 build.sbt 的描述檔。
  *
  * 為了讓所有子專案都可以使用到父專案的值，因此將專案的設定改到這。
  */
object DashboardBuild extends Build {

  lazy val commonSettings = Seq(
    organization := "shine.st",
    version := "1.0.1.SNAPSHOT",
    scalaVersion := "2.11.8",
    name := "web"
  )


//  lazy val batch = (project in file("batch-task")).settings(commonSettings: _*).dependsOn(common)
lazy val batch = (project in file("batch")).settings(commonSettings: _*).settings(
  libraryDependencies ++= Seq(Dependency.joda)
)

  //  lazy val root = (project in file(".")).enablePlugins(PlayScala).aggregate(common, batch, akka_compute).settings(commonSettings: _*).dependsOn(common).dependsOn(akka_compute)
  lazy val root = (project in file(".")).enablePlugins(PlayScala).aggregate(batch).settings(commonSettings: _*)


}