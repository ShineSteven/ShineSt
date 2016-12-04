package shine.st.blog.controllers

import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import play.api.mvc.Controller
import shine.st.blog.services.{CategoriesService, PostService}

import scala.concurrent.ExecutionContext

/**
  * Created by shinesteven on 2015/7/28.
  */

@Singleton
class CategoriesCtrl @Inject()(action: BlogAction, actorSystem: ActorSystem)(implicit ec: ExecutionContext) extends Controller {
  def count = action {
    val categoriesCount = CategoriesService.allFirstHierarchy
    Ok(shine.st.blog.views.html.count_categories(categoriesCount))
  }

  def allCategoriesPost(categoryName: String) = action {
    val result = PostService.findAllPostByCategoriesName(categoryName)
    Ok(shine.st.blog.views.html.all_categories_post(result))
  }

  def allCategoriesToBackend = action {
    val all = CategoriesService.all
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
