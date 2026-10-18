package ru.nsu.fit.oop.ilg.graph.app;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import ru.nsu.fit.oop.ilg.graph.algorithm.TopologicalSort;
import ru.nsu.fit.oop.ilg.graph.core.Graph;
import ru.nsu.fit.oop.ilg.graph.graph.AdjacencyListGraph;
import ru.nsu.fit.oop.ilg.graph.graph.AdjacencyMatrixGraph;
import ru.nsu.fit.oop.ilg.graph.graph.IncidenceMatrixGraph;
import ru.nsu.fit.oop.ilg.graph.view.ConsoleView;

/**
 * Приложение для работы с графами.
 * Читает граф из консоли, выводит его и сортирует вершины.
 */
public class Application {
    private final Scanner scanner;
    private final ConsoleView view;

    /**
     * Создаёт приложение.
     */
    public Application() {
        scanner = new Scanner(System.in);
        view = new ConsoleView();
    }

    /**
     * Запускает сценарий.
     */
    public void run() {
        System.out.println("Какой тип графа:");
        System.out.println("1 - матрица смежности");
        System.out.println("2 - матрица инцидентности");
        System.out.println("3 - список смежности");
        GraphType type = GraphType.fromString(scanner.nextLine().trim());

        Graph<String> graph = createGraph(type);

        System.out.println("Сколько вершин?");
        int vertexCount = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Введите " + vertexCount + " вершин по одной в строке");
        for (int i = 0; i < vertexCount; i++) {
            String vertex = scanner.nextLine().trim();
            if (!graph.hasVertex(vertex)) {
                graph.addVertex(vertex);
            }
        }

        System.out.println("Сколько рёбер?");
        int edgeCount = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Введите " + edgeCount
                + " рёбер по одному в строке (формат: from to)");
        for (int i = 0; i < edgeCount; i++) {
            List<String> parts = Arrays.asList(
                    scanner.nextLine().trim().split("\\s+"));
            if (parts.size() < 2) {
                view.printError("Неверный формат ребра, пропускаем");
                continue;
            }
            String from = parts.get(0);
            String to = parts.get(1);
            if (!graph.hasVertex(from)) {
                graph.addVertex(from);
            }
            if (!graph.hasVertex(to)) {
                graph.addVertex(to);
            }
            try {
                graph.addEdge(from, to);
            } catch (IllegalArgumentException e) {
                view.printError(e.getMessage());
            }
        }

        view.printMessage("");
        view.printMessage("Граф:");
        view.printGraph(graph);

        view.printMessage("");
        view.printMessage("Топологическая сортировка:");
        TopologicalSort<String> sorter = new TopologicalSort<>();
        view.printVertices(sorter.sort(graph));

        scanner.close();
    }

    /**
     * Создаёт граф указанного типа.
     *
     * @param type тип
     * @return пустой граф
     */
    private Graph<String> createGraph(GraphType type) {
        return switch (type) {
            case ADJACENCY_MATRIX -> new AdjacencyMatrixGraph<>();
            case INCIDENCE_MATRIX -> new IncidenceMatrixGraph<>();
            case ADJACENCY_LIST -> new AdjacencyListGraph<>();
            default -> throw new IllegalArgumentException(
                    "Неизвестный тип графа: " + type
            );
        };
    }

    /**
     * Тип графа.
     */
    private enum GraphType {
        ADJACENCY_MATRIX,
        INCIDENCE_MATRIX,
        ADJACENCY_LIST,
        UNKNOWN;

        /**
         * Находит тип по имени.
         *
         * @param name имя
         * @return тип
         */
        private static GraphType fromString(String name) {
            return switch (name) {
                case "1" -> ADJACENCY_MATRIX;
                case "2" -> INCIDENCE_MATRIX;
                case "3" -> ADJACENCY_LIST;
                default -> UNKNOWN;
            };
        }
    }
}