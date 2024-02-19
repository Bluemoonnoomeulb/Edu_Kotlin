package classes.calculator

sealed class Lexeme {
    object EOF: Lexeme()
    object OperationPlus: Lexeme()
    object OperationMinus: Lexeme()
    object OperationMul: Lexeme()
    object OperationDiv: Lexeme()
    class Number(val value: Double): Lexeme()
}
