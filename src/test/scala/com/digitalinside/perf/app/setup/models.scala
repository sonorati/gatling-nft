package com.digitalinside.perf.app.setup

import spray.json._

case class User(guid: Option[String])

object JsonFormats extends DefaultJsonProtocol {
  implicit val UserFormat = jsonFormat1(User.apply)
}
