package ru.nsu.fit.oop.ilg.expression;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.ilg.expression.core.Expression;
import ru.nsu.fit.oop.ilg.expression.core.Number;
import ru.nsu.fit.oop.ilg.expression.core.Variable;
import ru.nsu.fit.oop.ilg.expression.operation.Sub;

/**
 * Тесты для класса {@link Sub}.
 */
class SubTest {

    /**
     * Проверяет вычисление вычитания.
     */
    @Test
    void evalShouldSubtractValues() {
        Expression e = new Sub(new Number(10), new Number(3));

        assertEquals(7, e.eval(""));
    }

    /**
     * Проверяет вычитание с переменной.
     */
    @Test
    void evalShouldWorkWithVariables() {
        Expression e = new Sub(new Variable("x"), new Number(2));

        assertEquals(8, e.eval("x = 10"));
    }

    /**
     * Проверяет производную.
     */
    @Test
    void derivativeShouldReturnDifferenceOfDerivatives() {
        Expression e = new Sub(new Variable("x"), new Number(5));

        assertEquals("(1-0)", e.derivative("x").toString());
    }

    /**
     * Проверяет строковое представление.
     */
    @Test
    void toStringShouldWrapInBrackets() {
        Expression e = new Sub(new Number(10), new Number(3));

        assertEquals("(10-3)", e.toString());
    }
}