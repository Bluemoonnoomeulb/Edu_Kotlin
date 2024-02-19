package classes.calculator

import classes.calculator.Lexeme.*
import classes.calculator.Lexeme.Number
import java.text.ParseException

object Calculator {
    fun getAmount(lexemes: LexemeBuffer): Double {
        var value = getMultiplication(lexemes)
        while (true) {
            when (lexemes.next) {
                OperationMinus -> value -= getMultiplication(lexemes)
                OperationPlus -> value += getMultiplication(lexemes)
                OperationMul, OperationDiv, EOF -> {
                    lexemes.back()
                    return value
                }
                else -> throw ParseException("Неизвестный тип операции", lexemes.position)
            }
        }
    }

    private fun getFactor(lexemes: LexemeBuffer): Double {
        return when (val lexemeItem = lexemes.next) {
            is Number -> lexemeItem.value
            is OperationMinus -> -getFactor(lexemes)
            is OperationPlus -> +getFactor(lexemes)
            else -> throw ParseException("Неизвестный тип операции", lexemes.position)
        }
    }

    private fun getMultiplication(lexemes: LexemeBuffer): Double {
        var value = getFactor(lexemes)
        while (true) {
            when (lexemes.next) {
                OperationMul -> value *= getFactor(lexemes)
                OperationDiv -> value /= getFactor(lexemes)
                OperationPlus, OperationMinus, EOF -> {
                    lexemes.back()
                    return value
                }
                else -> throw ParseException("Неизвестный тип операции", lexemes.position)
            }
        }
    }
}
