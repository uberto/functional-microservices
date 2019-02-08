package com.gamasoft.cqrs.commons.application

interface AppConfiguration {

    operator fun get(key: String): String
}
