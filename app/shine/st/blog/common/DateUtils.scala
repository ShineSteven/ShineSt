package shine.st.blog.common

import java.text.SimpleDateFormat
import java.util.Date

import org.joda.time.DateTime

import scala.util.Try

object DateUtils {
  val DateTime = "yyyy-MM-dd HH:mm:ss"


  def parse(date: String, pattern: String) = {
    Try {
      val dateFormat = new SimpleDateFormat(pattern)
      dateFormat.parse(date)
    }.toOption
  }

  def format(date: Date, pattern: String) = {
    Try {
      val dateFormat = new SimpleDateFormat(pattern)
      dateFormat.format(date)
    }.toOption
  }

  def parseDateTime(date: String, pattern: String = DateTime) = {
    parse(date, pattern)
  }

  def formatDateTime(date: Date, pattern: String = DateTime) = {
    format(date, pattern)
  }

  def formatOptionDateTime(date: Option[Date], pattern: String = DateTime) = {
    date match {
      case Some(c) => format(c, pattern)
      case None => None
    }
  }

  def newDateTimeWithOpt(date: Date) = {
    Try {
      new DateTime(date.getTime)
    }.toOption
  }
}
