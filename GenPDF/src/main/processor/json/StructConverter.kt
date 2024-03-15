package main.processor.json

import org.json.JSONArray
import org.json.JSONObject

object  StructConverter {
    lateinit var jsonArray: JSONArray

    /*
        Метод приведения к некоторой однообразной структуре ответа JSON.
        {"ALL":[{},{}]}.
        Получаем из JSON -> JSONArray.
        Даже если исходный JSON содержал один элемент, а не список, то работаем с ним, как с элементом списка.
    */
    private fun modifyStructure(json: String): String {
        val finalJSON: (String) -> String = {
            if (it[0] != '[')
                "[$json]"
            else
                json
        }
        return "{\"All\":${finalJSON(json)}}"
    }

    fun splitData(json: String) {
        val obj = JSONObject(modifyStructure(json))
        jsonArray = obj.getJSONArray("All")
    }
}