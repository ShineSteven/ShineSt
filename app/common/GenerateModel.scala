package common

import java.sql.ResultSet
import java.util.Date
import model.Post

/**
 * Created by shinesteven on 2015/9/18.
 */
object GenerateModel {
  def post(rs: ResultSet) = {
    Post(rs.getInt("ID"), rs.getString("TITLE"), IOUtils.inputStreamToString(S3.getBucketObject(rs.getString("CONTENT_FILE")).getObjectContent), new Date(rs.getTimestamp("create_at").getTime))
  }
}
