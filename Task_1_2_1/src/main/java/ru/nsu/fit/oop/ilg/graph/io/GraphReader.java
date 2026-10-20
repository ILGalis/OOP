package ru.nsu.fit.oop.ilg.graph.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import ru.nsu.fit.oop.ilg.graph.core.Graph;

/**
 * Читает граф из файла фиксированного формата.
 *
 * <p>Формат:
 * <pre>
 * N
 * from1 to1
 * from2 to2
 * ...
 * </pre>
 * где {@code N} — число вершин, а строки после — рёбра.
 *
 * @param <V> тип значения вершины
 */
public class GraphReader<V> {
    private final Function<String, V> vertexParser;

    /**
     * Создаёт читатель с указанным парсером вершин.
     *
     * @param vertexParser функция преобразования строки в вершину
     */
    public GraphReader(Function<String, V> vertexParser) {
        this.vertexParser = vertexParser;
    }

    /**
     * Читает граф из файла.
     *
     * @param path  путь к файлу
     * @param graph пустой граф, в который будут добавлены данные
     * @throws IOException если произошла ошибка чтения
     */
    public void read(Path path, Graph<V> graph) throws IOException {
        List<String> lines = readLines(path);
        if (lines.isEmpty()) {
            return;
        }

        int vertexCount = Integer.parseInt(lines.get(0));

        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split("\\s+");
            if (parts.length < 2) {
                continue;
            }

            V from = vertexParser.apply(parts[0]);
            V to = vertexParser.apply(parts[1]);

            if (!graph.hasVertex(from)) {
                graph.addVertex(from);
            }
            if (!graph.hasVertex(to)) {
                graph.addVertex(to);
            }
            graph.addEdge(from, to);
        }

        for (int i = 0; i < vertexCount; i++) {
            V value = vertexParser.apply(String.valueOf(i));
            if (!graph.hasVertex(value)) {
                graph.addVertex(value);
            }
        }
    }

    /**
     * Читает все непустые строки файла.
     *
     * @param path путь
     * @return список строк
     * @throws IOException при ошибке чтения
     */
    private List<String> readLines(Path path) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(
                path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) {
                    lines.add(line.trim());
                }
            }
        }
        return lines;
    }
}