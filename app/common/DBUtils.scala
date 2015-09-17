package common

import java.sql.DriverManager
import play.api.Play.current
/**
 * Created by shinesteven on 2015/8/13.
 */
object DBUtils {
  Class.forName("com.mysql.jdbc.Driver")
  val config = current.configuration
//  def getConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shine_st?user=shinest&password=shineGoal")
  println(config.getString("DB.ip"))
  def connect = DriverManager.getConnection(s"jdbc:mysql://${config.getString("DB.ip").get}/${config.getString("DB.database").get}?user=${config.getString("DB.account").get}&password=${config.getString("DB.pw").get}")

  def close(closeable: AutoCloseable) {
    if (closeable != null)
      closeable.close()
  }

}
