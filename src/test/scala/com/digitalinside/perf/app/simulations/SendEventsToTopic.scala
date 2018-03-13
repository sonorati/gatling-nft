package com.digitalinside.perf.app.simulations

import com.digitalinside.perf.app.{Configuration, TopicApi}
import io.gatling.core.Predef._
import io.gatling.http.config.HttpProtocolBuilder.toHttpProtocol

import scala.concurrent.duration._

class SendEventsToTopic extends Simulation  with TopicApi with Configuration {


  private val scnReadUsers = scenario("Scenario send events to asb topic")
    .forever(
      exec(SendToTopic.request)
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
  ).maxDuration(duration minutes).protocols(httpConf)
}