package shine.st.blog.dao

import java.sql.ResultSet

import shine.st.blog.common.{DateTimeUtils, NumberUtils}
import shine.st.blog.model.Category

/**
  * Created by shinest on 2016/5/17.
  */
object CategoryDao extends BaseDao[Category] {
  override val singleSql: String = "select * from category where id = ?"
  override val allSql: String = "select * from category order by id desc"

  override def generate(rs: ResultSet) = {
    Category(rs.getInt("ID"), NumberUtils.toInt(rs.getString("parent_id")), rs.getString("name"), DateTimeUtils.newDateTimeWithOpt(rs.getTimestamp("create_at")).get, DateTimeUtils.newDateTimeWithOpt(rs.getTimestamp("update_at")))
  }
}
