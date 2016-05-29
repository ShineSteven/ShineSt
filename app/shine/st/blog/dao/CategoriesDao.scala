package shine.st.blog.dao

import java.sql.ResultSet

import shine.st.blog.common.{DateTimeUtils, NumberUtils}
import shine.st.blog.model.CategoriesModel

/**
  * Created by shinest on 2016/5/17.
  */
object CategoriesDao extends BaseDao[CategoriesModel] {
  override val singleSql: String = "select * from categories where id = ?"
  override val allSql: String = "select * from categories order by id desc"

  override def generate(rs: ResultSet) = {
    CategoriesModel(rs.getInt("ID"), NumberUtils.toInt(rs.getString("parent_id")), rs.getString("name"), DateTimeUtils.newDateTimeWithOpt(rs.getTimestamp("create_at")).get, DateTimeUtils.newDateTimeWithOpt(rs.getTimestamp("update_at")), Option(rs.getString("description")))
  }

  def queryByName(name: String): Option[CategoriesModel] = {
    query("select * from categories where lower(name) = ?") { ps =>
      ps.setString(1, name)
      ps
    } { rs =>
      if (rs.next()) {
        Option(generate(rs))
      } else {
        None
      }
    }
  }

  def queryByParentId(id: Int): List[CategoriesModel] = {
    list("select * from categories where parent_id = ?") { ps =>
      ps.setInt(1, id)
      ps
    }
  }

}
