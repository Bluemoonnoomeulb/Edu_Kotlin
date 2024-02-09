package classes.calculator

import java.text.ParseException

fun manipulation(example: String) {
    val checkSymbol: (Char) -> Boolean = {
        (it in '0'..'9') or (it == ',') or (it == '.')
    }

    val lexemes = mutableListOf<Lexeme>()

    var item = ""

    for (i in example.indices) {
        if (!checkSymbol(example[i]) and item.isNotEmpty()) {
            lexemes.add(Lexeme(item, "Number"))
            item = ""
        }
        when (example[i]) {
            '+' -> lexemes.add(Lexeme(example[i].toString(), "Operation_plus"))
            '-' -> lexemes.add(Lexeme(example[i].toString(), "Operation_minus"))
            '*' -> lexemes.add(Lexeme(example[i].toString(), "Operation_mul"))
            '/' -> lexemes.add(Lexeme(example[i].toString(), "Operation_div"))
            else -> if (checkSymbol(example[i])) {
                item += example[i]
            } else
                throw ParseException("Неизвестный символ", i)
        }
    }
    if (item.isNotEmpty())
        lexemes.add(Lexeme(item, "Number"))

    lexemes.add(Lexeme("", "EOF"))

    val lexemeBuffer = LexemeBuffer(lexemes)
    println(calculate(lexemeBuffer))
}

fun factor(lexemes: LexemeBuffer): Double {
    val lexemeItem = lexemes.next
    return when (lexemeItem.type) {
        "Number" -> lexemeItem.value.toDouble()
        "Operation_minus" -> -factor(lexemes)
        "Operation_plus" -> +factor(lexemes)
        else -> throw ParseException("Неизвестный тип операции", lexemes.position)
    }
}

fun muldiv(lexemes: LexemeBuffer): Double {
    var value = factor(lexemes)
    while(true) {
        val lexemeItem = lexemes.next
        when(lexemeItem.type) {
            "Operation_mul" -> value *= factor(lexemes)
            "Operation_div" -> value /= factor(lexemes)
            "Operation_minus", "Operation_plus", "EOF" -> {
                lexemes.back()
                return value
            }
            else -> throw ParseException("Неизвестный тип операции", lexemes.position)
        }
    }
}

fun plusminus(lexemes: LexemeBuffer): Double {
    var value = muldiv(lexemes)
    while(true) {
        val lexemeItem = lexemes.next
        when(lexemeItem.type) {
            "Operation_minus" -> value -= muldiv(lexemes)
            "Operation_plus" -> value += muldiv(lexemes)
            "Operation_mul", "Operation_div", "EOF" -> {
                lexemes.back()
                return value
            }
            else -> throw ParseException("Неизвестный тип операции", lexemes.position)
        }
    }
}

fun calculate(lexemes: LexemeBuffer): Double {
    val lexemeItem = lexemes.next
    if (lexemeItem.type == "EOF")
        return 0.0
    lexemes.back()
    return plusminus(lexemes)
}
