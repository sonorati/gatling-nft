package com.digitalinside.perf.app.simulations

import com.digitalinside.perf.app.wiremock.DigitalInsideWireMock
import com.digitalinside.perf.app.{Configuration, UserApis}
import io.gatling.core.Predef._
import io.gatling.http.config.HttpProtocolBuilder.toHttpProtocol

import scala.concurrent.duration._

class BaseLineWiremockReadAccountsSimulation extends Simulation with DigitalInsideWireMock  with UserApis with Configuration {


  setUpStubForUrlWithStatusCode("", 200)
  after()

  private val scnReadAccounts = scenario("Scenario read payments accounts")
    .forever(
      exec(GetUsers.request)
    )


  private val scn = scenario("Scenario Performance Test")
    .forever(
      randomSwitch(
        100d → exec(scnReadAccounts)
      )
    )

  setUp(
    scn.inject(
      rampUsers(users) over (rampUp seconds))
  ).maxDuration(duration minutes).protocols(httpConf)
}