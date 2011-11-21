package github.seanparsons.finagletest

import com.twitter.finagle.builder._
import com.twitter.finagle.http._
import com.twitter.finagle._
import org.jboss.netty.handler.codec.http._
import org.jboss.netty.handler.codec.http.HttpVersion._
import org.jboss.netty.handler.codec.http.HttpResponseStatus._
import com.twitter.util.Future
import java.net.InetSocketAddress

object FinagleTest extends App {
  val service: Service[HttpRequest, HttpResponse] = new Service[HttpRequest, HttpResponse] { // 1
    def apply(request: HttpRequest) = Future(new DefaultHttpResponse(HTTP_1_1, OK))          // 2
  }

  val address = new InetSocketAddress(10000)                                  // 3

  val server = ServerBuilder()                            // 4
    .codec(new Http)
    .bindTo(address)
    .name("HttpServer")
    .build(service)
  Console.readLine()
  server.close()
}