package shine.st.blog.dao

import java.sql.{PreparedStatement, ResultSet}

/**
  * Created by stevenfanchiang on 2016/5/19.
  */
trait BaseDao[A] extends Query {
  val singleSql: String
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
    list(allSql)(p => p)
  }


  def list(sql: String)(p: PreparedStatement => PreparedStatement): List[A] = {
    query(sql)(p) { rs =>
      val result = scala.collection.mutable.ArrayBuffer.empty[A]
      while (rs.next()) {
        result += generate(rs)
      }
      result.toList
    }
  }


}
