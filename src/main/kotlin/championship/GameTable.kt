package championship

import java.util.*

class GameTable {
    var round: Int = 0
        private set
    private val rounds: MutableList<Round>

    val x = Properties()

    constructor(round: Int, rounds: MutableList<Round>) {
        this.round = round
        this.rounds = rounds
    }

    constructor() {
        rounds = mutableListOf()
    }

    fun addRound(round: Round) = rounds.add(round)

    fun nextRound() {
        round = (round + 1) % rounds.size
    }

    fun getRoundGames(): Round {
        return rounds[round]
    }
}