package ru.nsu.fit.oop.ilg.expression;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.ilg.expression.core.Expression;
import ru.nsu.fit.oop.ilg.expression.core.Number;
import ru.nsu.fit.oop.ilg.expression.core.Variable;
import ru.nsu.fit.oop.ilg.expression.operation.Div;

/**
 * Тесты для класса {@link Div}.
 */
class DivTest {

    /**
     * Проверяет вычисление деления.
     */
    @Test
    void evalShouldDivideValues() {
        Expression e = new Div(new Number(20), new Number(4));

        assertEquals(5, e.eval(""));
    }

    /**
     * Проверяет деление с переменной.
     */
    @Test
    void evalShouldWorkWithVariables() {
        Expression e = new Div(new Variable("x"), new Number(2));

        assertEquals(5, e.eval("x = 10"));
    }

    /**
     * Проверяет деление на ноль.
     */
    @Test
    void evalByZeroShouldReturnZero() {
        Expression e = new Div(new Number(10), new Number(0));

        assertEquals(0, e.eval(""));
    }

    /**
     * Проверяет производную по правилу частного.
     */
    @Test
    void derivativeShouldFollowQuotientRule() {
        Expression e = new Div(new Variable("x"), new Number(2));
        Expression derivative = e.derivative("x");

        assertEquals("(((1*2)-(x*0))/(2*2))", derivative.toString());
    }

    /**
     * Проверяет строковое представление.
     */
    @Test
    void toStringShouldWrapInBrackets() {
        Expression e = new Div(new Number(20), new Number(4));

        assertEquals("(20/4)", e.toString());
    }
}