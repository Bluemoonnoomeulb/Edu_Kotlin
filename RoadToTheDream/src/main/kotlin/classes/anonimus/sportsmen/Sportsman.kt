package classes.anonimus.sportsmen

class Sportsman(val name: String) {

    fun callWaterboy(waterboy: Waterboy) {
        waterboy.bringWater()
    }

    inline fun callWaterboy(bringWater: () -> Unit) {
        bringWater()
    }
}