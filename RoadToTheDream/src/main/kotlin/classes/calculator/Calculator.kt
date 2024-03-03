package classes.calculator

import classes.calculator.Lexeme.*
import java.text.ParseException

object Calculator {
    fun getAmount(lexemes: LexemeBuffer): Double {
        var flag = true
        var value = getMultiplication(lexemes)

        while (flag) {
            when (lexemes.next) {
                OperationMinus -> value -= getMultiplication(lexemes)
                OperationPlus -> value += getMultiplication(lexemes)
                OperationMul, OperationDiv, EOF -> {
                    lexemes.back()
                    flag = false
                }
                else -> throw ParseException("Неизвестный тип операции", lexemes.position)
            }
        }
        return value
    }

    fun calculateFactor(lexemes: LexemeBuffer): Double {
        val lexemeItem = lexemes.next
        return lexemeItem.getFactor(lexemes)
    }

    private fun getMultiplication(lexemes: LexemeBuffer): Double {
        var flag = true
        var value = calculateFactor(lexemes)

        while (flag) {
            when (lexemes.next) {
                OperationMul -> value *= calculateFactor(lexemes)
                OperationDiv -> value /= calculateFactor(lexemes)
                OperationPlus, OperationMinus, EOF -> {
                    lexemes.back()
                    flag = false
                }
                else -> throw ParseException("Неизвестный тип операции", lexemes.position)
            }
        }
        return value
    }
}
