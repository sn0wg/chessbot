package performers

class HelpPerformer: Performer {
    override fun perform(command: MutableList<String>): String? {
        return if(command.isEmpty()) showBasicHelp()
        else {
            when(command.first()) {
                "command" -> showCommandsHelp()
                else -> subClassify(command = command[0])
            }
        }
    }

    private fun subClassify(command: String): String {
        return when(command) {
            "league" -> showLeagueHelp()
            "member" -> showMemberHelp()
            "match" -> showMatchHelp()
            else -> "Unknow command ${command.first()}"
        }
    }

    fun showLeagueHelp(): String {
        return mutableListOf(
            tabMessage("League help:"),
            tabMessage("c! league create <league_name> : Create a league", 1),
            tabMessage("c! league list : List all leagues", 1),
            tabMessage("c! league start <league_name>  : Start a league", 1),
            tabMessage("c! league show <league_name>  : Show the members of a league", 1)
        ).joinToString("\n")
    }

    fun showMemberHelp(): String {
        return mutableListOf(
            tabMessage("Member help:"),
            tabMessage("c! member add <member_name> <league_name> : Add a member to a league", 1),
            tabMessage("c! member list <league_name>: List all members of a league", 1)
        ).joinToString("\n")
    }

    fun showMatchHelp(): String {
        return mutableListOf(
            tabMessage("Match help:"),
            tabMessage("c! match result <league_name> <match_index> <match_result> : Define a winner of a match ", 1)
        ).joinToString("\n")
    }

    fun showBasicHelp(): String {
        return mutableListOf<String>(
                tabMessage("ChessBot help:"),
                tabMessage("c! <command> [<subcomand>] [<args>] : Basic command format", 1),
                tabMessage("Try \"c! help command\" to see comands options.")
        ).joinToString("\n")
    }

    fun showCommandsHelp(): String {
        return mutableListOf<String>(
            tabMessage("ChessBot commands:"),
            tabMessage("!c league [<subcomand>] [<args>]: Manage leagues", 1),
            tabMessage("!c member [<subcomand>] [<args>]: Manage members of leagues", 1),
            tabMessage("!c match [<subcomand>] [<args>]: Manage matches of leagues", 1),
            tabMessage("!c help [<subcomand>] [<args>]: Show help", 1),
            tabMessage("Try \"c! help <command>\" to see subcomands and arguments of a command.")
        ).joinToString("\n")
    }

    private fun tabMessage(message: String, deep: Int = 0) = "${getTab(deep * 4)}$message"

    private fun getTab(size: Int) = mutableListOf<String>().apply { for (i in 0 until size) { this.add(" ")} }.joinToString("")
}