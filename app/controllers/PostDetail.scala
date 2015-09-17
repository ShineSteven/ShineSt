package controllers

import common.{ProviderContext}
import dao.PostDao

import play.api.mvc.{Action, Controller}

class PostDetail extends Controller with ProviderContext {
  def view(id: Long) = Action { implicit request =>
    //    println(s"${request.uri},${id}")
    Ok(views.html.detail(PostDao.queryPostById(id).head))
  }
}