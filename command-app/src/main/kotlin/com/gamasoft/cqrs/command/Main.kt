package com.gamasoft.cqrs.command

import com.gamasoft.cqrs.bootstrap.application.Application
import com.gamasoft.cqrs.bootstrap.configuration.ConfigurationMap
import com.gamasoft.cqrs.bootstrap.http4k.Http4kFactory
import com.gamasoft.cqrs.command.routes.CommandRoutes
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK


fun main() {
    val serverFactory = Http4kFactory(CommandRoutes())
    val conf = ConfigurationMap(mapOf("port" to "8081"))
    Application(serverFactory, conf).start()
}