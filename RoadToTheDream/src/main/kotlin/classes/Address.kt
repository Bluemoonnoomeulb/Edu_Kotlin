package classes

import java.util.Objects

/*
    1. Создайте data-класс Адрес, конструктор которого содержит
    обязательные поля: название города, улицы и номер дома.

    2. В методе main создайте объект этого класса.

    3. Создайте второй такой же объект, используя метод copy().

    4. Выведите на экран значения этих объектов, их хэш-коды и результат
    сравнения объектов по equals().

    5. Используя деструктор разложите объект на составляющие, сохранив
    значения в соответствующие переменные.

    6. Удалите ключевое слово даа и реализуйте все необходимые методы,
    чтобы работа метода main() осталась прежней.
 */

/*data class Address(
    val city: String,
    val street: String,
    val houseNumber: Int)
 */

class Address(var city: String, val street: String, val houseNumber: Int) {

    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true
        if (javaClass != other?.javaClass)
            return false

        other as Address
        if ((this.city == other.city) and (this.street == other.street) and (this.houseNumber == other.houseNumber))
            return true
        return false
    }

    override fun hashCode(): Int {
        return city.hashCode() + street.hashCode() + houseNumber
    }

    override fun toString(): String {
        return "${javaClass.simpleName}(city=$city, street=$street, houseNumber=$houseNumber)"
    }

    fun copy(): Address {
        return Address(city, street, houseNumber)
    }

    operator fun component1() = city
    operator fun component2() = street
    operator fun component3() = houseNumber
}
