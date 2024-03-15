package main.processor.pdf

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.apache.pdfbox.pdmodel.font.*
import java.awt.Color
import java.io.File
import java.io.IOException
import java.lang.Exception


class Document (private val name: String, private val numberRecords: Int) {
    private val document: PDDocument = PDDocument()
    val page: PDPage = PDPage()
    val font: PDFont
    val fontSize = 6
    lateinit var contentStream: PDPageContentStream
        private set

    init {
        document.addPage(page)
        font = PDType0Font.load(document, File("GenPDF/ArialMT.ttf"))
    }

    fun create() {
        startStream()
        createTable()
        stopStream()
        with(document) {
            save(name)
            close()
        }
        val file = File(name)
        println("Файл создан. Путь: ${file.absolutePath}")
    }

    private fun startStream() {
        try {
            contentStream = PDPageContentStream(document, page)
            contentStream.setStrokingColor(Color.DARK_GRAY)
            contentStream.setLineWidth(0.5f)
        } catch (e: IOException) {
            println(e.message)
        }
    }

    private fun stopStream() {
        try {
            contentStream.stroke()
            contentStream.close()
        } catch (e: IOException) {
            println(e.message)
        }
    }

    private fun createTable() {
        if (numberRecords > 0) {
            val table = Table(this, numberRecords)
            table.create()
        } else {
            throw Exception("Ошибка! Отрицательное число записей")
        }
    }
}
