package classes.calculator

object Manipulator {
    fun calculateExpression(expression: String) {
        val lexemeBuffer = getLexemes(expression)

        val lexemeItem = lexemeBuffer.next
        val result = if (lexemeItem is Lexeme.EOF) {
            0.0
        }
        else {
            lexemeBuffer.back()
            Calculator.getAmount(lexemeBuffer)
        }

        println(result)
    }

    private fun checkNumber(symbol: Char): Boolean = (symbol in '0'..'9') or (symbol == ',') or (symbol == '.')

    private fun getLexemes(expression: String): LexemeBuffer {
        val lexemes = mutableListOf<Lexeme>()
        var numberSymbol = ""

        expression.forEachIndexed { index, symbol ->
            if (!checkNumber(symbol) and numberSymbol.isNotEmpty()) {
                lexemes.add(Lexeme.Number(numberSymbol.toDouble()))
                numberSymbol = ""
            }

            val result = Parser.parseLexeme(symbol, index)
            if (result is Lexeme.Number){
                numberSymbol += result.value.toString()
            } else {
                lexemes.add(result)
            }
        }
        if (numberSymbol.isNotEmpty())
            lexemes.add(Lexeme.Number(numberSymbol.toDouble()))

        lexemes.add(Lexeme.EOF)

        return LexemeBuffer(lexemes)
    }
}
