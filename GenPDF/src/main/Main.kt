package main

import main.processor.pdf.Document

fun main() {
    print("Введите количество записей для генерации: ")
    val numberRecords = readln().toInt()

    val document = Document("result.pdf", numberRecords)
    document.create()
}
