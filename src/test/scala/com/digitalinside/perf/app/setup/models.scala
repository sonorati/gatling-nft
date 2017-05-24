package com.digitalinside.perf.app.setup

import spray.json._

case class User(guid: Option[String])

object JsonFormats extends DefaultJsonProtocol {
  implicit val UserFormat = jsonFormat1(User.apply)
}


case class TargetEntry(contextRoot: List[String], targetUrl: List[String], guids: List[String])

object TargetEntryFormats extends DefaultJsonProtocol {
  implicit val TargetEntryFormat = jsonFormat3(TargetEntry.apply)
}