package shine.st.blog.services

import shine.st.blog.dao.{CategoryDao, PostDao}
import shine.st.blog.model.CategoryModel
import shine.st.blog.model.vo.CategoryVO

/**
  * Created by shinest on 2016/5/21.
  */
object CategoryService {
  def level1All() = {
    PostDao.all().groupBy(_.categoryId).map { case (k, v) =>
      findLevel1(k) match {
        case Some(c) => CategoryVO(c.id, c.name, v.size)
        case None => throw new Exception
      }
    }.toList
  }

  def findLevel1(id: Int): Option[CategoryModel] = {
    CategoryDao.queryById(id) match {
      case Some(c) => c.parentId match {
        case Some(parentId) => findLevel1(parentId)
        case None => Some(c)
      }
      case None => None
    }
  }
}
