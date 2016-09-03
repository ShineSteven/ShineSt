package shine.st.blog.model

import org.joda.time.DateTime
import shine.st.blog.model.Model.CategoriesModel

/**
  * Created by shinest on 2016/9/2.
  */
object Vo {

  case class CategoriesVo(id: Int, name: String, description: Option[String], count: Int)

  case class PostVo(id: Int, title: String, content: String, staticPageName: String, createAt: DateTime, updateAt: Option[DateTime], category: CategoriesModel)

  case class HomePostVo(id: Int, title: String, brief: String, staticPageName: String, createAt: DateTime)

}
