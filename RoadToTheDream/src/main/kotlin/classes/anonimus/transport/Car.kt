package classes.anonimus.transport

import kotlin.random.Random

class Car(override var name: String = "Машина") : Transport(name) {

    override fun drive() {
        println("$name едет...")
    }

    fun startEngine(): Boolean = Random.nextBoolean()
}