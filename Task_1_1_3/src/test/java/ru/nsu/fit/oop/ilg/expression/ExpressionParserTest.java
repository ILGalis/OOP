package ru.nsu.fit.oop.ilg.expression;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.ilg.expression.core.Expression;
import ru.nsu.fit.oop.ilg.expression.parser.ExpressionParser;

/**
 * Тесты для класса {@link ExpressionParser}.
 */
class ExpressionParserTest {

    /**
     * Проверяет парсинг числа.
     */
    @Test
    void parseNumber() {
        Expression e = new ExpressionParser("42").parse();

        assertEquals("42", e.toString());
    }

    /**
     * Проверяет парсинг переменной.
     */
    @Test
    void parseVariable() {
        Expression e = new ExpressionParser("x").parse();

        assertEquals("x", e.toString());
    }

    /**
     * Проверяет парсинг сложения.
     */
    @Test
    void parseAdd() {
        Expression e = new ExpressionParser("(3+5)").parse();

        assertEquals("(3+5)", e.toString());
    }

    /**
     * Проверяет парсинг выражения из задания.
     */
    @Test
    void parseComplexExpression() {
        Expression e = new ExpressionParser("(3+(2*x))").parse();

        assertEquals("(3+(2*x))", e.toString());
    }

    /**
     * Проверяет парсинг с пробелами.
     */
    @Test
    void parseWithSpaces() {
        Expression e = new ExpressionParser("( 3 + ( 2 * x ) )").parse();

        assertEquals("(3+(2*x))", e.toString());
    }

    /**
     * Проверяет вычисление распарсенного выражения.
     */
    @Test
    void evalParsedExpression() {
        Expression e = new ExpressionParser("(3+(2*x))").parse();

        assertEquals(23, e.eval("x = 10"));
    }

    /**
     * Проверяет производную распарсенного выражения.
     */
    @Test
    void derivativeOfParsedExpression() {
        Expression e = new ExpressionParser("(3+(2*x))").parse();
        Expression derivative = e.derivative("x");

        assertEquals("(0+((0*x)+(2*1)))", derivative.toString());
    }

    /**
     * Проверяет многобуквенные имена.
     */
    @Test
    void parseLongVariableNames() {
        Expression e = new ExpressionParser("(alpha+beta)").parse();

        assertEquals("(alpha+beta)", e.toString());
        assertEquals(15, e.eval("alpha = 10; beta = 5"));
    }

    /**
     * Проверяет вложенные скобки.
     */
    @Test
    void parseNestedBrackets() {
        Expression e = new ExpressionParser("((1+2)*(3+4))").parse();

        assertEquals("((1+2)*(3+4))", e.toString());
        assertEquals(21, e.eval(""));
    }
}