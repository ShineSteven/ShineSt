package shine.st.blog.dao

import java.sql.ResultSet

import shine.st.blog.common.{DateTimeUtils, DevConnectionPool}
import shine.st.blog.model.PostModel

object PostDao extends BaseDao[PostModel]{
  override val singleSql: String = "select * from post where id = ?"
  override val allSql: String = "select * from post order by id desc"

  override def generate(rs: ResultSet) = {
    //    Post(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("CONTENT_FILE"),IOUtils.inputStreamToString(S3.getBucketObject(rs.getString("CONTENT_FILE")).getObjectContent), Option(rs.getTimestamp("create_at")),Option(rs.getTimestamp("update_at")))
    PostModel(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("CONTENT_FILE"), DateTimeUtils.newDateTimeWithOpt(rs.getTimestamp("create_at")).get, DateTimeUtils.newDateTimeWithOpt(rs.getTimestamp("update_at")), rs.getInt("category_id"))
  }

  override implicit def connect = {
    println("post get connection")
    DevConnectionPool.getConnection()
  }
}
