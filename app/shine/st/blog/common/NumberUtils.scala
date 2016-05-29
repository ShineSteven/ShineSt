package shine.st.blog.common

import scala.util.Try

/**
 * Created by shinesteven on 2015/8/23.
 */
object NumberUtils {
  def toInt(value: String) = Try { value.toInt } toOption
}
