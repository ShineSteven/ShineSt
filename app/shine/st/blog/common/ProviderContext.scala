package shine.st.blog.common

import play.api.mvc.Request

trait ProviderContext {
  implicit def customizeContext[A](implicit request: Request[A]): CustomizeContext = {
    CustomizeContext("shinest.cc", Map("owner" -> "Shine Steven"))
  }
}

case class CustomizeContext(domain: String, header: Map[String, String])

/* use
controller extends ProviderContext
action implicit request
view (implicit customizeContext: CustomizeContext)
 */

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