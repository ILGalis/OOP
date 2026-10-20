package ru.nsu.fit.oop.ilg.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.nsu.fit.oop.ilg.graph.graph.AdjacencyListGraph;
import ru.nsu.fit.oop.ilg.graph.io.GraphReader;

/**
 * Тесты для {@link GraphReader}.
 */
class GraphReaderTest {

    /**
     * Проверяет чтение графа из файла.
     *
     * @param tempDir временная директория
     * @throws IOException при ошибке чтения
     */
    @Test
    void shouldReadGraph(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("graph.txt");
        Files.writeString(file, "4\nA B\nB C\nC D\n");

        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        new GraphReader<String>(s -> s).read(file, graph);

        assertTrue(graph.hasVertex("A"));
        assertTrue(graph.hasVertex("B"));
        assertTrue(graph.hasVertex("C"));
        assertTrue(graph.hasVertex("D"));
        assertTrue(graph.hasEdge("A", "B"));
        assertTrue(graph.hasEdge("B", "C"));
        assertTrue(graph.hasEdge("C", "D"));
    }
}