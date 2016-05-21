package shine.st.blog.common

import java.util.Date

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

import scala.util.Try

object DateTimeUtils {
  val DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss"
  val DATE_PATTERN = "yyyy-MM-dd"

  val dateTimeFormatter = DateTimeFormat.forPattern(DATE_TIME_PATTERN)
  val dateFormatter = DateTimeFormat.forPattern(DATE_PATTERN)

  def parse(dateString: String, pattern: String) = {
    Try {
      val formatter = pattern match {
        case DATE_TIME_PATTERN => dateTimeFormatter
        case DATE_PATTERN => dateFormatter
        case p => DateTimeFormat.forPattern(p)
      }
      formatter.parseDateTime(dateString)
    }.toOption
  }

  def format(date: DateTime, pattern: String) = {
    Try {
      val formatter = pattern match {
        case DATE_TIME_PATTERN => dateTimeFormatter
        case DATE_PATTERN => dateFormatter
        case p => DateTimeFormat.forPattern(p)
      }
      date.toString(formatter)
    }.toOption
  }

  def parseDateTime(date: String, pattern: String = DATE_TIME_PATTERN) = {
    parse(date, pattern)
  }

  def formatDateTime(date: DateTime, pattern: String = DATE_TIME_PATTERN) = {
    format(date, pattern)
  }

  def formatOptionDateTime(date: Option[DateTime], pattern: String = DATE_TIME_PATTERN) = {
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
