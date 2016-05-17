package shine.st.blog.services

import org.joda.time.DateTime
import shine.st.blog.common.S3
import shine.st.blog.dao.{CategoryDao, PostDao}
import shine.st.blog.model.{Category, Post}
import shine.st.blog.model.vo.PostVO
import shine.st.blog.common.IOUtils

/**
  * Created by shinest on 2016/5/17.
  */
object PostService {
  def all() = {

  }

  def queryById(id: Int) = {
    PostDao.queryById(id) match {
      case Some(post) =>
        val content = IOUtils.inputStreamToString(S3.getBucketObject(post.contentFile).getObjectContent)
        val category = CategoryDao.queryById(post.categoryId)
        Option(PostVO(post.id, post.title, content, post.createAt, post.updateAt, category))
      case None => None
    }
  }
}
