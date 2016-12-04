package shine.st.blog.controllers

import javax.inject.Inject

import play.api.http.HttpVerbs
import play.api.i18n.{Messages, MessagesApi}
import play.api.mvc.{ActionBuilder, Request, Result, WrappedRequest}
import shine.st.blog._

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by shinest on 04/12/2016.
  */

class BlogRequest[A](request: Request[A], val messages: Messages) extends WrappedRequest(request)

class BlogAction @Inject()(messagesApi: MessagesApi)
                          (implicit ec: ExecutionContext
                          ) extends ActionBuilder[BlogRequest] with HttpVerbs {

  type requestBlock[A] = BlogRequest[A] => Future[Result]

  override def invokeBlock[A](request: Request[A],
                              block: requestBlock[A]): Future[Result] = {

    if (logger.isTraceEnabled()) {
      logger.trace(s"invokeBlock: request = $request")
    }

    val messages = messagesApi.preferred(request)

    logger.debug(messages.toString)

    block(new BlogRequest(request, messages))

    //    future.map{result =>
    //      result.as(ContentTypes.JSON)
    //    }
  }

}
