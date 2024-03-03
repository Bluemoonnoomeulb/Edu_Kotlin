package classes.anonimus.transport

fun main() {
    val car: Transport = Car()
    val bicycle: Transport = Bicycle()

    if (car is Car)
        car.name = "Спортивная машина"

    println(car.name)
    travel(car)

    // но если нам нужно передать объект единожды, то можно не создавать дополнительный класс
    travel(object : Transport("Самолет") {
        override fun drive() {
            println("$name летит...")
        }
    })
}

fun travel(transport: Transport) {
    transport.drive()
}