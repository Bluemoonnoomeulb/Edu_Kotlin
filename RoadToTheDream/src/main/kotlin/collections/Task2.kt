package collections

/*
        1. Создать коллекцию случайных целых чисел в диапазоне от 0 до
    1000 размером 1000 элементов
        2. Произвести следующие манипуляции:
            а) Оставить только те числа, которые без остатка делятся на 5 или на 3
            б) Каждое число из полученной коллекции возвести в квадрат
            в) Полученную коллекцию отсортировать по убыванию
            г) Преобразовать элементы коллекции к типу String

        Пример: исходная коллекция: 5, 30, 4, 10
        На выходе должно получиться: "900", "100", "25"
 */

fun solve2() {
    val list = List(1000) { (Math.random() * 1000).toInt() }

    val result = list.filter { (it % 5 == 0) || (it % 3 == 0) }
        .map { it * it }.sortedDescending().map { it.toString() }

    for (i in result)
        println(i)
}