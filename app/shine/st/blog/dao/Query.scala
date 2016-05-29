package shine.st.blog.dao

import java.sql.{Connection, PreparedStatement, ResultSet}

import shine.st.blog.common.{ConnectionPool, DBUtils}


trait Query {
  implicit def connect = {
    ConnectionPool.getConnection()
  }

  def query[A](sql: String)(p: PreparedStatement => PreparedStatement)(r: ResultSet => A)(implicit c: Connection): A = {
    lazy val conn = c
    lazy val ps = p(conn.prepareStatement(sql))
    lazy val rs = ps.executeQuery()
    try {
      r(rs)
    } finally {
      DBUtils.close(rs)
      DBUtils.close(ps)
      DBUtils.close(conn)
    }
  }

}


