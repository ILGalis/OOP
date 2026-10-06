package ru.nsu.fit.oop.ilg.expression;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.ilg.expression.core.Expression;
import ru.nsu.fit.oop.ilg.expression.core.Variable;

/**
 * Тесты для класса {@link Variable}.
 */
class VariableTest {

    /**
     * Проверяет вычисление переменной.
     */
    @Test
    void evalShouldReturnValue() {
        Variable variable = new Variable("x");

        assertEquals(10, variable.eval("x = 10"));
    }

    /**
     * Проверяет, что незаданная переменная равна нулю.
     */
    @Test
    void evalOfUnknownVariableShouldReturnZero() {
        Variable variable = new Variable("y");

        assertEquals(0, variable.eval("x = 10"));
    }

    /**
     * Проверяет производную по той же переменной.
     */
    @Test
    void derivativeBySameVariableShouldReturnOne() {
        Variable variable = new Variable("x");
        Expression derivative = variable.derivative("x");

        assertEquals("1", derivative.toString());
    }

    /**
     * Проверяет производную по другой переменной.
     */
    @Test
    void derivativeByOtherVariableShouldReturnZero() {
        Variable variable = new Variable("x");
        Expression derivative = variable.derivative("y");

        assertEquals("0", derivative.toString());
    }

    /**
     * Проверяет многобуквенное имя.
     */
    @Test
    void shouldSupportLongNames() {
        Variable variable = new Variable("alpha");

        assertEquals(5, variable.eval("alpha = 5"));
        assertEquals("alpha", variable.toString());
    }
}