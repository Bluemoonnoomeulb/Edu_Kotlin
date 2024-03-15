package main.api

enum class URI(var url: String = "https://api.randomdatatools.ru/", var params: String = "") {
    CITY(params = "?unescaped=false&params=City"),
    MAIN(params = "?typeName=all&unescaped=false&" +
            "params=LastName,FirstName,FatherName,Gender,DateOfBirth,Country,Region,City,Street,Apartment,House")
}