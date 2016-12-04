package shine.st.blog.controllers

import javax.inject.{Inject, Singleton}

import play.api.mvc.Controller
import shine.st.blog._
import shine.st.blog.services.PostService
import shine.st.common.aws.S3
import shine.st.common.{DateTimeUtils, IOUtils}

/**
  * Created by shinesteven on 2015/7/28.
  */
@Singleton
class IndexCtrl @Inject()(action: BlogAction) extends Controller {
  def index = action {
    Ok(shine.st.blog.views.html.index(PostService.homePosts))
  }


  def aboutMe(typeName: String) = action {
    config.getString(s"about.$typeName.file_name") match {
      case Some(fileName) =>
        val s3AboutMe = S3.getObject(blogBucketName, fileName)
        val meta = s3AboutMe.getObjectMetadata
        val content = IOUtils.inputStreamToString(s3AboutMe.getObjectContent)

        //FIXME config to map
        Ok(shine.st.blog.views.html.about_me(config.getString(s"about.$typeName.title").get, (content, DateTimeUtils.getDateTimeOptFromDate(meta.getLastModified))))

      case None =>
        //FIXME add reuse error page
        Ok(shine.st.blog.views.html.about_me("error", ("about me key wrod error", None)))
    }

  }


  def templateTest = action {
    Ok(shine.st.blog.views.html.template_test("shine/st/blog/test"))
  }
}
