package shine.st.blog.controllers

import javax.inject._

import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}
import shine.st.blog._
import shine.st.blog.model.FormData.{LoginData, PostData}
import shine.st.blog.services.{LoginService, PostService}
import shine.st.common.aws.S3

/**
  * Created by shinest on 2016/8/21.
  */
@Singleton
class BackendCtrl @Inject()(action: BlogAction) extends Controller {
  val postForm = Form(
    mapping(
      "title" -> nonEmptyText,
      "file_name" -> nonEmptyText,
      "md_content" -> nonEmptyText,
      "html_content" -> nonEmptyText,
      "category_id" -> number,
      "brief_way" -> number,
      "brief" -> optional(text)
    )(PostData.apply)(PostData.unapply)
  )

  val loginForm = Form(
    mapping(
      "account" -> nonEmptyText,
      "password" -> nonEmptyText
    )(LoginData.apply)(LoginData.unapply)
  )

  def index = action { implicit request =>
    request.session.get("connected").map { user =>
      Ok("Hello " + user)
    }.getOrElse {
      //      Unauthorized("Oops, you are not connected")
      Redirect(shine.st.blog.controllers.routes.BackendCtrl.login)
    }

  }

  def addPost = action { implicit request =>
    if (request.session.get("connected").isDefined) {
      request.method match {
        case "GET" => Ok(shine.st.blog.views.html.backend.add_post(postForm))
        case "POST" =>
          postForm.bindFromRequest.fold(
            formWithErrors => {
              // binding failure, you retrieve the form containing errors:
              println(formWithErrors)
              BadRequest(shine.st.blog.views.html.backend.add_post(formWithErrors))
            },
            postData => {
              println(postData)
              /* binding success, you get the actual value. */

              S3.putObject(blogBucketName, s"${postData.fileName}.html", postData.htmlContent)
              S3.putObject(blogBucketName, postData.fileName, postData.htmlContent)
              val postId = PostService.insertPost(postData)

              if (postData.briefWay == 2)
                PostService.insertBrief(postId, postData)

              Redirect(shine.st.blog.controllers.routes.BackendCtrl.addPost()).flashing("post_message" -> postData.title)
            }
          )
      }
    } else
      Redirect(shine.st.blog.controllers.routes.BackendCtrl.login).flashing("login_message" -> "Unauthorized! please login")
  }

  def postList = action {
    Ok("continue...")
  }

  def login = action { implicit request =>
    request.method match {
      case "GET" =>
        request.session.get("connected").map { user =>
          Redirect(shine.st.blog.controllers.routes.BackendCtrl.addPost)
        }.getOrElse {
          //      Unauthorized("Oops, you are not connected")
          Ok(shine.st.blog.views.html.backend.login(loginForm))
        }
      case "POST" =>
        loginForm.bindFromRequest.fold(
          formWithErrors => {
            // binding failure, you retrieve the form containing errors:
            println(formWithErrors)
            BadRequest(shine.st.blog.views.html.backend.login(formWithErrors))
          },
          loginData => {
            /* binding success, you get the actual value. */

            if (LoginService.verification(loginData))
              Redirect(shine.st.blog.controllers.routes.BackendCtrl.addPost).withSession("connected" -> "Shine St")
            else
              Redirect(shine.st.blog.controllers.routes.BackendCtrl.login).flashing("login_message" -> "login failed")
          }
        )
    }
  }

}

