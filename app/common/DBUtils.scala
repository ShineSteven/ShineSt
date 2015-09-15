package common

import java.sql.DriverManager

/**
 * Created by shinesteven on 2015/8/13.
 */
object DBUtils {
  Class.forName("com.mysql.jdbc.Driver")

  def getConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shine_st?user=shinest&password=shineGoal")

  def close(closeable: AutoCloseable) {
    if (closeable != null)
      closeable.close()
  }

}
