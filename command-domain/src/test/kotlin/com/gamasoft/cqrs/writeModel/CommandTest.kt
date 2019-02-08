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

        val actual = ObjectMapper()
            .enableDefaultTyping()
            .registerKotlinModule()
            .writeValueAsString(cmd)

        assertThat(actual).isEqualTo(json)
    }

    @Test
    fun deserializeFromJson() {

        val command: Command = ObjectMapper()
            .enableDefaultTyping()
            .registerKotlinModule()
            .readerFor(Command::class.java)
            .readValue(json)
        assertThat(command.javaClass.simpleName).isEqualTo("CreateNewToDoItem")
    }
}