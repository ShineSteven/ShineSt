package shine.st.blog.controllers

import play.api.mvc.{Action, Controller}
import shine.st.blog.common.ProviderContext
import shine.st.blog.services.{CategoriesService, PostService}

/**
  * Created by shinesteven on 2015/7/28.
  */

class Categories extends Controller with ProviderContext {
  def count = Action { implicit reqeust =>
    val allCategory = CategoriesService.allFirstHierarchy()
    Ok(shine.st.blog.views.html.count_categories(allCategory))
  }

  def allPostByCategory(categoryName: String) = Action { implicit request =>
    val post = PostService.allPostByCategory(categoryName)
    Ok(shine.st.blog.views.html.all_categories(post.get))
  }


}
