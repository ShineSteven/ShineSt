package shine.st.blog.dao

import java.sql.{PreparedStatement, ResultSet}

import shine.st.blog.model.Model.PostModel
import shine.st.common.DateTimeUtils

object PostDao extends BaseDao[PostModel] {
  override val singleSql: String = "select * from post where id = ?"
  override val allSql: String = "select * from post order by id desc"

  override def generate(rs: ResultSet) = {
    PostModel(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("CONTENT_FILE"), DateTimeUtils.getDateTimeOptFromDate(rs.getTimestamp("create_at")).get, DateTimeUtils.getDateTimeOptFromDate(rs.getTimestamp("update_at")), rs.getInt("category_id"), rs.getByte("brief_way"))
  }

  def queryByCategoryId(categoryId: Int) = {
    list("select * from post where category_id = ?") { p =>
      p.setInt(1, categoryId)
      p
    }
  }

  def queryByTitle(title: String) = {
    list("select * from post where title = ? order by id desc") { p =>
      p.setString(1, title)
      p
    } head
  }

  override protected val insertModelSql: String = "insert into post(title,content_file,create_at,category_id,brief_way) values(?,?,now(),?,?)"

  override protected def insertPs(model: PostModel, ps: PreparedStatement): PreparedStatement = {
    ps.setString(1, model.title)
    ps.setString(2, model.contentFile)
    ps.setInt(3, model.categoryId)
    ps.setInt(4, model.briefWay)
    ps
  }
}
