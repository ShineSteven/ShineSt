package shine.st.blog.controllers

import play.api.mvc.{Action, Controller}
import shine.st.blog.common.ProviderContext
import shine.st.blog.services.{CategoriesService, PostService}

/**
  * Created by shinesteven on 2015/7/28.
  */

class CategoriesCtrl extends Controller with ProviderContext {
  def count = Action { implicit request =>
    val allCategory = CategoriesService.allFirstHierarchy()
    Ok(shine.st.blog.views.html.count_categories(allCategory))
  }

  def allCategoriesPost(categoryName: String) = Action { implicit request =>
    val post = PostService.allPostByCategoriesName(categoryName)
    post match {
      case Some(content) => Ok(shine.st.blog.views.html.all_categories_post(content))
      case None => Ok(shine.st.blog.views.html.oops("no categories post, see log to know exception"))
    }

  }

  def allCategoriesToBackend = Action { implicit request =>

    val all = CategoriesService.all()
    val first = all.filter {
      _.parentId.isEmpty
    }

    val result = all.filter(_.parentId.isDefined).groupBy {
      _.parentId.get
    }.flatMap { case (k, v) => val parentName = first.find(_.id == k).get.name
      v.map { c => (c.id, s"$parentName-${c.name}") }
    }.toList

    Ok(shine.st.blog.views.html.backend.all_categories_option(result))
  }


}
