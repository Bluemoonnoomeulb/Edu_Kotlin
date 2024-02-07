package collections

/*
        1. Создать коллекцию имен
        2. Получить новую коллекцию, имена в которой
    начинаются с буквы "М"
 */

fun solve1() {
    val names = arrayListOf<String>("Анна", "Максим", "Петр", "Виктор", "Милана", "макар", "Юля")
    val namesStartM = names.filter { it.startsWith("М", true) }

    for (name in namesStartM)
        print("$name ")
}