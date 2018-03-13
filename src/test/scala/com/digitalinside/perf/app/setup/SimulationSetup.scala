package com.digitalinside.perf.app.setup

import javax.ws.rs.client.{Client, WebTarget}

import com.digitalinside.perf.app.{Configuration, JerseyClientBuilder}


object SimulationSetup extends Configuration {
  val client: Client = JerseyClientBuilder.getClient
  val mmHost = httpConf.protocol.baseURL.getOrElse("http://localhost:8080/") + "pims"
  private val mmResource = client.target(mmHost)
  val userResource: WebTarget = mmResource.path("user")
}