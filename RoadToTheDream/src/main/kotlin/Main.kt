class MapManager() {
    private var pointsMap: MutableMap<String, Point> = linkedMapOf()

    fun showPoint(title: String, coordinate: Point) {
        pointsMap[title] = coordinate
    }

    fun onMapClick(clickable: (String, Point) -> Unit) {
        var coordinates: String
        var x: Int
        var y: Int

        while (true)
        {
            coordinates = readlnOrNull() ?: break

            x = coordinates.substringBefore(";").toInt()
            y = coordinates.substringAfter(";").toInt()

            if (x in 0 until 1000 && y in 0 until 1000) {
                val necessaryEntry = pointsMap.entries.find { it.value == Point(x, y) } ?: continue
                clickable.invoke(necessaryEntry.key, necessaryEntry.value)
            }
        }
    }
}

fun main() {
    val orderManager = OrderManager()
    val mapManager = MapManager()

    orderManager.collectOrders { order ->
        mapManager.showPoint(order.goal, order.coordinate)
    }

    mapManager.onMapClick { title, point ->
        println("$title по координатам (${point.x};${point.y})")
    }
}

data class Point(val x: Int, val y: Int)
data class Order(val id: Int, val goal: String, val coordinate: Point)

class OrderManager {
    private val orders = arrayOf(Order(1, "Дроид TT-20", Point(50, 30)), Order(2, "Дроид Т-1000", Point(100, 100)))

    fun collectOrders(onNotify: (Order) -> Unit) {
        for (order in orders)
            onNotify.invoke(order)
    }
}