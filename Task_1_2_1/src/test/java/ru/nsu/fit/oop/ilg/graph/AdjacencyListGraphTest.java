package ru.nsu.fit.oop.ilg.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.ilg.graph.graph.AdjacencyListGraph;

/**
 * Тесты для {@link AdjacencyListGraph}.
 */
class AdjacencyListGraphTest {

    /**
     * Проверяет добавление вершины.
     */
    @Test
    void shouldAddVertex() {
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        graph.addVertex("A");

        assertTrue(graph.hasVertex("A"));
        assertEquals(1, graph.size());
    }

    /**
     * Проверяет, что нельзя добавить дубликат.
     */
    @Test
    void shouldNotAddDuplicateVertex() {
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        graph.addVertex("A");

        assertThrows(IllegalArgumentException.class, () -> graph.addVertex("A"));
    }

    /**
     * Проверяет удаление вершины.
     */
    @Test
    void shouldRemoveVertex() {
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        graph.addVertex("A");
        graph.removeVertex("A");

        assertFalse(graph.hasVertex("A"));
    }

    /**
     * Проверяет добавление ребра.
     */
    @Test
    void shouldAddEdge() {
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");

        assertTrue(graph.hasEdge("A", "B"));
        assertFalse(graph.hasEdge("B", "A"));
    }

    /**
     * Проверяет, что ребро нельзя добавить без вершин.
     */
    @Test
    void shouldNotAddEdgeWithoutVertices() {
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();

        assertThrows(IllegalArgumentException.class,
                () -> graph.addEdge("A", "B"));
    }

    /**
     * Проверяет получение соседей.
     */
    @Test
    void shouldGetNeighbors() {
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");

        List<String> neighbors = graph.getNeighbors("A");

        assertEquals(2, neighbors.size());
        assertTrue(neighbors.contains("B"));
        assertTrue(neighbors.contains("C"));
    }

    /**
     * Проверяет получение всех вершин.
     */
    @Test
    void shouldGetVertices() {
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");

        Set<String> vertices = graph.getVertices();

        assertEquals(2, vertices.size());
        assertTrue(vertices.contains("A"));
        assertTrue(vertices.contains("B"));
    }

    /**
     * Проверяет удаление вершины вместе с рёбрами.
     */
    @Test
    void removingVertexShouldRemoveItsEdges() {
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        graph.removeVertex("A");

        assertFalse(graph.hasVertex("A"));
        assertEquals(0, graph.getEdges().size());
    }
}