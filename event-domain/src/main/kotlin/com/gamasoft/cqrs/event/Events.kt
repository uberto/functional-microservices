package com.gamasoft.cqrs.event

import java.time.Instant


sealed class Event() {
    abstract val key: String
    val created = Instant.now()
    val version = 0
}


sealed class ToDoEvent(): Event()

data class ToDoCreated(override val key: String,
                           val userId: String,
                           val description: String): ToDoEvent()

data class ToDoEdited(override val key: String,
                           val description: String): ToDoEvent()
data class ToDoDone(override val key: String): ToDoEvent()
data class ToDoCancelled(override val key: String): ToDoEvent()
