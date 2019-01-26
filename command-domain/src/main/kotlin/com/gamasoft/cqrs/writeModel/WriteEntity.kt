package com.gamasoft.cqrs.writeModel

import com.gamasoft.cqrs.event.*


interface EventComposable<T: Event> {
    fun compose(e: T): EventComposable<T>
}


sealed class ToDoItem(): EventComposable<ToDoEvent>{
    abstract override fun compose(e: ToDoEvent): ToDoItem
    abstract val id: String

    val ignoreIt = this
}

object emptyToDoItem: ToDoItem(){
    override val id: String = ""

    override fun compose(e: ToDoEvent)= when (e) {

        is ToDoCreated -> ActiveToDoItem(e.key, e.userId, e.description)
        is ToDoEdited -> ignoreIt
        is ToDoDone -> ignoreIt
        is ToDoCancelled -> ignoreIt
    }

}

data class ActiveToDoItem(override val id: String, val userId: String, val description: String) : ToDoItem() {
    override fun compose(e: ToDoEvent)= when (e) {

        is ToDoEdited -> ActiveToDoItem(this.id, this.userId, e.description)
        is ToDoCreated -> ignoreIt
        is ToDoDone -> DoneItem(this.id, this.userId, this.description)
        is ToDoCancelled -> CancelledItem(this.id, this.userId)
    }
}

data class DoneItem(override val id: String, val userId: String, val description: String) : ToDoItem() {
    override fun compose(e: ToDoEvent)= ignoreIt
}

data class CancelledItem(override val id: String, val userId: String) : ToDoItem() {
    override fun compose(e: ToDoEvent)= ignoreIt
}

