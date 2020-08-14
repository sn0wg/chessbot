package performers

import org.litote.kmongo.*

class LeaguePerformer: Performer {
    override fun perform(command: MutableList<String>): String? {
        return "Created!"
    }

}