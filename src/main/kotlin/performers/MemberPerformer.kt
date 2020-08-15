package performers

import championship.League
import championship.Participant
import org.litote.kmongo.eq
import org.nocrala.tools.texttablefmt.Table
import storage.MongoStorage

class MemberPerformer: Performer {

    override fun perform(command: MutableList<String>): String? {
        return when(command.removeAt(0)) {
            "add" -> addMember(command)
            "list" -> listMember(command)
            else -> "Invalid command!"
        }
    }

    private fun addMember(command: MutableList<String>): String {
        val league = MongoStorage.list<League>(League::name eq command[1]) ?: return "League ${command[1]} not found."

        league.addParticipant(command[0])

        MongoStorage.updateByID(league.id!!, league)

        return "Member ${command[0]} added to league ${command[1]}!"
    }

    private fun listMember(command: MutableList<String>): String {
        val league = MongoStorage.list<League>(League::name eq command[0]) ?: return "League ${command[1]} not found."

        val table = Table(2)

        table.addCell("Member")
        table.addCell("Points")

        league.participants.forEach {
            table.addCell(it.name)
            table.addCell(it.points.toString())
        }

        return table.render()
    }
}
