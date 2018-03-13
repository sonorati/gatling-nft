package com.digitalinside.perf.app

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder.toActionBuilder

trait TopicApi extends Configuration {

  object SendToTopic {
    val request: ScenarioBuilder = scenario("Send event to asb")
      .exec(http("Get URL wiremock/users")
        .get("wiremock/users")
        .check(status.in(200)))
  }

}