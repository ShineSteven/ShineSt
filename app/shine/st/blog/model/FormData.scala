package shine.st.blog.model

/**
  * Created by shinest on 2016/8/24.
  */
object FormData {

  case class PostData(title: String, fileName: String, mdContent: String, htmlContent: String, categoryId: Int)

  case class LoginData(account: String, password: String)

}
