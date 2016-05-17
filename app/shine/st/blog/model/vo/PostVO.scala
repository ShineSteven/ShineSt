package shine.st.blog.model.vo

import org.joda.time.DateTime
import shine.st.blog.model.Category

/**
  * Created by shinest on 2016/5/15.
  */
case class PostVO(id: Int, title: String,content: String, createAt: DateTime, updateAt: Option[DateTime],category :Option[Category])
