package shine.st.blog.controllers

import javax.inject.{Inject, Singleton}

import play.api.mvc.Controller
import shine.st.blog.services.PostService

@Singleton
class PostCtrl @Inject()(action: BlogAction) extends Controller {
  def detail(id: Int) = action {
    val post = PostService.queryById(id)
    Ok(shine.st.blog.views.html.post_detail(post.get))
  }
}