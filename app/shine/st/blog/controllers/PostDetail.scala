package shine.st.blog.controllers

import shine.st.blog.dao.PostDao
import shine.st.blog.model.CustomizeContext
import play.api.mvc.{Action, Controller}
import shine.st.blog.common.ProviderContext

class PostDetail extends Controller with ProviderContext {
  def view(id: Int) = Action { implicit request =>
    val post = PostDao.queryPostById(id)
    Ok(shine.st.blog.views.html.detail(post))
  }
}