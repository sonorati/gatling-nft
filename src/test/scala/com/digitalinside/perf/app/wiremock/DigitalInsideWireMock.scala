package com.digitalinside.perf.app.wiremock

import com.digitalinside.perf.app.Configuration
import io.gatling.core.Predef.Simulation
import com.typesafe.scalalogging.LazyLogging
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock._
import com.github.tomakehurst.wiremock.core.WireMockConfiguration._


trait DigitalInsideWireMock extends Configuration with LazyLogging {
  self: Simulation =>

  before {

    logger.info(s"configuring wiremock on $wiremockHost:$wiremockPort")

    WireMock.configureFor(wiremockHost, wiremockPort)

    reset()

    setUpStubForUrlWithStatusCode("/wiremock", 200)

  }

  def setUpStubForUrlWithStatusCode(url: String, statusCode: Int) = {
      logger.info(s"stubbing $url to return status $statusCode")
      setUpStubForUrlsWithStatusCode(List(url), statusCode)
  }

  def setUpStubForUrlsWithStatusCode(urls: Seq[String], statusCode: Int) = {
    urls.foreach(path => stubFor(post(urlEqualTo(path))
      .willReturn(aResponse().withBody(s"WireMockResponse for $path")
        .withStatus(statusCode))))
  }


  def setUpStubFailureWithInternalServerError() = {
    setUpStubForUrlWithStatusCode("/wiremock", 500)
  }

  def reset() {

    logger.info(s"resetting wiremock")
    WireMock.reset()
  }
}
