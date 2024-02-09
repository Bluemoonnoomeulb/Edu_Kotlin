package classes

class Dog {
    var name = ""
        get() = field.lowercase().replaceFirstChar { it - 32 }
    var age = 0
        set(value) {
            if (value >= 0)
                field = value
        }
    var weight = 0
        set(value) {
            if (value >= 0)
                field = value
        }
}
