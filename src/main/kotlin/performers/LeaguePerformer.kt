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
            else -> "Invalid command."
        }
    }

    private fun create(command: MutableList<String>): String {
        val league = League(name = command.removeAt(0))
        MongoStorage.save(league)
        return "League ${league.name} created!"
    }

    private fun list(): String {

        val table: CustomTable = NocralaTable(listOf("name"))

        table.addContent(MongoStorage.list<League>())

        return table.render()

    }

}