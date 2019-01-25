package com.gamasoft.cqrs.bootstrap.application

interface AppConfiguration {

    operator fun get(key: String): String
}
