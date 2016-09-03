package shine.st.blog.dao

import java.sql.{Connection, PreparedStatement}

import shine.st.blog.common.DBUtils


trait Insert {

  protected def insert(insertSql: String)(p: PreparedStatement => PreparedStatement)(implicit c: Connection): Int = {
    lazy val conn = c
    lazy val ps = p(conn.prepareStatement(insertSql))
    try {
      ps.executeUpdate()
    } finally {
      DBUtils.close(ps)
      DBUtils.close(conn)
    }
  }

}


