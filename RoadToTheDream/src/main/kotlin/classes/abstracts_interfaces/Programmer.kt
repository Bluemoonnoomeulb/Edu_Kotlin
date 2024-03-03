package classes.abstracts_interfaces

class Programmer(name: String, age: Int, private val lang: String): Worker(name, age), Cleaner {
    override fun work() {
        println("Я - $name. Мне $age. Я программирую на $lang...")
    }

    override fun clean() {
        println("Программист убирает рабочее место...")
    }
}
