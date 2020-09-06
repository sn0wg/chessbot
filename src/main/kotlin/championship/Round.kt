package championship

data class Round(
        val matches: MutableList<Match>
) {
    fun add(match: Match) = matches.add(match)
}