package championship

import org.bson.codecs.pojo.annotations.BsonId

data class League(
    @BsonId
    val id: String? = null,
    val name: String,
    val participants: MutableList<Participant> = mutableListOf(),
    var status: LeagueStatus = LeagueStatus.CREATING,
    val table: GameTable = GameTable()
) {
    fun addParticipant(name: String) {
        if(status != LeagueStatus.CREATING)
            return
        this.participants.add(Participant(name, 0))
    }

    fun start() {
        if(status == LeagueStatus.CREATING) {
            val originalArray = mutableListOf<String>()

            originalArray.addAll(participants.map { it.name })

            originalArray.shuffle()

            val arr1 = mutableListOf<String>()
            val arr2 = mutableListOf<String>()

            arr1.addAll(originalArray.subList(0, originalArray.size/2))
            arr2.addAll(originalArray.subList(originalArray.size/2, originalArray.size))

            for(i in 1 until originalArray.size) {
                val round = Round(mutableListOf())

                for(k in 0 until originalArray.size/2) {
                    round.add(Match(arr1[k], arr2[k]))
                }

                roll(arr1, arr2)

                this.table.addRound(round)
            }

            this.status = LeagueStatus.STARTED
        }
    }

    private fun roll(arr1: MutableList<String>, arr2: MutableList<String>) {
        val first = arr1.removeAt(0)

        arr2.add(arr1.removeAt(arr1.size-1))

        arr1.add(0, arr2.removeAt(0))

        arr1.add(0, first)
    }
}