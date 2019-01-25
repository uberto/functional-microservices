package com.gamasoft.cqrs.bootstrap.http4k

import com.gamasoft.cqrs.bootstrap.application.AppServer
import org.http4k.core.HttpHandler
import org.http4k.server.Jetty
import org.http4k.server.asServer

class Http4kAppServer(handler: HttpHandler, port: Int): AppServer {
    val jettyServer = handler.asServer(Jetty(port))

    override fun stop() { jettyServer.stop() }

    override fun start() { jettyServer.start() }
}