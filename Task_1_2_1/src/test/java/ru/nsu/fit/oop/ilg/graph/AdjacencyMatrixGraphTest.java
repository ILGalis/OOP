package ru.nsu.fit.oop.ilg.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.ilg.graph.graph.AdjacencyMatrixGraph;

/**
 * Тесты для {@link AdjacencyMatrixGraph}.
 */
class AdjacencyMatrixGraphTest {

    @Test
    void shouldAddVertex() {
        AdjacencyMatrixGraph<String> graph = new AdjacencyMatrixGraph<>();
        graph.addVertex("A");

        assertTrue(graph.hasVertex("A"));
        assertEquals(1, graph.size());
    }

    @Test
    void shouldNotAddDuplicateVertex() {
        AdjacencyMatrixGraph<String> graph = new AdjacencyMatrixGraph<>();
        graph.addVertex("A");

        assertThrows(IllegalArgumentException.class, () -> graph.addVertex("A"));
    }

    @Test
    void shouldRemoveVertex() {
        AdjacencyMatrixGraph<String> graph = new AdjacencyMatrixGraph<>();
        graph.addVertex("A");
        graph.removeVertex("A");

        assertFalse(graph.hasVertex("A"));
    }

    @Test
    void shouldAddEdge() {
        AdjacencyMatrixGraph<String> graph = new AdjacencyMatrixGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");

        assertTrue(graph.hasEdge("A", "B"));
        assertFalse(graph.hasEdge("B", "A"));
    }

    @Test
    void shouldNotAddEdgeWithoutVertices() {
        AdjacencyMatrixGraph<String> graph = new AdjacencyMatrixGraph<>();

        assertThrows(IllegalArgumentException.class,
                () -> graph.addEdge("A", "B"));
    }

    @Test
    void shouldGetNeighbors() {
        AdjacencyMatrixGraph<String> graph = new AdjacencyMatrixGraph<>();
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

    @Test
    void shouldGetVertices() {
        AdjacencyMatrixGraph<String> graph = new AdjacencyMatrixGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");

        Set<String> vertices = graph.getVertices();

        assertEquals(2, vertices.size());
    }

    @Test
    void removingVertexShouldRemoveItsEdges() {
        AdjacencyMatrixGraph<String> graph = new AdjacencyMatrixGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        graph.removeVertex("A");

        assertFalse(graph.hasVertex("A"));
        assertEquals(0, graph.getEdges().size());
    }
}