package com.gamasoft.cqrs.writeModel

import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS)
sealed class Command

data class CreateNewToDoItem(val userId: String, val desc: String): Command()

data class EditToDoItem(val itemId: String, val newDesc: String): Command()

data class CancelToDoItem(val itemId: String): Command()

data class MarkAsDoneToDoItem(val itemId: String): Command()


