package classes.calculator

import classes.calculator.Calculator.calculateFactor
import java.text.ParseException

sealed class Lexeme {
    object EOF: Lexeme()
    object OperationPlus: Lexeme() {
        override fun getFactor(lexemes: LexemeBuffer): Double = +calculateFactor(lexemes)
    }
    object OperationMinus: Lexeme() {
        override fun getFactor(lexemes: LexemeBuffer): Double = -calculateFactor(lexemes)
    }
    object OperationMul: Lexeme()
    object OperationDiv: Lexeme()
    class Number(val value: Double): Lexeme() {
        override fun getFactor(lexemes: LexemeBuffer): Double = value
    }

    open fun getFactor(lexemes: LexemeBuffer): Double {
        throw ParseException("Неизвестный тип операции", lexemes.position)
    }
}
