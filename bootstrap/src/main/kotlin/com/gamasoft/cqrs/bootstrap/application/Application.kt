package com.gamasoft.cqrs.bootstrap.application

class Application(val server: AppServer, val configuration: AppConfiguration) {

    fun start(){

            server.start(configuration)

            Runtime.getRuntime().addShutdownHook(object : Thread() {
                override fun run() {
                    server.stop()
                }
            })
        }
    }
