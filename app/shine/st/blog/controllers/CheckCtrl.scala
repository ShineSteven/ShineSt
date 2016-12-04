package shine.st.blog.controllers

import javax.inject.Inject

import com.google.inject.Singleton
import play.api.mvc.{Action, Controller}

@Singleton
class CheckCtrl @Inject()(action: BlogAction, configuration: play.api.Configuration) extends Controller {

  var currentCount = 0

  def checkEnv() = action {
    currentCount += 1
    Ok(s"current env ${configuration.getString("env.debug").get} : $currentCount")
  }
}
