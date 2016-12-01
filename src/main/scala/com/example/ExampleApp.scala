package com.example

import java.util.concurrent.Executors

import org.http4s.server.blaze.BlazeBuilder
import org.http4s.server.{Server, ServerApp, ServerBuilder}
import org.http4s.{HttpService, Service}
import org.http4s.dsl._
import org.http4s.circe._
import org.http4s.MediaType._
import org.http4s.headers._

import io.circe.Json
import org.log4s.getLogger

import scala.util.Properties.envOrNone
import scalaz.concurrent.Task

class ExampleApp(host: String, port: Int) {

  val service = HttpService {

  case GET -> Root / "hello" / name =>
    Ok(s"Hello, $name.")

   case GET -> Root / "json" / name => {
      val json = Json.fromString(name)
      Ok(json).withContentType(Some(`Content-Type`(`application/json`)))
	}
  }

  // Construct the blaze pipeline.
  def build(): ServerBuilder =
    BlazeBuilder
      .bindHttp(port, host)
      .mountService(service)
}

object ExampleApp extends ServerApp {
  val ip   = "0.0.0.0"
  val port = envOrNone("HTTP_PORT") map (_.toInt) getOrElse (8080)

  override def server(args: List[String]): Task[Server] =
    new ExampleApp(ip, port)
      .build()
      .start

}
