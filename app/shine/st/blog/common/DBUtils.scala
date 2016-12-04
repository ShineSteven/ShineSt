package shine.st.blog.common

import java.sql.DriverManager

import com.mchange.v2.c3p0.ComboPooledDataSource
import play.api.Configuration
import shine.st.blog._


/**
  * Created by shinest on 2015/8/13.
  */
object DBUtils {
  Class.forName("com.mysql.jdbc.Driver")

  def connect = DriverManager.getConnection(s"jdbc:mysql://${config.getString("DB.ip").get}/${config.getString("DB.database").get}?user=${config.getString("DB.user").get}&password=${config.getString("DB.password").get}")

  def close(closeable: => AutoCloseable) {
    if (closeable != null)
      closeable.close()
  }

}

object ConnectionPool extends BaseConnectionPool {
  init(config.getConfig("DB").get)
}


abstract class BaseConnectionPool {
  private val pool: ComboPooledDataSource = new ComboPooledDataSource()

  def init(config: Configuration) = {
    pool.setDriverClass(config.getString("driver").get)
    pool.setJdbcUrl(config.getString("url").get)
    pool.setUser(config.getString("user").get)
    pool.setPassword(config.getString("password").get)
    pool.setMaxPoolSize(20)
  }

  def getConnection() = pool.getConnection
}

