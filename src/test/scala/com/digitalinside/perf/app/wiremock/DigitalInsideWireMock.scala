package com.digitalinside.perf.app.wiremock

import com.digitalinside.perf.app.Configuration
import io.gatling.core.Predef.Simulation
import com.typesafe.scalalogging.LazyLogging
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock._
import com.github.tomakehurst.wiremock.core.WireMockConfiguration._


trait DigitalInsideWireMock extends Configuration {
  self: Simulation with LazyLogging =>

  before {
    WireMock.configureFor(wiremockHost, wiremockPort)

    reset()

    setUpInitialStubWithStatusCode(200)

    self.before()
  }


  def setUpInitialStubWithStatusCode(statusCode: Int) = {
    List("/wiremock").foreach(path => stubFor(post(urlEqualTo(path))
      .willReturn(aResponse().withBody(s"WireMockResponse for $path")
        .withStatus(statusCode))))
  }

  def setUpStubFailureWithInternalServerError() = {
    setUpInitialStubWithStatusCode(500)
  }

  def reset() {
    WireMock.reset()
  }
}
