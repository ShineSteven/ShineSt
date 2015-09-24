package common

import java.text.SimpleDateFormat
import java.util.Date

object DateUtils {
  def baseDateFormat(date: Date) = {
    val format: SimpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
    format.format(date)
  }
}
