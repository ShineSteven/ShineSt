package shine.st.blog.model

import org.joda.time.DateTime

case class Post(id: Int, title: String, contentFile: String, createAt: DateTime, updateAt: Option[DateTime], categoryId: Int)