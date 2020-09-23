package performers

import championship.League
import championship.Participant
import org.litote.kmongo.eq
import org.nocrala.tools.texttablefmt.Table
import storage.MongoStorage
import table.CustomTable
import table.NocralaTable

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

        val league = MongoStorage.list<League>(League::name eq command[0]) ?: return "League ${command[0]} not found."

        val table: CustomTable = NocralaTable(listOf("name", "points"))

        table.addContent(league.participants.apply { this.sortByDescending { it.points } })

        return table.render()

    }
}
