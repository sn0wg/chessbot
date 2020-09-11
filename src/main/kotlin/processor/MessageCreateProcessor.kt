package processor

import discord4j.core.event.domain.Event
import discord4j.core.event.domain.message.MessageCreateEvent
import performers.*

class MessageCreateProcessor: Processor {
    override fun process(event: Event) {
        val message = (event as MessageCreateEvent).message

        if(message.author.get().isBot)
            return

        val command = message.content.split(" ").toMutableList()

        val performer = createPerformer(command) ?: return

        val response = performer.perform(command) ?: return

        val channel = message.channel.block() ?: return

        channel.createMessage(response).block()
    }

    private fun createPerformer(command: MutableList<String>): Performer? {
        if(command.size > 0) {
            if(command.removeAt(0) == "c!") {
                return when(command.removeAt(0).trim()) {
                    "league" -> LeaguePerformer()
                    "member" -> MemberPerformer()
                    "match" -> MatchPerformer()
                    "help" -> HelpPerformer()
                    else -> null
                }
            }
        }
        return null
    }
}