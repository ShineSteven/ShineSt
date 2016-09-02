package shine.st.blog.model

import org.joda.time.DateTime

/**
  * Created by shinest on 2016/9/1.
  */
case class ManagerModel(id: Int, account: String, aliasName: String, password: String, createAt: DateTime, status: Byte)
