package shine.st.blog.dao

import java.sql.ResultSet

import shine.st.blog.common.{DateUtils, NumberUtils}
import shine.st.blog.model.Category

/**
  * Created by shinest on 2016/5/17.
  */
object CategoryDao extends Query {
  val singlePostSql = "select * from category where id = ?"

  def generate(rs: ResultSet) = {
    Category(rs.getInt("ID"), NumberUtils.toInt(rs.getString("parent_id")), rs.getString("name"), DateUtils.newDateTimeWithOpt(rs.getTimestamp("create_at")).get, DateUtils.newDateTimeWithOpt(rs.getTimestamp("update_at")))
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
}
