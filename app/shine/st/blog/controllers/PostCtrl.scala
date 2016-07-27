package shine.st.blog.controllers

import play.api.mvc.{Action, Controller}
import shine.st.blog.common.ProviderContext
import shine.st.blog.services.PostService

class PostCtrl extends Controller with ProviderContext {
  def detail(id: Int) = Action { implicit request =>
    val post = PostService.queryById(id)
    Ok(shine.st.blog.views.html.detail(post.get))
  }
}