package com.digitalinside.perf.app

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder.toActionBuilder

trait UserApis extends Configuration {

  object GetUsers {
    val request: ScenarioBuilder = scenario("UserController - readUsers")
      .exec(http("Get URL")
        .get("wiremock/users")
        .check(status.in(200)))
  }

}