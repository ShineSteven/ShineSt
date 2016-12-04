package shine.st

import org.slf4j.LoggerFactory
import play.api.Play._

/**
  * Created by shinest on 2016/7/31.
  */
package object blog {
  val config = current.configuration
  val blogBucketName = config.getString("blog.bucket.name").get
  val logger = LoggerFactory.getLogger("shine.st.blog")
}
