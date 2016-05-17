package shine.st.blog.controllers

import shine.st.blog.dao.PostDao
import play.api.mvc.{Action, Controller}
import shine.st.blog.common.ProviderContext

/**
 * Created by shinesteven on 2015/7/28.
 */
class Index extends Controller  with ProviderContext{
  def index = Action {implicit reqeust =>
    val allPost = PostDao.allPost
    Ok(shine.st.blog.views.html.index(allPost))
  }

  def template = Action {implicit reqeust =>
    Ok(shine.st.blog.views.html.template("shine/st/blog/test"))
  }
}
