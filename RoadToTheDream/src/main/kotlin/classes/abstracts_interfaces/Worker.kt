package classes.abstracts_interfaces

abstract class Worker(val name: String, val age: Int) {
    open fun work() {
        println("Я - $name. Мне $age. Я работаю...")
    }
}
