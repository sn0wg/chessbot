package performers

import championship.League
import org.litote.kmongo.eq
import storage.MongoStorage

class MatchPerformer: Performer {
    override fun perform(command: MutableList<String>): String? {
        return when(command.removeAt(0)) {
            "result" -> changeResult(command)
            else -> "Invalid command!"
        }
    }

    fun changeResult(command: MutableList<String>): String {
        val league = MongoStorage.list<League>(League::name eq command[0]) ?: return "League ${command[0]} not found."

        league.defineMatchWinner(command[1].toInt(), command[2])

        MongoStorage.updateByID(league.id!!, league)

        return "Saved!"
    }
}