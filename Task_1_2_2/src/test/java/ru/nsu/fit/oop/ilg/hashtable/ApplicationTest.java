package ru.nsu.fit.oop.ilg.hashtable;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.ilg.hashtable.app.Application;

/**
 * Тесты для класса Application.
 */
class ApplicationTest {

    /**
     * Проверяет основной сценарий работы (пример из задания).
     */
    @Test
    void shouldHandleBasicScenario() {
        String input = "0\n";
        String output = runWithInput(input);

        assertTrue(output.contains("1.0"));
        assertTrue(output.contains("Текущая таблица: {one=1.0}"));
    }

    /**
     * Проходит по всем командам меню, чтобы покрыть все case в switch.
     */
    @Test
    void shouldCoverAllCommands() {
        StringBuilder inputBuilder = new StringBuilder();

        inputBuilder.append("1\nkey1\n10.5\n");
        inputBuilder.append("2\nkey1\n11.5\n");
        inputBuilder.append("3\nkey1\n");
        inputBuilder.append("5\nkey1\n");
        inputBuilder.append("7\n");
        inputBuilder.append("6\n");
        inputBuilder.append("4\nkey1\n");
        inputBuilder.append("999\n");
        inputBuilder.append("1\nkey2\nabc\n");
        inputBuilder.append("0\n");

        String output = runWithInput(inputBuilder.toString());

        assertTrue(output.contains("Добавлено."));
        assertTrue(output.contains("Обновлено."));
        assertTrue(output.contains("Значение: 11.5"));
        assertTrue(output.contains("Содержится: true"));
        assertTrue(output.contains("Размер: 2"));
        assertTrue(output.contains("Удалено: 11.5"));
        assertTrue(output.contains("Неверный ввод."));
        assertTrue(output.contains("Ошибка:"));
    }

    private String runWithInput(String input) {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
            System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8));

            new Application().run();
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }

        return outputStream.toString(StandardCharsets.UTF_8);
    }
}