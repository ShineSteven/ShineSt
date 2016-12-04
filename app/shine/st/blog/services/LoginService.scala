package shine.st.blog.services

import shine.st.blog.dao.ManagerDao
import shine.st.blog.handler.Error
import shine.st.blog.model.FormData.LoginData
import shine.st.common.HashUtils

/**
  * Created by shinest on 2016/9/2.
  */
object LoginService {
  def verification(userData: LoginData): Boolean = {
    val allManager = ManagerDao.all

    allManager match {
      case Some(all) =>
        val passwordHex = HashUtils.sha256(userData.account + userData.password)
        all.find(_.password == passwordHex).isDefined
      case None => throw Error("Manager no data")
    }
  }
}
