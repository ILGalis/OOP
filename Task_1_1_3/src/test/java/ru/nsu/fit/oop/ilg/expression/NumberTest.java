package ru.nsu.fit.oop.ilg.expression;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.ilg.expression.core.Expression;
import ru.nsu.fit.oop.ilg.expression.core.Number;

/**
 * Тесты для класса {@link Number}.
 */
class NumberTest {

    /**
     * Проверяет вычисление константы.
     */
    @Test
    void evalShouldReturnValue() {
        Number number = new Number(42);

        assertEquals(42, number.eval(""));
    }

    /**
     * Проверяет, что производная константы равна нулю.
     */
    @Test
    void derivativeShouldReturnZero() {
        Number number = new Number(42);
        Expression derivative = number.derivative("x");

        assertEquals("0", derivative.toString());
    }

    /**
     * Проверяет строковое представление.
     */
    @Test
    void toStringShouldReturnValue() {
        Number number = new Number(7);

        assertEquals("7", number.toString());
    }
}