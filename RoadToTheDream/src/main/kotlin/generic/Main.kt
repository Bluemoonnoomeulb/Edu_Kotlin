package generic

fun main() {
    val list1 = MyArrayList.myListOf(1, 2, 3, 4)
    val list2 = MyArrayList<String>()

    list2.add("Ворк")
    list2.add("Не волк")

    for (i in 0 until list1.size())
        println(list1.get(i))

    for (i in 0 until list2.size())
        println(list2.get(i))
}