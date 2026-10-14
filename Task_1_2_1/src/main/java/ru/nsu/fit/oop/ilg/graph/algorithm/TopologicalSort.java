package ru.nsu.fit.oop.ilg.graph.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ru.nsu.fit.oop.ilg.graph.core.Graph;

/**
 * Топологическая сортировка вершин графа.
 * Работает с любой реализацией {@link Graph}.
 *
 * @param <V> тип значения вершины
 */
public class TopologicalSort<V> {

    /**
     * Выполняет топологическую сортировку.
     *
     * @param graph граф
     * @return список вершин в топологическом порядке
     */
    public List<V> sort(Graph<V> graph) {
        Set<V> visited = new HashSet<>();
        Deque<V> stack = new ArrayDeque<>();

        for (V vertex : graph.getVertices()) {
            if (!visited.contains(vertex)) {
                dfs(graph, vertex, visited, stack);
            }
        }

        return new ArrayList<>(stack);
    }

    /**
     * Рекурсивный обход в глубину.
     *
     * @param graph   граф
     * @param vertex  текущая вершина
     * @param visited посещённые вершины
     * @param stack   стек для результата
     */
    private void dfs(Graph<V> graph, V vertex, Set<V> visited, Deque<V> stack) {
        visited.add(vertex);

        for (V neighbor : graph.getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                dfs(graph, neighbor, visited, stack);
            }
        }

        stack.push(vertex);
    }
}