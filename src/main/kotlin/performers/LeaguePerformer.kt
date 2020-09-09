package performers

import championship.League
import org.litote.kmongo.*
import org.nocrala.tools.texttablefmt.BorderStyle
import org.nocrala.tools.texttablefmt.CellStyle
import org.nocrala.tools.texttablefmt.ShownBorders
import org.nocrala.tools.texttablefmt.Table
import storage.MongoStorage
import table.CustomTable
import table.NocralaTable

class LeaguePerformer: Performer {
    override fun perform(command: MutableList<String>): String? {
        return when(command.removeAt(0)) {
            "create" -> create(command)
            "list" -> list()
            "start" -> start(command)
            "show" -> show(command)
            else -> "Invalid command."
        }
    }

    private fun create(command: MutableList<String>): String {
        val league = League(name = command.removeAt(0))
        MongoStorage.save(league)
        return "League ${league.name} created!"
    }

    private fun list(): String {

        val table: CustomTable = NocralaTable(listOf("name", "status"))

        table.addContent(MongoStorage.list<League>())

        return table.render()

    }

    private fun start(command: MutableList<String>): String {
        val league = MongoStorage.list<League>(League::name eq command[0]) ?: return "League ${command[0]} not found."

        league.start()

        MongoStorage.updateByID<League>(league.id!!, league)

        return "League ${command[0]} started!"
    }

    private fun show(command: MutableList<String>): String {
        val league = MongoStorage.list<League>(League::name eq command[0]) ?: return "League ${command[0]} not found."

        val table: CustomTable = NocralaTable(listOf("challenger", "challenged", "winner"))

        table.addContent(league.table.roundGames().matches)

        return "Round: ${league.table.round + 1} \n ${table.render()}"
    }

}