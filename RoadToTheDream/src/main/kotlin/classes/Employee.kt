package classes

import java.time.LocalDate

class Employee(val name: String, val position: String, val employmentYear: Int) {

    val experience: Int
        get() = LocalDate.now().year - employmentYear

    fun work() {
        println("Работаю...")
    }
}
