package ru.nsu.fit.oop.ilg.expression;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.ilg.expression.core.Expression;
import ru.nsu.fit.oop.ilg.expression.core.Number;
import ru.nsu.fit.oop.ilg.expression.core.Variable;
import ru.nsu.fit.oop.ilg.expression.operation.Mul;

/**
 * Тесты для класса {@link Mul}.
 */
class MulTest {

    /**
     * Проверяет вычисление умножения.
     */
    @Test
    void evalShouldMultiplyValues() {
        Expression e = new Mul(new Number(3), new Number(4));

        assertEquals(12, e.eval(""));
    }

    /**
     * Проверяет умножение с переменной.
     */
    @Test
    void evalShouldWorkWithVariables() {
        Expression e = new Mul(new Number(2), new Variable("x"));

        assertEquals(20, e.eval("x = 10"));
    }

    /**
     * Проверяет производную по правилу произведения.
     */
    @Test
    void derivativeShouldFollowProductRule() {
        Expression e = new Mul(new Number(2), new Variable("x"));
        Expression derivative = e.derivative("x");

        assertEquals("((0*x)+(2*1))", derivative.toString());
    }

    /**
     * Проверяет строковое представление.
     */
    @Test
    void toStringShouldWrapInBrackets() {
        Expression e = new Mul(new Number(3), new Number(4));

        assertEquals("(3*4)", e.toString());
    }
}