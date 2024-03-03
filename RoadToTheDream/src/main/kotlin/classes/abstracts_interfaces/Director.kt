package classes.abstracts_interfaces

class Director(name: String, age: Int): Worker(name, age) {
    override fun work() {
        println("Я - $name. Мне $age. Я делегирую задачи...")
    }
}
