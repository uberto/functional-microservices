package com.gamasoft.cqrs.command

import com.gamasoft.cqrs.bootstrap.application.Application
import com.gamasoft.cqrs.bootstrap.configuration.ConfigurationMap
import com.gamasoft.cqrs.bootstrap.http4k.Http4kFactory
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK


fun main(args: Array<String>) {
    val serverFactory = Http4kFactory{
            request: Request -> Response(OK).body("Hello, you asked for ${request.uri}!")
    }
    val conf = ConfigurationMap(mapOf("port" to "8081"))
    Application(serverFactory, conf).start()
}