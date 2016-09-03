package shine.st.blog.dao

import java.sql.{PreparedStatement, ResultSet}

import shine.st.blog.common.ConnectionPool

/**
  * Created by stevenfanchiang on 2016/5/19.
  */
trait BaseDao[A] extends Query with Insert {
  implicit def connect = {
    ConnectionPool.getConnection()
  }

  val singleSql: String
  val allSql: String
  protected val insertModelSql: String

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


  def list(sql: String)(ps: PreparedStatement => PreparedStatement): List[A] = {
    query(sql)(ps) { rs =>
      val result = scala.collection.mutable.ArrayBuffer.empty[A]
      while (rs.next()) {
        result += generate(rs)
      }
      result.toList
    }
  }

  def insertWithModel(model: A): Int = {
    insert(insertModelSql)(ps => insertPs(model, ps))
  }

  protected def insertPs(model: A, ps: PreparedStatement): PreparedStatement

}
