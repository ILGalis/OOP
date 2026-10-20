package ru.nsu.fit.oop.ilg.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.ilg.graph.core.Vertex;

/**
 * Тесты для класса {@link Vertex}.
 */
class VertexTest {

    /**
     * Проверяет создание и получение значения.
     */
    @Test
    void shouldStoreValue() {
        Vertex<String> vertex = new Vertex<>("A");

        assertEquals("A", vertex.getValue());
    }

    /**
     * Проверяет равенство вершин.
     */
    @Test
    void shouldBeEqualByValue() {
        Vertex<String> a = new Vertex<>("A");
        Vertex<String> b = new Vertex<>("A");

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    /**
     * Проверяет неравенство вершин.
     */
    @Test
    void shouldNotBeEqual() {
        Vertex<String> a = new Vertex<>("A");
        Vertex<String> b = new Vertex<>("B");

        assertNotEquals(a, b);
    }

    /**
     * Проверяет toString.
     */
    @Test
    void toStringShouldReturnValue() {
        Vertex<Integer> vertex = new Vertex<>(42);

        assertEquals("42", vertex.toString());
    }
}