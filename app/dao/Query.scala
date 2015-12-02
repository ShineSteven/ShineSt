package dao

import java.sql.{ResultSet, PreparedStatement, Connection}

import common.DBUtils

import scala.util.control.NonFatal


object Query {
  def queryToStore[A](injectParameter: Connection => PreparedStatement)(storeResult: ResultSet => A): List[A] = {
    lazy val conn = DBUtils.connect
    lazy val ps = injectParameter(conn)
    lazy val rs = ps.executeQuery()
    val result = scala.collection.mutable.ArrayBuffer.empty[A]

    try {
      while (rs.next) {
        result += storeResult(rs)
      }
      result.toList
    } finally {
      DBUtils.close(rs)
      DBUtils.close(ps)
      DBUtils.close(conn)
    }


  }
}
