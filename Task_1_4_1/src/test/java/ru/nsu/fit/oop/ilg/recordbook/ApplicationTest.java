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

    @Test
    void shouldCoverAllApplicationBranches() {
        // Эмулируем полный сценарий работы пользователя
        StringBuilder input = new StringBuilder();
        input.append("1\n"); // Платное обучение (1 - да)

        // Меню
        input.append("4\n"); // 4 - Узнать средний балл (пустая зачетка)
        input.append("5\n"); // 5 - Проверить перевод (пустая зачетка)
        input.append("6\n"); // 6 - Проверить диплом (пустая зачетка)
        input.append("7\n"); // 7 - Проверить стипендию (пустая зачетка)

        // Добавить семестр
        input.append("1\n");
        input.append("1\n"); // Номер семестра 1

        // Добавить оценку в семестр 1
        input.append("2\n");
        input.append("1\n"); // Номер семестра 1
        input.append("Математика\n"); // Название
        input.append("1\n"); // Это экзамен
        input.append("EXCELLENT\n"); // Оценка

        // Добавить еще оценку
        input.append("2\n");
        input.append("1\n"); // Номер семестра 1
        input.append("История\n");
        input.append("0\n"); // Не экзамен (диф.зачет)
        input.append("ENOUGH\n"); // Оценка

        // Добавить оценку в несуществующий семестр (ошибка)
        input.append("2\n");
        input.append("99\n"); // Нет такого семестра

        // Неверный ввод (неизвестная оценка)
        input.append("2\n");
        input.append("1\n");
        input.append("Физика\n");
        input.append("1\n");
        input.append("SUPER_GRADE\n"); // Ошибка

        // Установить оценку за ВКР
        input.append("3\n");
        input.append("EXCELLENT\n");

        // Установить неверную оценку за ВКР
        input.append("3\n");
        input.append("UNKNOWN\n");

        // Вывести статистику
        input.append("4\n"); // Средний балл
        input.append("5\n"); // Бюджет
        input.append("6\n"); // Диплом
        input.append("7\n"); // Стипендия

        input.append("999\n"); // Неизвестная команда
        input.append("0\n"); // Выход

        String output = runWithInput(input.toString());

        // Проверки (хотя бы раз выполнились все ветки)
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