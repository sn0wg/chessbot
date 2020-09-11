package championship

data class Participant(
    val name: String,
    var points: Int
) {
    fun winGame() {
        this.points+=2
    }
}