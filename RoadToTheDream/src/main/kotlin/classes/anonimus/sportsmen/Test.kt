package classes.anonimus.sportsmen

fun main() {

    val sportsman = Sportsman("Andy")

    sportsman.callWaterboy(object : Waterboy {
        override fun bringWater() {
            println("Вода принесена!")
        }
    })

    sportsman.callWaterboy { println("Вода принесена!") }
}