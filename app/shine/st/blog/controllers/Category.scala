package shine.st.blog.controllers

import play.api.mvc.{Action, Controller}
import shine.st.blog.common.ProviderContext
import shine.st.blog.dao.CategoryDao
import shine.st.blog.services.{CategoryService, PostService}

/**
  * Created by shinesteven on 2015/7/28.
  */

class Category extends Controller{
  def count = Action { implicit reqeust =>
    val allCategory = CategoryService.level1All()

    println(allCategory)
    Ok(shine.st.blog.views.html.category(allCategory))
  }


}
