import classifiers.EventClassifier
import classifiers.Classifier
import config.ConfigLoader
import discord4j.core.DiscordClient
import discord4j.core.GatewayDiscordClient
import discord4j.core.event.domain.message.MessageCreateEvent
import java.io.FileInputStream
import java.util.*

class ChessBot {
    private val token = ConfigLoader.read("discord.token")
    private val client: DiscordClient
    private val gateway: GatewayDiscordClient?
    private val classifier: Classifier

    init {

        client = DiscordClient.create(token)
        gateway = client.login().block()
        classifier = EventClassifier()

        if(gateway == null)
            throw Exception("Error when trying to create a gateway")

        gateway.on(MessageCreateEvent::class.java).subscribe { event ->
            val processor = classifier.classify(event) ?: return@subscribe
            processor.process(event)
        }

        gateway.onDisconnect().block()
    }

}

fun main() {
    ChessBot()
}