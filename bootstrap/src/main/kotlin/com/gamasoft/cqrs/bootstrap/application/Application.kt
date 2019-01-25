package com.gamasoft.cqrs.bootstrap.application

typealias ServerFactory = (AppConfiguration) -> AppServer


class Application(val serverFactory: ServerFactory, val configuration: AppConfiguration) {

    fun start(){

            val server = serverFactory(configuration)

            server.start()

            Runtime.getRuntime().addShutdownHook(object : Thread() {
                override fun run() {
                    server.stop()
                }
            })
        }
    }
