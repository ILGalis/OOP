package ru.nsu.fit.oop.ilg.substring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.nsu.fit.oop.ilg.substring.app.Application;
import ru.nsu.fit.oop.ilg.substring.core.SubstringFinder;

/**
 * Тесты для поиска подстроки и приложения.
 */
class SubstringFinderTest {

    @Test
    void shouldFindIndicesInSimpleText() throws IOException {
        SubstringFinder finder = new SubstringFinder();
        String text = "абракадабра";
        String target = "бра";

        List<Long> indices = finder.find(new StringReader(text), target);

        assertEquals(2, indices.size());
        assertEquals(1L, indices.get(0));
        assertEquals(8L, indices.get(1));
    }

    @Test
    void shouldFindSubstringAtEdges() throws IOException {
        SubstringFinder finder = new SubstringFinder();
        String text = "бракадабрабра";

        List<Long> indices = finder.find(new StringReader(text), "бра");

        assertEquals(3, indices.size());
        assertEquals(0L, indices.get(0));
        assertEquals(7L, indices.get(1));
        assertEquals(10L, indices.get(2));
    }

    @Test
    void shouldReturnEmptyListIfNotFound() throws IOException {
        SubstringFinder finder = new SubstringFinder();
        String text = "привет мир";

        List<Long> indices = finder.find(new StringReader(text), "Java");
        assertTrue(indices.isEmpty());

        List<Long> emptyTarget = finder.find(new StringReader(text), "");
        assertTrue(emptyTarget.isEmpty());

        List<Long> nullTarget = finder.find(new StringReader(text), null);
        assertTrue(nullTarget.isEmpty());
    }

    @Test
    void shouldFindInLargeFile(@TempDir Path tempDir) throws IOException {
        SubstringFinder finder = new SubstringFinder();
        Path file = tempDir.resolve("large_test.txt");

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 1_000_000; i++) {
            builder.append("0123456789");
        }
        builder.append("NEEDLE");

        Files.writeString(file, builder.toString(), StandardCharsets.UTF_8);

        try (var reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            List<Long> indices = finder.find(reader, "NEEDLE");
            assertEquals(1, indices.size());
            assertEquals(10_000_000L, indices.get(0));
        }
    }

    @Test
    void shouldRunApplicationSuccessfully(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("input.txt");
        Files.writeString(file, "абракадабра", StandardCharsets.UTF_8);

        String input = file.toAbsolutePath() + "\nбра\n";
        String output = runWithInput(input);

        assertTrue(output.contains("[1, 8]"));
    }

    @Test
    void shouldHandleFileNotFound() {
        String input = "nonexistent_file_123.txt\nбра\n";
        String output = runWithInput(input);
        assertTrue(output.contains("Файл не найден!"));
    }

    @Test
    void shouldHandleIOException(@TempDir Path tempDir) {
        String input = tempDir.toAbsolutePath() + "\nбра\n";
        String output = runWithInput(input);
        assertTrue(output.contains("Ошибка при чтении файла:"));
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