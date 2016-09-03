package shine.st.blog.services

import org.joda.time.DateTime
import shine.st.blog._
import shine.st.blog.dao.{BriefDao, CategoriesDao, PostDao}
import shine.st.blog.model.FormData.PostData
import shine.st.blog.model.Model.{BriefModel, PostModel}
import shine.st.blog.model.Vo.{CategoriesVo, HomePostVo, PostVo}
import shine.st.common.IOUtils
import shine.st.common.aws.S3

/**
  * Created by shinest on 2016/5/17.
  */
object PostService {
  def homePosts() = {
    PostDao.all().map {
      transferToHomePostVo(_)
    }
  }

  def queryById(id: Int) = {
    PostDao.queryById(id) match {
      case Some(post) =>
        Option(transferToPostVo(post))
      case None => None
    }
  }

  def allPostByCategoriesName(categoryName: String) = {
    val category = CategoriesDao.queryByName(categoryName)

    category.map { c => CategoriesDao.queryByParentId(c.id) } match {
      case Some(categoriesList) if !categoriesList.isEmpty =>
        val postList = categoriesList.flatMap { c => PostDao.queryByCategoryId(c.id) }.map(transferToHomePostVo).sortWith {
          _.createAt.getMillis > _.createAt.getMillis
        }
        Option(CategoriesVo(category.get.id, category.get.name, category.get.description, postList.size) -> postList)

      case None => None
    }

  }

  def insertPost(p: PostData) = {
    val postModel = PostModel(-1, p.title, s"${p.fileName}.html", new DateTime(), None, p.categoryId, p.briefWay.toByte)
    PostDao.insertWithModel(postModel)
  }

  def insertBrief(p: PostData) = {
    val sameTitlePost = PostDao.queryByTitle(p.title)
    val briefModel = BriefModel(-1, sameTitlePost.id, p.brief.get)
    BriefDao.insertWithModel(briefModel)
  }

  private def transferToPostVo(post: PostModel) = {
    val content = IOUtils.inputStreamToString(S3.getObjectContent(blogBucketName, post.contentFile))
    val category = CategoriesDao.queryById(post.categoryId).get

    PostVo(post.id, post.title, content, post.contentFile, post.createAt, post.updateAt, category)
  }

  private def transferToHomePostVo(post: PostModel) = {
    val brief = post.briefWay match {
      case 1 =>
        val content = IOUtils.inputStreamToString(S3.getObjectContent(blogBucketName, post.contentFile))
        content.split("\n").zipWithIndex.filter(_._2 <= 5).map(_._1).mkString
      case 2 =>
        BriefDao.queryByPostId(post.id).content
    }
    HomePostVo(post.id, post.title, brief, post.contentFile, post.createAt)
  }
}
