package com.gamasoft.cqrs.eventstore.routes

import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK

class EventStoreRoutes: HttpHandler {
    override fun invoke(req: Request): Response =
        Response(OK).body("Here EventStore, you asked for ${req.uri}!")



}