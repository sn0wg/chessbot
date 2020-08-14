package performers

interface Performer {
    fun perform(command: MutableList<String>): String?
}