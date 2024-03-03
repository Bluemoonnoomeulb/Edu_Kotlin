package classes.abstracts_interfaces

class Seller(name: String, age: Int): Worker(name, age), Cleaner {
    override fun work() {
        println("Я - $name. Мне $age. Я продаю товар...")
    }

    override fun clean() {
        println("Продавец убирает рабочее место...")
    }
    companion object {
        val test = 5
    }
}
