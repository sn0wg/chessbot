package performers

class LeaguePerformer: Performer {
    override fun perform(command: MutableList<String>): String? {
        return "Created!"
    }

}