package classes.calculator

class LexemeBuffer(private val lexemes: List<Lexeme>, var position: Int = 0) {
    val next
        get() = lexemes[position++]

    fun back() {
        position--
    }
}
