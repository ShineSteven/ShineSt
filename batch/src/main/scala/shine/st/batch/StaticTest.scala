package shine.st.batch

import java.io.File

import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils

import scala.io.Source


/**
  * Created by shinest on 2016/4/10.
  */
object StaticTest  extends App{
  val httpclient = HttpClients.createDefault();
  val httpGet = new HttpGet("http://shinest.cc/post/11");
  val response1 = httpclient.execute(httpGet);
  // The underlying HTTP connection is still held by the response object
  // to allow the response content to be streamed directly from the network socket.
  // In order to ensure correct deallocation of system resources
  // the user MUST call CloseableHttpResponse#close() from a finally clause.
  // Please note that if response content is not fully consumed the underlying
  // connection cannot be safely re-used and will be shut down and discarded
  // by the connection manager.
  try {
    println(response1.getStatusLine());
    val entity1 = response1.getEntity();
    inputStreamToFile(entity1.getContent,"/Users/shinest/Steven/technology/tmp/post_11.html")
    // do something useful with the response body
    // and ensure it is fully consumed
    EntityUtils.consume(entity1);
  } finally {
    response1.close();
  }

//  HttpPost httpPost = new HttpPost("http://targethost/login");
//  List <NameValuePair> nvps = new ArrayList <NameValuePair>();
//    nvps.add(new BasicNameValuePair("username", "vip"));
//    nvps.add(new BasicNameValuePair("password", "secret"));
//    httpPost.setEntity(new UrlEncodedFormEntity(nvps));
//    CloseableHttpResponse response2 = httpclient.execute(httpPost);
//
//    try {
//    System.out.println(response2.getStatusLine());
//    HttpEntity entity2 = response2.getEntity();
//    // do something useful with the response body
//    // and ensure it is fully consumed
//    EntityUtils.consume(entity2);
//    } finally {
//    response2.close();
//    }

  def inputStreamToFile(inputStream: java.io.InputStream, fileName: String) = {
    val file = new File(fileName)
    val fos = new java.io.FileOutputStream(file)
    fos.write(
      Stream.continually(inputStream.read).takeWhile(-1 !=).map(_.toByte).toArray
    )
    fos.close()

    file
  }
}
