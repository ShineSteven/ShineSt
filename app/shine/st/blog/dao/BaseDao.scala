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

  val singleRs = ((rs: ResultSet) => {
    if (rs.next)
      Option(generate(rs))
    else
      None
  })


  def all(): Option[List[A]] = {
    list(allSql)(p => p)
  }


  def list(sql: String)(ps: PreparedStatement => PreparedStatement): Option[List[A]] = {
    query(sql)(ps) {
      rs =>
        val result = scala.collection.mutable.ArrayBuffer.empty[A]
        while (rs.next()) {
          result += generate(rs)
        }
        if (!result.isEmpty)
          Some(result.toList)
        else
          None
    }
  }


  def queryById(id: Int): Option[A] = {
    queryBy(singleSql) {
      ps =>
        ps.setInt(1, id)
        ps
    }
  }

  def queryBy(sql: String)(p: PreparedStatement => PreparedStatement) = {
    query(sql)(p)(singleRs)
  }


  def insertWithModel(model: A): Int = {
    insert(insertModelSql)(ps => insertPs(model, ps))
  }

  protected def insertPs(model: A, ps: PreparedStatement): PreparedStatement

}
