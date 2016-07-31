package shine.st.blog.controllers

import com.google.inject.Singleton
import com.typesafe.config.ConfigFactory
import play.api.mvc.{Action, Controller}
import shine.st.blog.common.ProviderContext

@Singleton
class CheckCtrl extends Controller with ProviderContext {
  val config = ConfigFactory.load()

  var currentCount = 0

  def checkEnv() = Action {
    currentCount += 1
    Ok(s"current env ${config.getString("env.debug")} : $currentCount")
  }
}
