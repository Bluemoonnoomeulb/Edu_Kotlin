package classes.anonimus.transport

class Bicycle : Transport("Велосипед") {

    override fun drive() {
        println("$name едет...")
    }
}