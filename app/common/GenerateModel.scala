package common

import java.sql.ResultSet
import java.util.Date

import model.Post


object GenerateModel {
  def post(rs: ResultSet) = {
    Post(rs.getInt("ID"), rs.getString("TITLE"), IOUtils.inputStreamToString(S3.getBucketObject(rs.getString("CONTENT_FILE")).getObjectContent), Option(rs.getTimestamp("create_at")),Option(rs.getTimestamp("update_at")))
  }
}
