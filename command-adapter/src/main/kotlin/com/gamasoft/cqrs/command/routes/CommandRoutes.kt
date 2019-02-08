package com.gamasoft.cqrs.command.routes

import com.gamasoft.cqrs.commons.functional.createActor
import com.gamasoft.cqrs.writeModel.CommandHandler
import com.gamasoft.cqrs.writeModel.CommandMsg
import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK

class CommandRoutes(private val commandHandler: CommandHandler): HttpHandler {

    val channel = createActor<CommandMsg>{ commandHandler(it) }

    override fun invoke(req: Request): Response {

        // val cmdMsg = from request
        // val result = channel.send(cmdMsg)
        // return result.toResponse()
        return Response(OK).body("Here Command, you asked for ${req.uri}!")
    }
}