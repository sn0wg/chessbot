package championship

data class Round(
        val games: MutableList<RoundGame>
) {
    fun addGame(game: RoundGame) = games.add(game)
}