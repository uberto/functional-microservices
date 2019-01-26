package com.gamasoft.functionalcqrs.eventStore

import com.gamasoft.cqrs.event.Event


abstract class EventStore {

//    abstract val sendChannel: SendChannel<Event>
//
//    abstract fun addListener(listener: SendChannel<Event>)
//
    inline fun <reified T: Event> getEvents(pk: String): List<T> {
        return when (T::class) {
//            OrderEvent::class ->  getOrderEvents(pk) as List<T>
//            ItemEvent::class -> getItemEvents(pk) as List<T>
            else -> emptyList()
        }

    }

//    abstract fun getOrderEvents(pk: String): List<OrderEvent>
//
//    abstract fun getItemEvents(pk: String): List<ItemEvent>
}