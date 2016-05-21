package shine.st.blog.controllers

import play.api.mvc.{Action, Controller}
import shine.st.blog.common.ProviderContext
import shine.st.blog.services.PostService
import play.api.mvc._
import play.api.routing._
/**
 * Created by shinesteven on 2015/7/28.
 */
class Common extends Controller{
  def javascriptRoutes = Action { implicit request =>
    Ok(
      JavaScriptReverseRouter("jsRoutes")(
        routes.javascript.Category.count
      )
    ).as("text/javascript")
  }


}
