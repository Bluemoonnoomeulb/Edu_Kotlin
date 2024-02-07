package collections

fun main() {
    val revenueByMonth = mapOf(
        "Ноябрь" to listOf(600, 400, 100, 300),
        "Декабрь" to listOf(560, 900, 1000, 1500),
        "Январь" to listOf(100, 200, 300, 400),
        "Февраль" to listOf(-90, 450, 500, 700),
        "Март" to listOf(300, 200, 150, 230)
    )

    printInfo(revenueByMonth)
}

fun printInfo(data: Map<String, List<Int>>) {
    with(data) {
        val predicate: (item: Int) -> Boolean = { item -> item >= 0 }
        val allPositive = filter {
            it.value.all(predicate) }

        println("Average revenue per week: ${ allPositive.flatMap { it.value }.average()}")

        println("Average revenue per month: ${ allPositive.map { it.value.sum() }.average() }")

        val maxRev = allPositive.map { it.value.sum() }.maxOrNull() ?: 0

        println("Maximum revenue per month: $maxRev\n" +
                "Months: ${ filter { it.value.sum() == maxRev }
                    .map { it.key } }")

        val minRev = filter { it.value
            .all { weeklyRevenue -> weeklyRevenue >= 0 } }
            .map { it.value.sum() }.minOrNull() ?: 0

        println("Minimum revenue per month: $minRev\n" +
                "Months: ${ filter { it.value.sum() == minRev }
                    .map { it.key } }")

        println("Months with negative revenue: ${ filter { it.value
            .any { weeklyRevenue -> weeklyRevenue < 0 } }
            .map { it.key } }")
    }
}