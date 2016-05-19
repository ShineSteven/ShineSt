package shine.st.blog.dao

import java.sql.ResultSet

/**
  * Created by stevenfanchiang on 2016/5/19.
  */
trait BaseDao[A] extends Query{
  val singleSql :String
  val allSql: String
  def generate(rs: ResultSet): A

  def queryById(id: Int): Option[A] = {
    query(singleSql) { ps =>
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

  def all(): List[A] = {
    query(allSql)(p => p) { rs =>
      val result = scala.collection.mutable.ArrayBuffer.empty[A]
      while (rs.next()) {
        result += generate(rs)
      }
      result.toList
    }
  }


}
