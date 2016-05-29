package shine.st.blog.controllers

import play.api.mvc.{Action, Controller}
import play.api.routing._

/**
  * Created by shinesteven on 2015/7/28.
  */
class Common extends Controller {
  def javascriptRoutes = Action { implicit request =>
    Ok(
      JavaScriptReverseRouter("jsRoutes")(
        routes.javascript.Categories.count
      )
    ).as("text/javascript")
  }


}
