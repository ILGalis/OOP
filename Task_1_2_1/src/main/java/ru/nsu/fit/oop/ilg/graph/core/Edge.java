package ru.nsu.fit.oop.ilg.graph.core;

import java.util.Objects;

/**
 * Ребро графа.
 *
 * @param <V> тип значения вершин
 */
public class Edge<V> {
    private final Vertex<V> from;
    private final Vertex<V> to;
    private final boolean directed;

    /**
     * Создаёт ребро.
     *
     * @param from     начальная вершина
     * @param to       конечная вершина
     * @param directed ориентированное ли ребро
     */
    public Edge(Vertex<V> from, Vertex<V> to, boolean directed) {
        this.from = from;
        this.to = to;
        this.directed = directed;
    }

    /**
     * Возвращает начальную вершину.
     *
     * @return начальная вершина
     */
    public Vertex<V> getFrom() {
        return from;
    }

    /**
     * Возвращает конечную вершину.
     *
     * @return конечная вершина
     */
    public Vertex<V> getTo() {
        return to;
    }

    /**
     * Проверяет, ориентированное ли ребро.
     *
     * @return {@code true}, если ориентированное
     */
    public boolean isDirected() {
        return directed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Edge<V> edge = (Edge<V>) o;
        return directed == edge.directed
                && Objects.equals(from, edge.from)
                && Objects.equals(to, edge.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, directed);
    }

    @Override
    public String toString() {
        return from + (directed ? " -> " : " -- ") + to;
    }
}