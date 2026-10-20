package ru.nsu.fit.oop.ilg.graph.view;

import java.util.List;
import ru.nsu.fit.oop.ilg.graph.core.Graph;

/**
 * Отвечает за вывод графа и результатов в консоль.
 */
public class ConsoleView {

    /**
     * Выводит граф.
     *
     * @param graph граф
     */
    public void printGraph(Graph<?> graph) {
        System.out.println(graph);
    }

    /**
     * Выводит список вершин.
     *
     * @param vertices вершины
     */
    public void printVertices(List<?> vertices) {
        System.out.println("Вершины: " + vertices);
    }

    /**
     * Выводит сообщение.
     *
     * @param message сообщение
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Выводит ошибку.
     *
     * @param message сообщение
     */
    public void printError(String message) {
        System.out.println("Ошибка: " + message);
    }
}