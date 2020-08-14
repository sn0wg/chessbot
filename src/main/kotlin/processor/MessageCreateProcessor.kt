package processor

import discord4j.core.event.domain.Event
import discord4j.core.event.domain.message.MessageCreateEvent

class MessageCreateProcessor: Processor {
    override fun process(event: Event) {
        val message = (event as MessageCreateEvent).message
        if(message.content == "!ping"){
            val channel = message.channel.block()
            channel.createMessage("Pong!").block()
        }
    }
}