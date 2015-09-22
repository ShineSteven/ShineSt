package common

import play.api.mvc.{Request}

trait ProviderContext {
  implicit def context[A](implicit request: Request[A]): Context = {
    val test = request.getQueryString("steven")
    Context("http://shinest.cc", Map("steven" -> test.getOrElse("water")))
  }
}

case class Context(domain: String, header: Map[String, String])
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