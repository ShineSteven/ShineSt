package dao

import common.GenerateModel

object PostDao {
  val singlePostSql = "select * from post where id = ?"
  val allPostSql = "select * from post order by id desc"

  def allPost() = {
    Query.queryToStore(conn => conn.prepareStatement(allPostSql)) {
      GenerateModel.post
    }
  }

  def queryPostById(id: Int) = {
    Query.queryToStore { conn =>
      val ps = conn.prepareStatement(singlePostSql)
      ps.setInt(1, id)
      ps
    } {
      GenerateModel.post
    }.head

  }
}
