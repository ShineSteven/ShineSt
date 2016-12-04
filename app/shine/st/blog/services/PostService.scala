package shine.st.blog.services

import org.joda.time.DateTime
import shine.st.blog._
import shine.st.blog.dao.{BriefDao, CategoriesDao, PostDao}
import shine.st.blog.handler.NoData
import shine.st.blog.model.FormData.PostData
import shine.st.blog.model.Model.{BriefModel, CategoriesModel, PostModel}
import shine.st.blog.model.Vo.{CategoriesVo, HomePostVo, PostVo}
import shine.st.common.IOUtils
import shine.st.common.aws.S3

/**
  * Created by shinest on 2016/5/17.
  */
object PostService {
  def homePosts() = {
    PostDao.all.get.map(transferToHomePostVo)
  }

  def queryById(id: Int) = {
    PostDao.queryById(id) match {
      case Some(post) =>
        Option(transferToPostVo(post))
      case None => None
    }
  }

  def findAllPostByCategoriesName(categoryName: String) = {
    val opt = CategoriesDao.queryByName(categoryName)

    opt match {
      case Some(categories) =>
        val categoriesList = categories :: subCategories(categories.id)
        //        println(categoriesList)
        val postList = categoriesList.flatMap { c =>
          PostDao.queryByCategoryId(c.id).map(_.map(transferToHomePostVo))
        }.flatMap(el => el).sortWith {
          _.createAt.getMillis > _.createAt.getMillis
        }

        CategoriesVo(categories.id, categories.name, categories.description, categoriesList.map(_.keywords), postList)

      case None => throw NoData(s"nodata with categories name: $categoryName")
    }

  }

  def subCategories(id: Int): List[CategoriesModel] = {
    CategoriesDao.queryByParentId(id) match {
      case Some(c) => c.flatMap(ca => ca :: subCategories(ca.id))
      case None => Nil
    }
  }

  def insertPost(p: PostData): Int = {
    val postModel = PostModel(-1, p.title, s"${p.fileName}.html", new DateTime(), None, p.categoryId, p.briefWay.toByte)
    PostDao.insertWithModel(postModel)
  }

  def insertBrief(postId: Int, postData: PostData) = {
    val briefModel = BriefModel(-1, postId, postData.brief.get)
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
        BriefDao.queryByPostId(post.id).map(_.content).getOrElse("empty.....")

    }
    HomePostVo(post.id, post.title, brief, post.contentFile, post.createAt)
  }

  def all = {
    PostDao.all match {
      case Some(list) => list
      case None => throw NoData("no posts")
    }
  }
}
