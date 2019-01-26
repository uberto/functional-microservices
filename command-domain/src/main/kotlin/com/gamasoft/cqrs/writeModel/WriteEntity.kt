package com.gamasoft.cqrs.writeModel

import com.gamasoft.cqrs.event.*


interface EventComposable<T: Event> {
    fun compose(e: T): EventComposable<T>
}


sealed class ToDoItem(): EventComposable<ToDoEvent>{
    abstract override fun compose(e: ToDoEvent): ToDoItem
    abstract val id: String
}

object emptyToDoItem: ToDoItem(){
    override val id: String = ""

    override fun compose(e: ToDoEvent)= when (e) {

        is ToDoCreated -> ActiveToDoItem(e.key, e.userId, e.description)
        else -> this //ignore other events
    }

}

data class ActiveToDoItem(override val id: String, val userId: String, val description: String) : ToDoItem() {
    override fun compose(e: ToDoEvent)= when (e) {

        is ToDoEdited -> ActiveToDoItem(this.id, this.userId, e.description)
        else -> this //ignore other events
    }
}

//object emptyOrder: Order("") {
//    override fun compose(e: OrderEvent) = when (e) {
//        is Started -> NewOrder(e.phoneNum, emptyList())
//        else -> this //ignore other events
//    }
//}
//
//data class NewOrder(val phoneNum: String, val details: List<OrderDetail>): Order(phoneNum) {
//    override fun compose(e: OrderEvent) = when (e) {
//        is ItemAdded -> NewOrder(phoneNum, details.plus(OrderDetail(e.itemId, e.quantity)))
//        is AddressAdded -> ReadyOrder(phoneNum, e.address, details)
//        is Cancelled -> CancelledOrder(phoneNum)
//        else -> this
//    }
//}
///etc

