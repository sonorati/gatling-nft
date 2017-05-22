package com.digitalinside.perf.app

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder.toActionBuilder

trait UserApis extends Configuration {

  object GetPaymentsAccounts {
    val request: ScenarioBuilder = scenario("PaymentController - readPaymentsAccounts")
      .exec(http("Get URL")
        .get("")
        .header("_guid", "")
        .check(status.in(200)))
  }

}