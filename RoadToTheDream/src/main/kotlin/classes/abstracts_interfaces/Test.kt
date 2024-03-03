package classes.abstracts_interfaces

fun main() {
    val workers = mutableListOf<Worker>()
    workers.add(Seller("Garry", 26))
    workers.add(Seller("Tom", 43))
    workers.add(Programmer("Max", 22, "Kotlin"))
    workers.add(Director("Ken", 50))

    workers.forEach {
        it.work()
        if (it is Cleaner)
            it.clean()
    }
    println()

    val cleaners = workers.filter { it is Cleaner }.map { it as Cleaner }
    cleaners.forEach { it.clean() }

    Seller.test
}
