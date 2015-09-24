package common

import java.sql.ResultSet
import java.util.Date
import model.Post

/**
 * Created by shinesteven on 2015/9/18.
 */
object GenerateModel {
  def post(rs:ResultSet) = {
    Post(rs.getInt("ID"), rs.getString("TITLE"), IOUtils.readFileToString("/usr/local/web/post/" + rs.getString("CONTENT_FILE")),new Date(rs.getTimestamp("create_at").getTime))
  }
}
