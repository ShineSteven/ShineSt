package shine.st.blog.model

import org.joda.time.DateTime

/**
  * Created by shinest on 2016/9/2.
  */
object Model {

  case class CategoriesModel(id: Int, parentId: Option[Int], name: String, createAt: DateTime, updateAt: Option[DateTime], description: Option[String], keywords: String)

  case class BriefModel(id: Int, postId: Int, content: String)

  case class ManagerModel(id: Int, account: String, aliasName: String, password: String, createAt: DateTime, status: Byte)

  case class PostModel(id: Int, title: String, contentFile: String, createAt: DateTime, updateAt: Option[DateTime], categoryId: Int, briefWay: Byte)

}
