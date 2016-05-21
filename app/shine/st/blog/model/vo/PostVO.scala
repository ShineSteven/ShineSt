package shine.st.blog.model.vo

import org.joda.time.DateTime
import shine.st.blog.model.CategoryModel

/**
  * Created by shinest on 2016/5/15.
  */
case class PostVO(id: Int, title: String, content: String, staticPageName:String, createAt: DateTime, updateAt: Option[DateTime], category: CategoryModel)
