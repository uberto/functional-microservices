package com.gamasoft.cqrs.command

import com.gamasoft.cqrs.commons.application.Application
import com.gamasoft.cqrs.commons.configuration.ConfigurationMap
import com.gamasoft.cqrs.commons.http4k.Http4kFactory
import com.gamasoft.cqrs.command.routes.CommandRoutes


fun main() {
    val serverFactory = Http4kFactory(CommandRoutes())
    val conf = ConfigurationMap(mapOf("port" to "8081"))
    Application(serverFactory, conf).start()
}