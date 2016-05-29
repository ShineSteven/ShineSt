package shine.st.blog.services

import shine.st.blog.common.{IOUtils, S3}
import shine.st.blog.dao.{CategoriesDao, PostDao}
import shine.st.blog.model.PostModel
import shine.st.blog.model.vo.{CategoriesVO, PostVO}

/**
  * Created by shinest on 2016/5/17.
  */
object PostService {
  def all() = {
    PostDao.all().map {
      transfer(_)
    }
  }

  def queryById(id: Int) = {
    PostDao.queryById(id) match {
      case Some(post) =>
        Option(transfer(post))
      case None => None
    }
  }

  def allPostByCategory(categoryName: String) = {
    val category = CategoriesDao.queryByName(categoryName)

    category.flatMap { c => CategoriesService.findSecondHierarchyByName(c.id) }.flatMap { list =>
      val postList = list.flatMap(c => PostDao.queryByCategoryId(c.id)).map {
        transfer(_)
      }.sortWith(_.createAt.getMillis > _.createAt.getMillis)
      Option(CategoriesVO(category.get.id, category.get.name, category.get.description, postList.size) -> postList)
    }
  }

  private def transfer(post: PostModel) = {
    val content = IOUtils.inputStreamToString(S3.getBucketObject(post.contentFile).getObjectContent)
    val category = CategoriesDao.queryById(post.categoryId).get
    PostVO(post.id, post.title, content, post.contentFile, post.createAt, post.updateAt, category)
  }
}
