package sequences

/*
        Сгенерировать бесконечную последовательность строк:
        "Сотрудник №1" и т.д.
        Вывести на экран первые 100 элементов.
 */

fun main() {
    solve1()
}

fun solve1() {
    var counter = 1
    val sequence = generateSequence { "Сотрудник №${counter++}" }
        .take(100)

    sequence.forEach { println(it) }
}