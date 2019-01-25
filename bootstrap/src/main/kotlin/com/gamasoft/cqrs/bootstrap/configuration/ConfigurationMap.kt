package com.gamasoft.cqrs.bootstrap.configuration

import com.gamasoft.cqrs.bootstrap.application.AppConfiguration

data class ConfigurationMap(private val map: Map<String, String>): AppConfiguration {
    override fun get(key: String): String = map[key].orEmpty()
}