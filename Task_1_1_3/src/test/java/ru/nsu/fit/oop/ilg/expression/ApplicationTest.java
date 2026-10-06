package ru.nsu.fit.oop.ilg.expression;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.ilg.expression.app.Application;

/**
 * Тесты для класса {@link Application}.
 */
class ApplicationTest {

    /**
     * Проверяет команду derivative.
     */
    @Test
    void shouldHandleDerivative() {
        String output = runWithInput("(3+(2*x))\nderivative\nx\n");

        assertTrue(output.contains("(0+((0*x)+(2*1)))"));
    }

    /**
     * Проверяет команду eval.
     */
    @Test
    void shouldHandleEval() {
        String output = runWithInput("(3+(2*x))\neval\nx = 10\n");

        assertTrue(output.contains("23"));
    }

    /**
     * Проверяет команду print.
     */
    @Test
    void shouldHandlePrint() {
        String output = runWithInput("(3+(2*x))\nprint\n");

        assertTrue(output.contains("(3+(2*x))"));
    }

    /**
     * Проверяет неизвестную команду.
     */
    @Test
    void shouldHandleUnknownCommand() {
        String output = runWithInput("(3+5)\nhello\n");

        assertTrue(output.contains("Неизвестная команда"));
    }

    /**
     * Запускает приложение с заданным вводом и возвращает вывод.
     *
     * @param input входные данные
     * @return вывод приложения
     */
    private String runWithInput(String input) {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            System.setIn(new ByteArrayInputStream(
                    input.getBytes(StandardCharsets.UTF_8)));
            System.setOut(new PrintStream(outputStream, true,
                    StandardCharsets.UTF_8));

            new Application().run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }

        return outputStream.toString(StandardCharsets.UTF_8);
    }
}