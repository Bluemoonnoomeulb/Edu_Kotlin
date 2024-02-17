package classes

fun main() {
    val employee = Employee("Max", "QA Mobile", 2023)
    employee.work()
    employee.toString()
    println("Опыт работы: ${employee.experience}")

    var address1 = Address("Самара", "Ново-Садовая", 201)
    val address2 = address1.copy()
    println("$address1\n${address1.hashCode()}")
    println("$address2\n${address2.hashCode()}")
    println(address1 == address2)
    address1 = address1.copy(city = "Саранск")
    println(address1 == address2)

    val (city, street, house) = address1
    println("$city $street $house")
    val (city2, street2, house2) = address2
    println("$city2 $street2 $house2")
}
