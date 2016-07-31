package shine.st.blog.services

import shine.st.blog.dao.{CategoriesDao, PostDao}
import shine.st.blog.model.CategoriesModel
import shine.st.blog.model.vo.CategoriesVO

/**
  * Created by shinest on 2016/5/21.
  */
object CategoriesService {
  def allFirstHierarchy() = {
    PostDao.all().groupBy(_.categoryId).map { case (k, v) =>
      findFirstHierarchyById(k) match {
        case Some(c) => CategoriesVO(c.id, c.name, c.description, v.size)
        case None => throw new Exception
      }
    }.toList
  }

  def findFirstHierarchyById(id: Int): Option[CategoriesModel] = {
    CategoriesDao.queryById(id) match {
      case Some(c) => c.parentId match {
        case Some(parentId) => findFirstHierarchyById(parentId)
        case None => Some(c)
      }
      case None => None
    }
  }

}
