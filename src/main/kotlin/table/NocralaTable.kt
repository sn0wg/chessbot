package table

import org.nocrala.tools.texttablefmt.BorderStyle
import org.nocrala.tools.texttablefmt.CellStyle
import org.nocrala.tools.texttablefmt.ShownBorders
import org.nocrala.tools.texttablefmt.Table
import kotlin.reflect.full.memberProperties

class NocralaTable(override val columns: List<String>): CustomTable(columns) {

    private val cellStyle: CellStyle = CellStyle(CellStyle.HorizontalAlign.LEFT, CellStyle.AbbreviationStyle.DOTS, CellStyle.NullStyle.EMPTY_STRING)
    private val borderStyle: BorderStyle = BorderStyle.CLASSIC
    private val showBorderStyle:ShownBorders = ShownBorders.ALL
    private val escapeXml: Boolean = false
    private val table: Table = Table(columns.size, borderStyle, showBorderStyle, escapeXml)

    init {
        columns.forEach { pushValue(it.capitalize()) }
    }

    override fun addContent(content: Any) {
        content::class.memberProperties
                .filter { columns.contains(it.name) }
                .forEach { pushValue(it.getter.call(content)?.toString() ?: " - ") }
    }

    override fun addContent(content: List<Any>) {
        content.forEach { addContent(it) }
    }

    override fun render(): String = table.render()

    private fun pushValue(value: String) = table.addCell(value, cellStyle)

}