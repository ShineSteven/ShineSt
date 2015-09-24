package controllers

import dao.PostDao
import model.{ProviderContext, CustomizeContext}

import play.api.mvc.{Action, Controller}

class PostDetail extends Controller with ProviderContext {
  def view(id: Long) = Action { implicit request =>



    Ok(views.html.detail(PostDao.queryPostById(id).head))
  }
}