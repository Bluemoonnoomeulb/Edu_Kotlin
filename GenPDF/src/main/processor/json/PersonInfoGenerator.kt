package main.processor.json

import main.Person
import main.api.ExternalAPI
import main.api.URI
import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*


object PersonInfoGenerator {
    /*
        Метод, определяющий соответствие города рождения и проживания.
        true -> соответствует.
     */
    private fun matchCity(): Boolean = Random().nextBoolean()

    //  Метод генерации индекса.
    private fun generateIndex(count: Int): Set<Int> {
        val random = Random()
        val numberSet: MutableSet<Int> = HashSet()
        var length = 0

        while (length < count) {
            numberSet.add(random.nextInt(100000, 1000000))
            if (numberSet.size == length + 1) {
                length++
            }
        }
        return numberSet
    }

    private fun countYears(birthDate: String): Int {
        val startDate = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        val endDate = LocalDate.now()
        val period = Period.between(startDate, endDate)
        return period.years
    }

    //  БЛОК МОДИФИКАЦИИ ДАННЫХ, ПОЛУЧЕННЫХ ОТ API.

    private fun modifyGender(gender: String): String = gender.substring(0, 3).uppercase(Locale.getDefault())

    private fun modifyCity(city: String): String = city.substring(3)

    private fun modifyStreet(street: String): String {
           return if (street.contains("ул."))
               street.substring(0, street.indexOf("ул."))
           else
               street
    }

    private fun modifyDate(date: String): String {
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val resultDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        return resultDate.format(formatter)
    }

    fun assembleAllPersonsInfo(): List<Person> {
        val result: MutableList<Person> = mutableListOf()
        val persons: JSONArray = StructConverter.jsonArray
        val indexes = generateIndex(persons.length()).toIntArray()

        persons.forEachIndexed { index, _ ->
            val person = persons.getJSONObject(index)
            val cityBirth: () -> String = {
                if (!matchCity()) {
                    val obj = JSONObject(ExternalAPI.executeRequest(URI.CITY))
                    obj.getString("City")
                } else {
                    person.getString("City")
                }
            }

            result += Person(
                person.getString("FirstName"),
                person.getString("LastName"),
                person.getString("FatherName"),
                modifyGender(person.getString("Gender")),
                countYears(person.getString("DateOfBirth")).toString(),
                modifyDate(person.getString("DateOfBirth")),
                modifyCity(cityBirth()),
                indexes[index].toString(),
                person.getString("Country"),
                person.getString("Region"),
                modifyCity(person.getString("City")),
                modifyStreet(person.getString("Street")),
                person.getInt("House").toString(),
                person.getInt("Apartment").toString()
            )
        }

        return result
    }
}
