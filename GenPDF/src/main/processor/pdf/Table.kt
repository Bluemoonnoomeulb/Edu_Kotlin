package main.processor.pdf

import main.Person
import main.processor.json.PersonInfoGenerator
import main.api.ExternalAPI
import main.api.URI
import main.processor.json.StructConverter

class Table(private val document: Document, private val rowCount: Int) {
    private val colCount = 14
    private val cellHeight = 8f
    private var coordinateX = 5f
    private var coordinateY = document.page.cropBox.height - 10
    private val headers = Person(
        "Фамилия", "Имя", "Отчество", "Пол", "Возраст",
        "Дата рождения", "Место рождения", "Индекс", "Страна", "Область", "Город", "Улица",
        "Дом", "Квартира"
    )
    private val widthArray = floatArrayOf(53f, 50f, 55f, 20f, 27f, 50f, 50f, 28f, 33f, 67f, 50f, 67f, 20f, 30f)

    fun create() {
        val json = ExternalAPI.executeRequest(URI.MAIN, rowCount)
        StructConverter.splitData(json)

        val persons = PersonInfoGenerator.assembleAllPersonsInfo()
        for (i in 0 .. persons.size) {
            coordinateX = 5f
            val row = if (i == 0) headers else persons[i-1]
            createRowWithInfo(
                row.component1(),
                row.component2(),
                row.component3(),
                row.component4(),
                row.component5(),
                row.component6(),
                row.component7(),
                row.component8(),
                row.component9(),
                row.component10(),
                row.component11(),
                row.component12(),
                row.component13(),
                row.component14()
            )
            coordinateY -= cellHeight
        }
    }

    private fun createRowWithInfo(vararg fields: String) {
        for (index in 0 until colCount) {
            createCell(coordinateX, coordinateY, index, fields[index])
            coordinateX += widthArray[index]
        }
    }

    private fun createCell(x: Float, y: Float, index: Int, info: String) {
        with(document.contentStream) {
            addRect(x, y, widthArray[index], -cellHeight)
            beginText()
            newLineAtOffset(x + 1, y - cellHeight + 2)
            setFont(document.font, document.fontSize.toFloat())
            showText(cutText(info, widthArray[index]))
            endText()
        }
    }

    private fun cutText(currentString: String, cellWidth: Float): String {
        var substring = currentString
        var lineWidth = (document.font.getStringWidth(substring) / 1000) * document.fontSize;

        while (lineWidth > cellWidth) {
            substring = substring.substring(0, substring.length - 1);
            lineWidth = (document.font.getStringWidth(substring) / 1000) * document.fontSize;
        }

        return substring
    }
}