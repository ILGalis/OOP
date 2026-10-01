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
     * Проверяет полный цикл работы: ввод выражения, print, derivative,
     * eval, exit.
     */
    @Test
    void shouldHandleFullSession() {
        String input = "(3+(2*x))\n"
                + "print\n"
                + "derivative\n"
                + "x\n"
                + "eval\n"
                + "x = 10\n"
                + "exit\n";

        String output = runWithInput(input);

        assertTrue(output.contains("(3+(2*x))"));
        assertTrue(output.contains("(0+((0*x)+(2*1)))"));
        assertTrue(output.contains("23"));
    }

    /**
     * Проверяет реакцию на неизвестную команду.
     */
    @Test
    void shouldHandleUnknownCommand() {
        String input = "(3+5)\n"
                + "unknown\n"
                + "exit\n";

        String output = runWithInput(input);

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
            System.setOut(new PrintStream(outputStream,
                    true, StandardCharsets.UTF_8));

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