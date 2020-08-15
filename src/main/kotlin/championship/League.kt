package championship

import org.bson.codecs.pojo.annotations.BsonId

data class League(
        @BsonId
        val id: String? = null,
        val name: String,
        val participants: MutableList<Participant> = mutableListOf()
) {
        fun addParticipant(name: String) = this.participants.add(Participant(name, 0))
}

data class Participant(
        val name: String,
        val points: Int
)