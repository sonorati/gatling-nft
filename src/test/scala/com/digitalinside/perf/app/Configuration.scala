package com.digitalinside.perf.app

import com.typesafe.config.{Config, ConfigFactory}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

trait Configuration {

  val config: Config = ConfigFactory.load("environments/" + System.getProperty("env", "local")).resolve()

  val topicUrl = config.getString("topic.url")
  private val brokerPropertiesName = config.getString("topic.headers.brokerProperties.name")
  private val brokerPropertiesValue = config.getString("topic.headers.brokerProperties.value")
  private val contentType = config.getString("topic.headers.contentType")
  private val sasToken = config.getString("topic.sasToken")
  val topicName: String = config.getString("topic.name")


  val httpConf = http.baseURL(s"$topicUrl/$topicName")
    .authorizationHeader(sasToken)
    .contentTypeHeader(contentType)
    .header(brokerPropertiesName, brokerPropertiesValue)

  val rampUp: Int = System.getProperty("rampUp", "20").toInt
  val threads: Int = System.getProperty("users", "100").toInt
  val duration: Int = System.getProperty("duration", "2").toInt
  val startUsers: Int = System.getProperty("startUsers", "1").toInt
}
