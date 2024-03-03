package classes.calculator

import java.text.ParseException

class Parser {

    companion object {
        private val conversion: (Char) -> Double = {
            val temp = it.toString()
            temp.toDouble()
        }
        private fun checkNumber(symbol: Char): Boolean = (symbol in '0'..'9') or (symbol == ',') or (symbol == '.')

        fun parseLexeme(symbol: Char, index: Int): Lexeme {
            return when (symbol) {
                Operation.PLUS.symbol -> Lexeme.OperationPlus
                Operation.MINUS.symbol -> Lexeme.OperationMinus
                Operation.MULTIPLICATION.symbol -> Lexeme.OperationMul
                Operation.DIVISION.symbol -> Lexeme.OperationDiv
                else -> if (checkNumber(symbol)) {
                    Lexeme.Number(conversion(symbol))
                } else {
                    throw ParseException("Неизвестный символ", index)
                }
            }
        }
    }
}