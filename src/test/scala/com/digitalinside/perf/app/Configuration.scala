package com.digitalinside.perf.app

import com.typesafe.config.{Config, ConfigFactory}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

trait Configuration {

  val config: Config = ConfigFactory.load("environments/" + System.getProperty("env", "local")).resolve()


  val wiremockHost: String = config.getString("wiremock.url")
  val wiremockPort: Int = config.getInt("wiremock.port")

  val httpConf = http.baseURL(config.getString("public.url")) // Here is the root for all relative URLs
    .acceptHeader("application/json") // Here are the common headers
    .contentTypeHeader("application/json")
    .acceptEncodingHeader("gzip, deflate,sdch ")
    .acceptLanguageHeader("en-US,en;q=0.8")

  val rampUp: Int = System.getProperty("rampUp", "60").toInt
  val users: Int = System.getProperty("users", "100").toInt
  val duration: Int = System.getProperty("duration", "2").toInt
  val startUsers: Int = System.getProperty("startUsers", "1").toInt
}
