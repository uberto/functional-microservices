package com.gamasoft.cqrs.commons.configuration

import com.gamasoft.cqrs.commons.application.AppConfiguration

data class ConfigurationMap(private val map: Map<String, String>): AppConfiguration {
    override fun get(key: String): String = map[key].orEmpty()
}