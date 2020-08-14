package processor

import discord4j.core.event.domain.Event

interface Processor {
    fun process(event: Event)
}