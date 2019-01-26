package com.gamasoft.cqrs.readModel

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking

data class QueryMsg(val query: Query<out ReadEntity>, val response: CompletableDeferred<List<ReadEntity>>) // a request with reply

class QueryHandler {

//    val queryChannel = createActor { qm: QueryMsg -> qm.response.complete(processQuery(qm.query)) }
//
//    val eventChannel = createActor { e: com.gamasoft.cqrs.event.Event -> processEvent(e) }

//    private val items = mutableMapOf<String, ReadItem>()
//    private val orders = mutableMapOf<String, ReadOrder>()

//    private fun processEvent(e: com.gamasoft.cqrs.event.Event): ReadEntity? {

//        return when (e){
////            is ItemEvent -> when (e) {
//
////                is ItemCreated ->  {
////                    items.put(e.itemId, ReadItem(e.desc, e.price, true))
////                }
//            }
////            is OrderEvent -> when (e){
////                is Started -> orders.put(e.phoneNum, ReadOrder(OrderStatus.new, e.phoneNum, 0.0, null, mutableListOf()))
//
//        }

//        return null
//    }

    private fun processQuery(q: Query<out ReadEntity>): List<ReadEntity> {
        println("Processing $q")

//        return when(q){
////            GetAllOpenOrders -> orders.values.filter { it.status in setOf(OrderStatus.new, OrderStatus.ready) }
////            is GetOrder -> orders.get(q.phoneNum)?.run { listOf(this)}?: emptyList()
////etc.
//        }
        return emptyList()
    }




    fun handle(q: Query<out ReadEntity>):List<ReadEntity> {

        val msg = QueryMsg(q, CompletableDeferred())

        runBlocking {
//            queryChannel.send(msg)

            msg.response.await()

        }
        return msg.response.getCompleted()

    }

}

