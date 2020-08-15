package performers

import championship.League
import org.litote.kmongo.*
import org.nocrala.tools.texttablefmt.BorderStyle
import org.nocrala.tools.texttablefmt.CellStyle
import org.nocrala.tools.texttablefmt.ShownBorders
import org.nocrala.tools.texttablefmt.Table
import storage.MongoStorage

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
        val table = Table(1, BorderStyle.CLASSIC, ShownBorders.ALL, false)
        table.addCell("League")
        val cs = CellStyle(CellStyle.HorizontalAlign.LEFT, CellStyle.AbbreviationStyle.DOTS, CellStyle.NullStyle.EMPTY_STRING)
        MongoStorage.list<League>().forEach {
            table.addCell(it.name, cs)
        }
        return table.render()
    }

}