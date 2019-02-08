package com.gamasoft.cqrs.writeModel

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

class CommandTest {

    val cmd = CreateNewToDoItem("ub", "write the test")
    val json = "{\"@c\":\".CreateNewToDoItem\",\"userId\":\"ub\",\"desc\":\"write the test\"}"

    @Test
    fun serializeToJson() {

        val actual = cmd.toJsonString()

        assertThat(actual).isEqualTo(json)
    }

    @Test
    fun deserializeFromJson() {

        val command = Command.fromJsonString(json)

        assertThat(command.javaClass.simpleName).isEqualTo("CreateNewToDoItem")
    }
}