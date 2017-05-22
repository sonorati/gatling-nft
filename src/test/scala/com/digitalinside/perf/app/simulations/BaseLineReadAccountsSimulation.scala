package com.digitalinside.perf.app.simulations

import com.digitalinside.perf.app.{Configuration, UserApis}
import io.gatling.core.Predef._
import io.gatling.http.config.HttpProtocolBuilder.toHttpProtocol

import scala.concurrent.duration._

class BaseLineReadAccountsSimulation extends Simulation  with UserApis with Configuration {


  private val scnReadAccounts = scenario("Scenario read payments accounts")
    .forever(
      exec(GetPaymentsAccounts.request)
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