package shine.st.blog.controllers

import play.api.mvc.{Action, Controller}
import shine.st.blog.common.ProviderContext
import shine.st.blog.services.PostService

/**
 * Created by shinesteven on 2015/7/28.
 */
class Index extends Controller  with ProviderContext{
  def index = Action {implicit reqeust =>
    val allPost = PostService.all
    Ok(shine.st.blog.views.html.index(allPost))
  }

  def template = Action {implicit reqeust =>
    routes.javascript.Category
    Ok(shine.st.blog.views.html.template("shine/st/blog/test"))
  }
}
