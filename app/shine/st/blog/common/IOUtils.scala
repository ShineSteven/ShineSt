package shine.st.blog.common

import java.io.InputStream

import play.api.Play.current

import scala.io.Source
import scala.util.control.Exception.nonFatalCatch

/**
 * Created by shinesteven on 2015/8/15.
 */
object IOUtils {
  def readFileToString(fileName :String) = {
    val source = Source.fromFile(fileName)
    nonFatalCatch[String] andFinally { source.close() } opt { source.mkString } getOrElse("unknow content")
  }

  def readFileToStringOfLimit(fileName :String) = {
    val limit = NumberUtils.parseInt(current.configuration.getString("index.post.limit").get)
    val source = Source.fromFile(fileName)
//println( source.getLines().take(limit.getOrElse(10)).mkString)
    nonFatalCatch[String] andFinally { source.close() } opt { source.getLines().take(limit.getOrElse(10)).mkString } getOrElse("unknow content")

  }

  def inputStreamToString(is:InputStream) = {
    val source = Source.fromInputStream(is)
    nonFatalCatch[String] andFinally { source.close() } opt { source.mkString } getOrElse("unknow content")
  }
}
