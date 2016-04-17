import Dependency._

name := """ShineSt"""


libraryDependencies ++= Seq(
  jdbc,
//  cache,
//  ws,
  specs2 % Test
)

libraryDependencies ++= Seq(
  mysql,
  aws,
  joda,
  lang3
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator


//package
import AssemblyKeys._

assemblySettings

mainClass in assembly := Some("play.core.server.NettyServer")

fullClasspath in assembly += Attributed.blank(PlayKeys.playPackageAssets.value)