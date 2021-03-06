package shine.st.blog.dao

import java.sql.{PreparedStatement, ResultSet}

import shine.st.blog.model.Model.BriefModel

object BriefDao extends BaseDao[BriefModel] {
  override val singleSql: String = "select * from brief where id = ?"
  override val allSql: String = "select * from brief order by id desc"

  override def generate(rs: ResultSet) = {
    BriefModel(rs.getInt("ID"), rs.getInt("POST_ID"), rs.getString("CONTENT"))
  }


  def queryByPostId(postId: Int): Option[BriefModel] = {
    queryBy("select * from brief where post_id = ?") { ps =>
      ps.setInt(1, postId)
      ps
    }
  }

  override protected val insertModelSql: String = "insert into brief(post_id,content) values(?,?)"

  override protected def insertPs(model: BriefModel, ps: PreparedStatement): PreparedStatement = {
    ps.setInt(1, model.postId)
    ps.setString(2, model.content)
    ps
  }
}
