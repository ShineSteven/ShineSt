package shine.st.blog.controllers

import javax.inject.{Inject, Singleton}

import play.api.mvc.Controller
import play.api.routing._

/**
  * Created by shinesteven on 2015/7/28.
  */
@Singleton
class CommonCtrl @Inject()(action: BlogAction) extends Controller {
  def javascriptRoutes = action { implicit request =>
    Ok(
      JavaScriptReverseRouter("jsRoutes")(
        routes.javascript.CategoriesCtrl.count,
        routes.javascript.CategoriesCtrl.allCategoriesToBackend
      )
    ).as("text/javascript")
  }


}
