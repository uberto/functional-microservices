package com.gamasoft.cqrs.query.routes

import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK

class QueryRoutes: HttpHandler {
    override fun invoke(req: Request): Response =
        Response(OK).body("Here Query, you asked for ${req.uri}!")



}