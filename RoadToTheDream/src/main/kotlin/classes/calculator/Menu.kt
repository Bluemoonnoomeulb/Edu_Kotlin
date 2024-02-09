package classes.calculator

import java.text.ParseException
import kotlin.system.exitProcess

const val MENU = "Меню:\n" +
        "1. Открыть калькулятор\n" +
        "2. Открыть FAQ\n" +
        "3. Выйти из приложения"
const val ACTION = "\nВыберите пункт: "
const val FAQ = "Вопрос: Какая цель приложения?\n" +
        "Ответ: Целью игры является вычисления результата выражения, состоящего из чисел и математических операций.\n" +
        "\nВопрос: Какие математические операции поддерживаются?\n" +
        "Ответ: Программа поддерживает следующие мат.операции - \"+\", \"-\", \"*\", \"/\".\n" +
        "\nВопрос: Почему так мало математических операций?\n" +
        "Ответ: Наше приложение использует новую версии ИИ - ленивую ;) Этот искусственный интеллект считается только самые простые операции."

fun main() {
    while (true) {
        println(MENU)
        print(ACTION)
        try {
            when (readln()) {
                "1" -> {
                    print("Введите выражение для вычисления: ")
                    manipulation(readln())
                }

                "2" -> println(FAQ)
                "3" -> exitProcess(0)
                else -> println("Повторите попытку")
            }
        } catch (e: ParseException) {
            println(e.message)
        }
    }
}
