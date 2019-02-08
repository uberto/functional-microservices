package com.gamasoft.cqrs.writeModel

import com.gamasoft.cqrs.event.*
import com.gamasoft.functionalcqrs.eventStore.*
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

sealed class Validated<out A, out B>()

data class Invalid<A>(val err: A): Validated<A, Nothing>()
data class Valid<B>(val value: B): Validated<Nothing, B>()


sealed class Result<out A, out B> {
    class Error<A>(val errorMsg: A): Result<A, Nothing>()
    class Success<B>(val resultValue: B): Result<Nothing, B>()

}

sealed class DomainError(val msg: String)

data class ToDoError(val e: String, val item: ToDoItem): DomainError(e)

typealias CmdResult = Validated<DomainError, Event>
typealias EsScope = EventStore.() -> CmdResult


data class CommandMsg(val command: Command,
                      val response: CompletableDeferred<CmdResult>) // a command with a result


class CommandHandler(val eventStore: EventStore): (CommandMsg) -> Unit {

//
//    //if we need we can have multiple instances
//    val sendChannel =  createActor<CommandMsg> { executeCommand(it) }

    override fun invoke(msg: CommandMsg) {

        val res = processPoly(msg.command)(eventStore)

        runBlocking {
            //we want to reply after sending the event to the store
            if (res is Valid) {
            //    eventStore.sendChannel.send(res.value)
            }
            msg.response.complete(res)
        }
    }

    private fun processPoly(c: Command): EsScope {

        println("Processing ${c}")

        val cmdResult = when (c) {

            is CreateNewToDoItem -> execute(c)
            is EditToDoItem -> execute(c)
            is CancelToDoItem -> execute(c)
            is MarkAsDoneToDoItem -> execute(c)
        }
        return cmdResult
    }

//    fun handle(cmd: Command): CompletableDeferred<CmdResult> {
//
//        val msg = CommandMsg(cmd, CompletableDeferred())
//
//        runBlocking { //use launch to execute commands in parallel slightly out of order
//            sendChannel.send(msg)
//        }
//
//        return msg.response
//    }


}

private fun List<ToDoEvent>.fold(): ToDoItem {
    return this.fold(emptyToDoItem) { i: ToDoItem, e: ToDoEvent -> i.compose(e)}
}



private fun execute(c: CreateNewToDoItem): EsScope = {
    Valid(ToDoCreated(c.userId, "new", c.desc))
    }


private fun execute(c: EditToDoItem): EsScope = {
    val item = getEvents<ToDoEvent>(c.itemId).fold()
    if (item is ActiveToDoItem)
        Valid(ToDoEdited(c.itemId, c.newDesc))
    else
        Invalid(ToDoError("Item not enabled! ${item}", item))
}

private fun execute(c: CancelToDoItem): EsScope = {
    val item = getEvents<ToDoEvent>(c.itemId).fold()
    if (item is ActiveToDoItem)
        Valid(ToDoCancelled(c.itemId))
    else
        Invalid(ToDoError("Item not enabled! ${item}", item))
}

private fun execute(c: MarkAsDoneToDoItem): EsScope = {
    val item = getEvents<ToDoEvent>(c.itemId).fold()
    if (item is ActiveToDoItem)
        Valid(ToDoDone(c.itemId))
    else
        Invalid(ToDoError("Item not enabled! ${item}", item))
}
