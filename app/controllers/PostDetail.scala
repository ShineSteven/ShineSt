package controllers

import dao.PostDao
import model.{ProviderContext, CustomizeContext}

import play.api.mvc.{Action, Controller}

class PostDetail extends Controller with ProviderContext {
  def view(id: Int) = Action { implicit request =>
    val post = PostDao.queryPostById(id)
    Ok(views.html.detail(post))
  }
}