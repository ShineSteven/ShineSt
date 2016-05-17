package shine.st.blog.common

import com.amazonaws.services.s3.AmazonS3Client
import play.api.Play._


object S3 {
  val config = current.configuration
  val amazonS3Client = new AmazonS3Client()
  val blogBucketName = config.getString("blog.bucket.name").get

  def getBucketObject(objectName:String) = {
    amazonS3Client.getObject(blogBucketName, objectName)
  }
}
