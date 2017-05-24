package com.digitalinside.perf.app.wiremock

import com.digitalinside.perf.app.Configuration
import com.digitalinside.perf.app.setup.TargetEntry
import io.gatling.core.Predef.Simulation
import com.typesafe.scalalogging.LazyLogging
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock._
import com.github.tomakehurst.wiremock.core.WireMockConfiguration._
import spray.json._
import com.digitalinside.perf.app.setup.TargetEntryFormats._

trait WireMockSupport extends Configuration with LazyLogging {
  self: Simulation =>

  before {

    logger.info(s"configuring wiremock on $wiremockHost:$wiremockPort")

    WireMock.configureFor(wiremockHost, wiremockPort)

    reset()

    val body  = Some(List(TargetEntry(List("fromHere"),List("toHere"), List("withme"))).toJson.prettyPrint)

    setUpStubForUrlWithStatusCode("/wiremock/users", 200, body)

  }

  def setUpStubForUrlWithStatusCode(url: String, statusCode: Int, body: Option[String] = None) = {
    logger.info(s"stubbing $url to return status $statusCode")
    setUpStubForUrlsWithStatusCode(List(url), statusCode, body)
  }

  def setUpStubForUrlsWithStatusCode(urls: Seq[String], statusCode: Int, body: Option[String] = None) = {

    val defaultResponse = aResponse().withStatus(statusCode)
    val response = body.map(defaultResponse.withBody).getOrElse(defaultResponse)

    urls.foreach(path => stubFor(
      any(urlEqualTo(path))
        .willReturn(
          response
        )
    )
    )
  }


  def setUpStubFailureWithInternalServerError() = {
    setUpStubForUrlWithStatusCode("/wiremock", 500)
  }

  def reset() {
    logger.info(s"resetting wiremock")
    WireMock.reset()
  }
}
