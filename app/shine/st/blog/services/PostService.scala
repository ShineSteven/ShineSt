package shine.st.blog.services

import org.joda.time.DateTime
import shine.st.blog.common.S3
import shine.st.blog.dao.{CategoryDao, PostDao}
import shine.st.blog.model.{CategoryModel, PostModel}
import shine.st.blog.model.vo.PostVO
import shine.st.blog.common.IOUtils

/**
  * Created by shinest on 2016/5/17.
  */
object PostService {
  def all() = {
    PostDao.all().map { post => transfer(post) }
  }

  def queryById(id: Int) = {
    PostDao.queryById(id) match {
      case Some(post) =>
        Option(transfer(post))
      case None => None
    }
  }

  private def transfer(post: PostModel) = {
    val content = IOUtils.inputStreamToString(S3.getBucketObject(post.contentFile).getObjectContent)
    val category = CategoryDao.queryById(post.categoryId).get
    PostVO(post.id, post.title, content,post.contentFile, post.createAt, post.updateAt, category)
  }
}
