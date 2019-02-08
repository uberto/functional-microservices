package com.gamasoft.cqrs.eventstore

import com.gamasoft.cqrs.commons.application.Application
import com.gamasoft.cqrs.commons.configuration.ConfigurationMap
import com.gamasoft.cqrs.commons.http4k.Http4kFactory
import com.gamasoft.cqrs.eventstore.routes.EventStoreRoutes

fun main() {
    val serverFactory = Http4kFactory(EventStoreRoutes())
    val conf = ConfigurationMap(mapOf("port" to "8090"))
    Application(serverFactory, conf).start()
}