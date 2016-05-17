package shine.st.blog.dao

import java.sql.{Connection, PreparedStatement, ResultSet}

import shine.st.blog.common.{DBUtils, DevConnectionPool}
import shine.st.database.{DBUtils, DevConnectionPool}
import sun.tools.tree.FinallyStatement

import scala.util.Try
import scala.util.control.NonFatal


trait Query[A] {
  implicit def connect = {
    println("trait get connection")
    DevConnectionPool.getConnection()
  }

  def query[B](sql: String)(p: PreparedStatement => PreparedStatement)(r: ResultSet => A)(implicit c: Connection): B = {
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


