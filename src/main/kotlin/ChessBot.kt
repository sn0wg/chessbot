import discord4j.core.DiscordClient
import discord4j.core.GatewayDiscordClient
import discord4j.core.event.domain.message.MessageCreateEvent

class ChessBot {
    private val token = "NzQxNzk5MzQ5NTE1ODQ1NjY1.Xy80eg.SS5Xnlf5EHTPp-UwLDk7WF1YNYU"
    private val client: DiscordClient
    private  val gateway: GatewayDiscordClient?

    init {
        client = DiscordClient.create(token)
        gateway = client.login().block()

        if(gateway == null)
            throw Exception("Error when trying to create a gateway")

        gateway.on(MessageCreateEvent::class.java).subscribe { event ->
            val message = event.message
            if(message.content == "!ping"){
                val channel = message.channel.block()
                channel.createMessage("Pong!").block()
            }
        }

        gateway.onDisconnect().block()
    }

}

fun main() {
    ChessBot()
}