package com.gamasoft.cqrs.bootstrap.application

import assertk.assertThat
import assertk.assertions.isTrue
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ApplicationTest

@Test
fun startServerOnStart(){

    val server = FakeServer()
    val conf = ConfigurationMap()

    Application(server, conf).start()

    assertThat(server.isRunning).isTrue()

}

class ConfigurationMap : AppConfiguration {
    override fun get(key: String): String = "$key value"
}

class FakeServer : AppServer {
    var isRunning = false

    override fun start(configuration: AppConfiguration) {
        isRunning = true
    }

    override fun stop() {
        isRunning = false
    }

}
