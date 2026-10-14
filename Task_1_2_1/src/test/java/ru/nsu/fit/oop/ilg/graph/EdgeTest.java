package ru.nsu.fit.oop.ilg.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.ilg.graph.core.Edge;
import ru.nsu.fit.oop.ilg.graph.core.Vertex;

/**
 * Тесты для класса {@link Edge}.
 */
class EdgeTest {

    /**
     * Проверяет создание ребра.
     */
    @Test
    void shouldStoreEndpoints() {
        Vertex<String> a = new Vertex<>("A");
        Vertex<String> b = new Vertex<>("B");
        Edge<String> edge = new Edge<>(a, b, true);

        assertEquals(a, edge.getFrom());
        assertEquals(b, edge.getTo());
        assertTrue(edge.isDirected());
    }

    /**
     * Проверяет равенство рёбер.
     */
    @Test
    void shouldBeEqual() {
        Edge<String> e1 = new Edge<>(new Vertex<>("A"), new Vertex<>("B"), true);
        Edge<String> e2 = new Edge<>(new Vertex<>("A"), new Vertex<>("B"), true);

        assertEquals(e1, e2);
        assertEquals(e1.hashCode(), e2.hashCode());
    }

    /**
     * Проверяет неравенство рёбер.
     */
    @Test
    void shouldNotBeEqual() {
        Edge<String> e1 = new Edge<>(new Vertex<>("A"), new Vertex<>("B"), true);
        Edge<String> e2 = new Edge<>(new Vertex<>("B"), new Vertex<>("A"), true);

        assertNotEquals(e1, e2);
    }

    /**
     * Проверяет toString для ориентированного ребра.
     */
    @Test
    void toStringShouldShowArrow() {
        Edge<String> edge = new Edge<>(new Vertex<>("A"), new Vertex<>("B"), true);

        assertEquals("A -> B", edge.toString());
    }
}