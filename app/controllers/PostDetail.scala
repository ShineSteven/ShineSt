package controllers

import dao.PostDao

import play.api.mvc.{Action, Controller}

class PostDetail extends  Controller{
  def view(id:Long) = Action { request =>
    println(s"${request.uri},${id}")
    Ok(views.html.detail(PostDao.getPostById(id)))
  }
}