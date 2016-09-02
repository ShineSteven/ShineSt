package shine.st.blog.controllers

import play.api.mvc.{Action, Controller}
import play.api.routing._

/**
  * Created by shinesteven on 2015/7/28.
  */
class CommonCtrl extends Controller {
  def javascriptRoutes = Action { implicit request =>
    Ok(
      JavaScriptReverseRouter("jsRoutes")(
        routes.javascript.CategoriesCtrl.count,
        routes.javascript.CategoriesCtrl.allCategoriesToBackend
      )
    ).as("text/javascript")
  }


}
