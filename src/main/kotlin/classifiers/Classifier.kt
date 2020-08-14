package classifiers

import discord4j.core.event.domain.Event
import processor.Processor

interface Classifier {
    fun classify(event: Event): Processor?
}