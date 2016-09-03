package shine.st.blog.dao

import java.sql.{PreparedStatement, ResultSet}

import shine.st.blog.model.Model.ManagerModel
import shine.st.common.DateTimeUtils

object ManagerDao extends BaseDao[ManagerModel] {
  override val singleSql: String = "select * from manager where id = ?"
  override val allSql: String = "select * from manager order by id desc"

  override def generate(rs: ResultSet) = {
    ManagerModel(rs.getInt("ID"), rs.getString("ACCOUNT"), rs.getString("ALIAS_NAME"), rs.getString("PASSWORD"), DateTimeUtils.getDateTimeOptFromDate(rs.getTimestamp("create_at")).get, rs.getByte("STATUS"))
  }

  override protected val insertModelSql: String = "insert into manager(account,alias_name,password,create_at,status) values(?,?,?,now(),1)"

  override protected def insertPs(model: ManagerModel, ps: PreparedStatement): PreparedStatement = {
    ps.setString(1, model.account)
    ps.setString(2, model.aliasName)
    ps.setString(3, model.password)
    ps
  }
}
