package championship

data class Match(
    val challenger: String,
    val challenged: String,
    val winner: String? = null
)