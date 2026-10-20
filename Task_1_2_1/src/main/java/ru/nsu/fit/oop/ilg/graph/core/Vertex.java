package ru.nsu.fit.oop.ilg.graph.core;

import java.util.Objects;

/**
 * Вершина графа.
 *
 * @param <V> тип значения вершины
 */
public class Vertex<V> {
    private final V value;

    /**
     * Создаёт вершину с указанным значением.
     *
     * @param value значение вершины
     */
    public Vertex(V value) {
        this.value = value;
    }

    /**
     * Возвращает значение вершины.
     *
     * @return значение
     */
    public V getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vertex<?> vertex = (Vertex<?>) o;
        return Objects.equals(value, vertex.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}