package dao

import java.sql.{Connection, PreparedStatement, ResultSet}

import common.{DBUtils, GenerateModel}

import scala.util.control.NonFatal

/**
 * Created by shinesteven on 2015/8/15.
 */
object PostDao {
  val singlePostSql = "select * from post where id = ?"
  val allPostSql = "select * from post order by id desc"

  def getAllPost() = {
    queryPost { connection =>
      val ps = connection.prepareCall(allPostSql)
      ps
    } {
      GenerateModel.post
    }
  }

  def queryPostById(id: Long) = {
    queryPost { connection =>
      val ps = connection.prepareStatement(singlePostSql)
      ps.setLong(1, id)
      ps
    } {
      GenerateModel.post
    }
  }

  def queryPost[A](injectParameter: Connection => PreparedStatement)(storeResult: ResultSet => A): Seq[A] = {
    lazy val conn = DBUtils.connect
    lazy val ps = injectParameter(conn)
    lazy val rs = ps.executeQuery()
    val result = scala.collection.mutable.ArrayBuffer.empty[A]

    try {
      while (rs.next) {
        result += storeResult(rs)
      }
      result.toList
    } catch {
      case NonFatal(ex) =>
        println(ex)
        Nil
    } finally {
      DBUtils.close(rs)
      DBUtils.close(ps)
      DBUtils.close(conn)
    }


  }
}
