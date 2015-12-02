package model

import play.api.mvc.Request
import java.util.Date
import common.DateUtils

trait ProviderContext {
  implicit def customizeContext[A](implicit request: Request[A]): CustomizeContext = {
    val test = request.getQueryString("steven")
    CustomizeContext("http://shinest.cc", Map("steven" -> test.getOrElse("water")))
  }
}

case class CustomizeContext(domain: String, header: Map[String, String])

/*

trait ProviderContext {

  implicit def header[A](implicit request: Request[A]) : Header = {
    val menu = Seq(MenuItem("/home", "Home"),
      MenuItem("/about", "About"),
      MenuItem("/contact", "Contact"))
//    val user = request.session.get("user")
    Header(menu, Some("Steven"))
  }
}


case class Header(menu: Seq[MenuItem], user: Option[String])
case class MenuItem(url: String, name: String)
*/