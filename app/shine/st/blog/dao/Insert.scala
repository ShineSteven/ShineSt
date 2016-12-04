package shine.st.blog.dao

import java.sql.{Connection, PreparedStatement, Statement}

import shine.st.blog.common.DBUtils


trait Insert {

  protected def insert(insertSql: String)(p: PreparedStatement => PreparedStatement)(implicit c: Connection): Int = {
    lazy val conn = c
    lazy val ps = p(conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS))
    lazy val rs = ps.getGeneratedKeys

    try {
      ps.executeUpdate()
      rs.next()
      rs.getInt(1)
    } finally {
      DBUtils.close(rs)
      DBUtils.close(ps)
      DBUtils.close(conn)
    }
  }

}


