package ru.nsu.fit.oop.ilg.graph;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.ilg.graph.app.Application;

/**
 * Тесты для {@link Application}.
 */
class ApplicationTest {

    /**
     * Проверяет сценарий с графом-цепочкой.
     */
    @Test
    void shouldRunScenario() {
        String input = "3\n"
                + "4\n"
                + "A\nB\nC\nD\n"
                + "3\n"
                + "A B\nB C\nC D\n";

        String output = runWithInput(input);

        assertTrue(output.contains("AdjacencyListGraph"));
        assertTrue(output.contains("D"));
    }

    /**
     * Запускает приложение с заданным вводом.
     *
     * @param input входные данные
     * @return вывод
     */
    private String runWithInput(String input) {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setIn(new ByteArrayInputStream(
                    input.getBytes(StandardCharsets.UTF_8)));
            System.setOut(new PrintStream(output, true,
                    StandardCharsets.UTF_8));
            new Application().run();
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }

        return output.toString(StandardCharsets.UTF_8);
    }
}