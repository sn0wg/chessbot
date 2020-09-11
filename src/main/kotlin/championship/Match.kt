package championship

data class Match(
    val challenger: String,
    val challenged: String,
    var winner: String? = null
) {
    fun winner(name: String) {
        this.winner = name
    }
}