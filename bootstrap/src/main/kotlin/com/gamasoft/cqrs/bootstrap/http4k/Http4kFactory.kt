package com.gamasoft.cqrs.bootstrap.http4k

import com.gamasoft.cqrs.bootstrap.application.AppConfiguration
import com.gamasoft.cqrs.bootstrap.application.AppServer
import com.gamasoft.cqrs.bootstrap.application.ServerFactory
import org.http4k.core.HttpHandler

class Http4kFactory(private val handler: HttpHandler): ServerFactory {
    override fun invoke(configuration: AppConfiguration): AppServer =
            Http4kAppServer(handler, configuration["port"].toInt())

}