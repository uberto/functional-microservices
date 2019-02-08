package com.gamasoft.cqrs.query

import com.gamasoft.cqrs.commons.application.Application
import com.gamasoft.cqrs.commons.configuration.ConfigurationMap
import com.gamasoft.cqrs.commons.http4k.Http4kFactory
import com.gamasoft.cqrs.query.routes.QueryRoutes

fun main() {
    val serverFactory = Http4kFactory(QueryRoutes())
    val conf = ConfigurationMap(mapOf("port" to "8082"))
    Application(serverFactory, conf).start()
}