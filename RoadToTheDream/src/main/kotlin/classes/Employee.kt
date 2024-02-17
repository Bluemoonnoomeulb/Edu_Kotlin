package classes

import java.time.LocalDate

class Employee(val name: String, val position: String, val employmentYear: Int) {

    val experience: Int
        get() = LocalDate.now().year - employmentYear

    fun work() {
        println("Работаю...")
    }

    override fun toString(): String {
        return "Имя: ${this.name}\n" +
                "Должность: ${this.position}\n" +
                "Год трудоустройства: ${this.employmentYear}"
    }
}
