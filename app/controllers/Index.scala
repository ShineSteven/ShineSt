package controllers

import dao.PostDao
import model.ProviderContext
import play.api.mvc.{Action, Controller}

/**
 * Created by shinesteven on 2015/7/28.
 */
class Index extends Controller  with ProviderContext{
  def index = Action {implicit reqeust =>
    val allPost = PostDao.allPost
    Ok(views.html.index(allPost))
  }
}
