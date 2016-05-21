package shine.st.blog.model

import org.joda.time.DateTime

/**
  * Created by shinest on 2016/5/15.
  */
case class CategoryModel(id: Int, parentId: Option[Int], name: String, createAt: DateTime, updateAt: Option[DateTime])
