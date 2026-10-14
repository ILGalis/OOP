package ru.nsu.fit.oop.ilg.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.ilg.graph.graph.IncidenceMatrixGraph;

/**
 * Тесты для {@link IncidenceMatrixGraph}.
 */
class IncidenceMatrixGraphTest {

    @Test
    void shouldAddVertex() {
        IncidenceMatrixGraph<String> graph = new IncidenceMatrixGraph<>();
        graph.addVertex("A");

        assertTrue(graph.hasVertex("A"));
        assertEquals(1, graph.size());
    }

    @Test
    void shouldAddEdge() {
        IncidenceMatrixGraph<String> graph = new IncidenceMatrixGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");

        assertTrue(graph.hasEdge("A", "B"));
    }

    @Test
    void shouldGetNeighbors() {
        IncidenceMatrixGraph<String> graph = new IncidenceMatrixGraph<>();
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
    void shouldRemoveVertexWithEdges() {
        IncidenceMatrixGraph<String> graph = new IncidenceMatrixGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        graph.removeVertex("A");

        assertFalse(graph.hasVertex("A"));
        assertEquals(0, graph.getEdges().size());
    }

    @Test
    void shouldNotAddDuplicateVertex() {
        IncidenceMatrixGraph<String> graph = new IncidenceMatrixGraph<>();
        graph.addVertex("A");

        assertThrows(IllegalArgumentException.class,
                () -> graph.addVertex("A"));
    }
}