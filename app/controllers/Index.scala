package controllers

import dao.PostDao
import play.api.Play.current
import play.api.mvc.{Action, Controller}

/**
 * Created by shinesteven on 2015/7/28.
 */
class Index extends Controller {
  def index = Action {

    Ok(views.html.index(PostDao.getAllPost()))
  }

}
