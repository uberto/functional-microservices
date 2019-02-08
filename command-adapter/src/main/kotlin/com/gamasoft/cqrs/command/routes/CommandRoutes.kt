package com.gamasoft.cqrs.command.routes

import com.gamasoft.cqrs.commons.functional.createActor
import com.gamasoft.cqrs.writeModel.*
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import org.http4k.core.HttpHandler
import org.http4k.core.Method.*
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.routes

class CommandRoutes(private val commandHandler: CommandHandler): HttpHandler {

    val sendChannel = createActor<CommandMsg>{ commandHandler(it) }

    private val executeCommand: HttpHandler = { req ->
        runBlocking {
            CommandMsg(req.toCmd(), CompletableDeferred()).let {
                sendChannel.send(it)
                it.response.toResponse()
            }
        }
    }

    override fun invoke(req: Request): Response =
        routes(
            "/execute" bind POST to executeCommand,
            "/hello" bind { _: Request -> Response(OK).body("Hello! ${req.uri}") },
            "/" bind { _: Request -> Response(OK).body("You looked for ${req.uri}") }
        )(req)
    }

private fun CompletableDeferred<CmdResult>.toResponse(): Response =
    Response(OK).body( toString())



private fun Request.toCmd(): Command = CreateNewToDoItem("1",  bodyString())

