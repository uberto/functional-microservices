package com.gamasoft.functionalcqrs.application

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.actor


fun <T> createActor(block: suspend (T)->Unit): SendChannel<T> {
    return GlobalScope.actor {

        for (qm in channel) {
            block(qm)
        }
    }
}
