package shine.st.blog.handler

/**
  * Created by shinest on 04/12/2016.
  */
trait BlogAbnormal extends Exception {
  def message: String
}

case class NoData(message: String) extends BlogAbnormal

case class Error(message: String) extends BlogAbnormal
