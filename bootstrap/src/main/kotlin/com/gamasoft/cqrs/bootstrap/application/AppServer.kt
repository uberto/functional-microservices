package com.gamasoft.cqrs.bootstrap.application

import com.gamasoft.cqrs.bootstrap.application.AppConfiguration

interface AppServer {

    fun start(configuration: AppConfiguration)

    fun stop()

}
