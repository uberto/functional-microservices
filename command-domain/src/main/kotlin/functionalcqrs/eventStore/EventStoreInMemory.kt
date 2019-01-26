package com.gamasoft.functionalcqrs.eventStore


class EventStoreInMemory : EventStore() {
//    override fun getItemEvents(pk: String) = itemEventCache.getOrDefault(pk, emptyList())
//
//    override fun getOrderEvents(pk: String) = orderEventCache.getOrDefault(pk, emptyList())

//    private val listeners: MutableList<SendChannel<Event>> = mutableListOf()
//
//    override val sendChannel = createActor<Event> { processEvents(it) }
//
//    private fun processEvents(event: Event) {
//
//        when (event) {
////            is ItemEvent -> itemEventCache.compute(event.pk) { _, el -> (el ?: emptyList()).plus(event) }
////            is OrderEvent -> orderEventCache.compute(event.pk) { _, el -> (el ?: emptyList()).plus(event) }
//        }
//
//        for (listener in listeners) {
//            GlobalScope.launch { listener.send(event) }
//        }
//
//        println("Processed com.gamasoft.cqrs.event.Event $event")
//    }
//
//    override fun addListener(listener: SendChannel<Event>) {
//        listeners.add(listener)
//    }
//
//    fun saveAllEvents() {
//        //not implemented
//
//    }
//
//    fun loadAllEvents() {
//        //not implemented
//    }

}


