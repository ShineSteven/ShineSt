package shine.st.blog.controllers




import com.google.inject.Singleton
import com.typesafe.config.ConfigFactory
import shine.st.blog.dao.PostDao
import play.api.mvc.{Action, Controller}
import shine.st.blog.common.ProviderContext

@Singleton
class CheckEnv extends Controller  with ProviderContext {
  val config = ConfigFactory.load()

  var currentCount = 0

  def check() = Action {
    currentCount += 1
    Ok(s"current env ${config.getString("env.debug")} : $currentCount")
  }

  def show(id: Int) = Action { implicit request =>
    Ok(s"current env ${config.getString("env.debug")} : $currentCount")
  }

}
