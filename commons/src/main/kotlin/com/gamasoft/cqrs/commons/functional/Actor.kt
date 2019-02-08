package com.gamasoft.cqrs.commons.functional

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun <T> createActor(block: suspend (T)->Unit): SendChannel<T> {
    return GlobalScope.actor {

        for (qm in channel) {
            block(qm)
        }
    }
}

//
//fun CoroutineScope.produceNumbers() = produce<Int> {
//    var x = 1 // start from 1
//    while (true) {
//        send(x++) // produce next
//        delay(100) // wait 0.1s
//    }
//}
//
//fun CoroutineScope.launchProcessor(id: Int, channel: ReceiveChannel<Int>) = launch {
//    for (msg in channel) {
//        println("Processor #$id received $msg")
//    }
//}