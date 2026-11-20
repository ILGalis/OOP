package ru.nsu.fit.oop.ilg.recordbook;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.ilg.recordbook.app.Application;

/**
 * Тесты для консольного приложения.
 */
class ApplicationTest {

    /**
     * Проверяет покрытие всех веток приложения.
     */
    @Test
    void shouldCoverAllApplicationBranches() {
        StringBuilder input = new StringBuilder();
        input.append("1\n");

        input.append("4\n");
        input.append("5\n");
        input.append("6\n");
        input.append("7\n");

        input.append("1\n");
        input.append("1\n");

        input.append("2\n");
        input.append("1\n");
        input.append("Математика\n");
        input.append("1\n");
        input.append("EXCELLENT\n");

        input.append("2\n");
        input.append("1\n");
        input.append("История\n");
        input.append("0\n");
        input.append("ENOUGH\n");

        input.append("2\n");
        input.append("99\n");

        input.append("2\n");
        input.append("1\n");
        input.append("Физика\n");
        input.append("1\n");
        input.append("SUPER_GRADE\n");

        input.append("3\n");
        input.append("EXCELLENT\n");

        input.append("3\n");
        input.append("UNKNOWN\n");

        input.append("4\n");
        input.append("5\n");
        input.append("6\n");
        input.append("7\n");

        input.append("999\n");
        input.append("0\n");

        String output = runWithInput(input.toString());

        assertTrue(output.contains("Электронная зачетная книжка ФИТ"));
        assertTrue(output.contains("Семестр добавлен."));
        assertTrue(output.contains("Оценка добавлена."));
        assertTrue(output.contains("Семестр с таким номером не найден!"));
        assertTrue(output.contains("Неверная оценка."));
        assertTrue(output.contains("Оценка за ВКР установлена."));
        assertTrue(output.contains("Средний балл:"));
        assertTrue(output.contains("Можно перевестись:"));
        assertTrue(output.contains("Светит красный диплом:"));
        assertTrue(output.contains("Можно получить повышенную стипендию:"));
        assertTrue(output.contains("Неверный ввод."));
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