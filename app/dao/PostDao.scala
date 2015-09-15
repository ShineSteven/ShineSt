package dao


import common.{IOUtils, DBUtils}
import model.Post
import scala.collection.mutable.ArrayBuffer

/**
 * Created by shinesteven on 2015/8/15.
 */
object PostDao {
  val postPath = "/usr/local/web/post/"

  def getAllPost(): List[Post] = {

    lazy val conn = DBUtils.getConnection
    lazy val ps = conn.prepareStatement("select * from post")
    lazy val rs = ps.executeQuery()
    var posts = new ArrayBuffer[Post]()

    try {
      while (rs.next()) {
        posts += Post(rs.getInt("ID"), rs.getString("TITLE"), IOUtils.readFileToStringOfLimit(postPath + rs.getString("CONTENT_FILE")))
      }
    } finally {
      DBUtils.close(rs)
      DBUtils.close(ps)
      DBUtils.close(conn)
    }
    posts.toList
  }

  def getPostById(id: Long) = {
    lazy val conn = DBUtils.getConnection
    lazy val ps = conn.prepareStatement("select * from post where id = ?")
    ps.setLong(1, id)
    lazy val rs = ps.executeQuery()
    var result: Post = null

    try {
      while (rs.next()) {
        result = Post(rs.getInt("ID"), rs.getString("TITLE"), IOUtils.readFileToString(postPath + rs.getString("CONTENT_FILE")))
      }
    } finally {
      DBUtils.close(rs)
      DBUtils.close(ps)
      DBUtils.close(conn)
    }

    result
  }
}
