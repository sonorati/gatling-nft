package com.digitalinside.perf.app.simulations

import com.digitalinside.perf.app.{Configuration, SendToTopic}
import io.gatling.core.Predef._
import io.gatling.http.config.HttpProtocolBuilder.toHttpProtocol

import scala.concurrent.duration._

class SendEventsToTopic extends Simulation with Configuration {

  private val scn = scenario("Scenario Performance Test")
    .forever(
      randomSwitch(
        100d → exec(SendToTopic.request)))

  setUp(
    scn.inject(rampUsers(threads) over (rampUp seconds))
  ).maxDuration(duration minutes).protocols(httpConf)

}