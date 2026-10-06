package ru.nsu.fit.oop.ilg.expression;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.ilg.expression.core.Expression;
import ru.nsu.fit.oop.ilg.expression.core.Number;
import ru.nsu.fit.oop.ilg.expression.core.Variable;
import ru.nsu.fit.oop.ilg.expression.operation.Add;

/**
 * Тесты для класса {@link Add}.
 */
class AddTest {

    /**
     * Проверяет вычисление сложения.
     */
    @Test
    void evalShouldAddValues() {
        Expression e = new Add(new Number(3), new Number(5));

        assertEquals(8, e.eval(""));
    }

    /**
     * Проверяет сложение с переменной.
     */
    @Test
    void evalShouldWorkWithVariables() {
        Expression e = new Add(new Number(3), new Variable("x"));

        assertEquals(13, e.eval("x = 10"));
    }

    /**
     * Проверяет производную.
     */
    @Test
    void derivativeShouldReturnSumOfDerivatives() {
        Expression e = new Add(new Number(3), new Variable("x"));

        assertEquals("(0+1)", e.derivative("x").toString());
    }

    /**
     * Проверяет строковое представление.
     */
    @Test
    void toStringShouldWrapInBrackets() {
        Expression e = new Add(new Number(3), new Number(5));

        assertEquals("(3+5)", e.toString());
    }
}