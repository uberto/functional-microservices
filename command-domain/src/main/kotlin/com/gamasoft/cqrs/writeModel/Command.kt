package com.gamasoft.cqrs.writeModel

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS)
sealed class Command {

    companion object {
        val jsonMapper = ObjectMapper().registerKotlinModule()

        fun fromJsonString(json: String):Command = jsonMapper
            .readerFor(Command::class.java)
            .readValue(json)
    }

    fun toJsonString() = jsonMapper.writeValueAsString(this)
}

data class CreateNewToDoItem(val userId: String, val desc: String): Command()

data class EditToDoItem(val itemId: String, val newDesc: String): Command()

data class CancelToDoItem(val itemId: String): Command()

data class MarkAsDoneToDoItem(val itemId: String): Command()


