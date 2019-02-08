package com.gamasoft.cqrs.commons.application

import assertk.assertThat
import assertk.assertions.isTrue
import com.gamasoft.cqrs.commons.configuration.ConfigurationMap
import org.junit.jupiter.api.Test

internal class ApplicationTest {

    @Test
    fun startServerOnStart() {

        val server = FakeServer()

        fun createServer(configuration: AppConfiguration) = server

        val conf = ConfigurationMap(emptyMap())

        Application(::createServer, conf).start()

        assertThat(server.isRunning).isTrue()

    }
}



class FakeServer : AppServer {
    var isRunning = false

    override fun start() {
        isRunning = true
    }

    override fun stop() {
        isRunning = false
    }

}
