package shine.st.blog.handler

import javax.inject.{Inject, Provider, Singleton}

import play.api.http.DefaultHttpErrorHandler
import play.api.http.Status.{BAD_REQUEST, FORBIDDEN, NOT_FOUND}
import play.api.mvc.Results.{Forbidden, InternalServerError, _}
import play.api.mvc.{RequestHeader, Result}
import play.api.routing.Router
import play.api.{Configuration, Environment, OptionalSourceMapper, UsefulException}

import scala.concurrent.Future

/**
  * Created by shinest on 04/12/2016.
  */

@Singleton
class ErrorHandler @Inject()(env: Environment,
                             config: Configuration,
                             sourceMapper: OptionalSourceMapper,
                             router: Provider[Router]
                            ) extends DefaultHttpErrorHandler(env, config, sourceMapper, router) {

  override def onProdServerError(request: RequestHeader, exception: UsefulException) = {
    Future.successful(
      exception.cause match {
        case NoData(message) => BadRequest(shine.st.blog.views.html.oops(message))
        case Error(message) => BadRequest(shine.st.blog.views.html.oops(message))
        case _ =>
          InternalServerError("A server error occurred.... " + exception.getMessage)
      }
    )
  }

  //  override protected def onDevServerError(request: RequestHeader, exception: UsefulException): Future[Result] =
  //    Future.successful {
  //      InternalServerError("A server error occurred_dev: " + exception.getMessage)
  //    }

  override def onForbidden(request: RequestHeader, message: String) = {
    Future.successful(
      Forbidden("You're not allowed to access this resource.")
    )
  }

  override def onClientError(request: RequestHeader, statusCode: Int, message: String): Future[Result] = {
    println("client error occur")
    statusCode match {
      case BAD_REQUEST => onBadRequest(request, message)
      case FORBIDDEN => onForbidden(request, message)
      case NOT_FOUND => onNotFound(request, message)
      case clientError if statusCode >= 400 && statusCode < 500 => onOtherClientError(request, statusCode, message)
      case nonClientError =>
        throw new IllegalArgumentException(s"onClientError invoked with non client error status code $statusCode: $message")
    }
  }
}