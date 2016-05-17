package shine.st.blog.dao

import java.sql.{Connection, PreparedStatement, ResultSet}

import org.joda.time.DateTime
import shine.st.blog.common._
import shine.st.blog.model.Post

object PostDao extends Query {
  val singlePostSql = "select * from post where id = ?"
  val allPostSql = "select * from post order by id desc"

  def generate(rs: ResultSet) = {
    //    Post(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("CONTENT_FILE"),IOUtils.inputStreamToString(S3.getBucketObject(rs.getString("CONTENT_FILE")).getObjectContent), Option(rs.getTimestamp("create_at")),Option(rs.getTimestamp("update_at")))
    Post(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("CONTENT_FILE"), DateUtils.newDateTimeWithOpt(rs.getTimestamp("create_at")).get, DateUtils.newDateTimeWithOpt(rs.getTimestamp("update_at")), rs.getInt("category_id"))
  }

  def queryById(id: Int) = {
    query(singlePostSql) { ps =>
      ps.setInt(1, id)
      ps
    } { rs =>
      if (rs.next()) {
        Option(generate(rs))
      } else {
        None
      }
    }
  }

  def all() = {
    query(allPostSql)(_) { rs =>
      val result = scala.collection.mutable.ArrayBuffer.empty[Post]
      while (rs.next()) {
        result += generate(rs)
      }
      result.toList
    }
  }
}
