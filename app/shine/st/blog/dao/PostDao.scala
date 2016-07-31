package shine.st.blog.dao

import java.sql.ResultSet

import shine.st.common.DateTimeUtils
import shine.st.blog.model.PostModel

object PostDao extends BaseDao[PostModel] {
  override val singleSql: String = "select * from post where id = ?"
  override val allSql: String = "select * from post order by id desc"

  override def generate(rs: ResultSet) = {
    PostModel(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("CONTENT_FILE"), DateTimeUtils.getDateTimeOptFromDate(rs.getTimestamp("create_at")).get, DateTimeUtils.getDateTimeOptFromDate(rs.getTimestamp("update_at")), rs.getInt("category_id"))
  }

  def queryByCategoryId(categoryId: Int) = {
    list("select * from post where category_id = ?") { p =>
      p.setInt(1, categoryId)
      p
    }
  }
}
