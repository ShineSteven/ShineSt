package controllers




import com.google.inject.Singleton
import com.typesafe.config.ConfigFactory
import dao.PostDao
import model.ProviderContext
import play.api.mvc.{Action, Controller}

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
