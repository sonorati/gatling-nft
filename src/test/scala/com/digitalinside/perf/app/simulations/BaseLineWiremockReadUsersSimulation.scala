package com.digitalinside.perf.app.simulations

import com.digitalinside.perf.app.wiremock.WireMockSupport
import com.digitalinside.perf.app.{Configuration, UserApis}
import io.gatling.core.Predef._
import io.gatling.http.config.HttpProtocolBuilder.toHttpProtocol

import scala.concurrent.duration._

class BaseLineWiremockReadUsersSimulation extends Simulation with WireMockSupport  with UserApis with Configuration {



  private val scnReadUsers = scenario("Scenario read users")
    .forever(
      exec(GetUsers.request)
    )


  private val scn = scenario("Scenario Performance Test")
    .forever(
      randomSwitch(
        100d → exec(scnReadUsers)
      )
    )

  setUp(
    scn.inject(
      rampUsers(users) over (rampUp seconds))
  ).maxDuration(duration seconds).protocols(httpConf)
}