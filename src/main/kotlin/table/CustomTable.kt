package table

abstract class CustomTable(open val columns: List<String>) {

    abstract fun addContent(content: Any)

    abstract fun addContent(content: List<Any>)

    abstract fun render(): String

}