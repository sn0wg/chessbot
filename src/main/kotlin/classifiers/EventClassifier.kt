package classifiers

import discord4j.core.event.domain.Event
import discord4j.core.event.domain.message.MessageEvent
import processor.Processor

class EventClassifier: Classifier {
    override fun classify(event: Event): Processor? {
        return when(event) {
            is MessageEvent -> MessageClassifier().classify(event)
            else -> null
        }
    }
}