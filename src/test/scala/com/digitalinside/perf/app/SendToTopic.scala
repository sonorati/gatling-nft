package com.digitalinside.perf.app

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder.toActionBuilder

object SendToTopic extends Configuration {
  val request: ScenarioBuilder = scenario("Send event to asb")
    .exec(http("post URL asb/topic")
      .post("/messages")
      .body(RawFileBody("tgr_event.json"))
      .check(status.in(201)))
}

