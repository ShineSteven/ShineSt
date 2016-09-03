package shine.st.blog.dao

import java.sql.{PreparedStatement, ResultSet}

import shine.st.blog.model.Model.CategoriesModel
import shine.st.common.{DateTimeUtils, NumberUtils}

/**
  * Created by shinest on 2016/5/17.
  */
object CategoriesDao extends BaseDao[CategoriesModel] {
  override val singleSql: String = "select * from categories where id = ?"
  override val allSql: String = "select * from categories order by id desc"
  override protected val insertModelSql: String = "insert into categories(parent_id,name,create_at,description) values(?,?,str_to_date(?,'%Y-%m-%d %T'),?)"

  override def generate(rs: ResultSet) = {
    CategoriesModel(rs.getInt("ID"), NumberUtils.stringToInt(rs.getString("parent_id")), rs.getString("name"), DateTimeUtils.getDateTimeOptFromDate(rs.getTimestamp("create_at")).get, DateTimeUtils.getDateTimeOptFromDate(rs.getTimestamp("update_at")), Option(rs.getString("description")))
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


  override protected def insertPs(model: CategoriesModel, ps: PreparedStatement): PreparedStatement = {
    ps.setString(1, model.parentId.map {
      _.toString
    }.getOrElse(""))
    ps.setString(2, model.name)
    ps.setString(3, DateTimeUtils.formatDateHour(model.createAt))
    ps.setString(4, DateTimeUtils.formatDateHour(model.updateAt))
    ps.setString(5, model.description.getOrElse(""))
    ps
  }
}
