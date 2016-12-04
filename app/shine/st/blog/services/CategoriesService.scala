package shine.st.blog.services

import shine.st.blog.dao.CategoriesDao
import shine.st.blog.handler.{Error, NoData}
import shine.st.blog.model.Model.CategoriesModel

import scala.annotation.tailrec

/**
  * Created by shinest on 2016/5/21.
  */
object CategoriesService {

  def allFirstHierarchy: List[(String, Int)] = {
    val allPost = PostService.all
    val allFirstCategories = allPost.map(_.categoryId).distinct.map(id => id -> findFirstHierarchyById(id)).toMap

    allPost.groupBy(post => allFirstCategories(post.categoryId)).map { case (k, v) => k.name -> v.size }.toList
  }

  @tailrec
  def findFirstHierarchyById(id: Int): CategoriesModel = {
    CategoriesDao.queryById(id) match {
      case Some(c) =>
        if (c.parentId.isDefined)
          findFirstHierarchyById(c.parentId.get)
        else
          c
      case None => throw Error("no categories")
    }
  }

  def all = {
    CategoriesDao.all match {
      case Some(list) => list
      case None => throw NoData("no categories")
    }
  }

}
