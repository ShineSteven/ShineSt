package controllers

import dao.PostDao
import model.ProviderContext
import play.api.Play.current
import play.api.mvc.{Action, Controller}

/**
 * Created by shinesteven on 2015/7/28.
 */
class Index extends Controller  with ProviderContext{
  def index = Action {implicit reqeust =>
    Ok(views.html.index(PostDao.getAllPost()))
  }
}
