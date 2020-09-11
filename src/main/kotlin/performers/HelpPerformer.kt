package performers

class HelpPerformer: Performer {
    override fun perform(command: MutableList<String>): String? {
        return if(command.isEmpty()) showBasicHelp()
        else {
            when(command.first()) {
                "command" -> showCommandsHelp()
                else -> "Unknow command ${command.first()}"
            }
        }
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
            tabMessage("Try \"c! help <command>\" to see comand subcomands and arguments.")
        ).joinToString("\n")
    }

    private fun tabMessage(message: String, deep: Int = 0) = "${getTab(deep * 4)}$message"

    private fun getTab(size: Int) = mutableListOf<String>().apply { for (i in 0 until size) { this.add(" ")} }.joinToString("")
}