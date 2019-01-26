package com.gamasoft.cqrs.eventstore

import com.gamasoft.cqrs.bootstrap.application.Application
import com.gamasoft.cqrs.bootstrap.configuration.ConfigurationMap
import com.gamasoft.cqrs.bootstrap.http4k.Http4kFactory
import com.gamasoft.cqrs.eventstore.routes.EventStoreRoutes

fun main() {
    val serverFactory = Http4kFactory(EventStoreRoutes())
    val conf = ConfigurationMap(mapOf("port" to "8090"))
    Application(serverFactory, conf).start()
}