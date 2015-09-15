package common

import scala.util.Try

/**
 * Created by shinesteven on 2015/8/23.
 */
object ParseUtils {
  def parseInt(value: String) = Try { value.toInt } toOption
}
