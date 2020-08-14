package classifiers

import discord4j.core.event.domain.Event
import discord4j.core.event.domain.message.MessageCreateEvent
import processor.MessageCreateProcessor
import processor.Processor

class MessageClassifier: Classifier {
    override fun classify(event: Event): Processor? {
        return when(event) {
            is MessageCreateEvent -> MessageCreateProcessor()
            else -> null
        }
    }
}