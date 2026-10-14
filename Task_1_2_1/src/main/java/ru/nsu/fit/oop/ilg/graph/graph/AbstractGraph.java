package ru.nsu.fit.oop.ilg.graph.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import ru.nsu.fit.oop.ilg.graph.core.Edge;
import ru.nsu.fit.oop.ilg.graph.core.Graph;
import ru.nsu.fit.oop.ilg.graph.core.Vertex;

/**
 * Абстрактная реализация графа с общей логикой.
 *
 * @param <V> тип значения вершины
 */
public abstract class AbstractGraph<V> implements Graph<V> {

    @Override
    public void addVertex(V value) {
        if (hasVertex(value)) {
            throw new IllegalArgumentException("Вершина уже существует: " + value);
        }
        doAddVertex(value);
    }

    @Override
    public void removeVertex(V value) {
        if (!hasVertex(value)) {
            throw new IllegalArgumentException("Вершины не существует: " + value);
        }
        doRemoveVertex(value);
    }

    @Override
    public void addEdge(V from, V to) {
        if (!hasVertex(from) || !hasVertex(to)) {
            throw new IllegalArgumentException(
                    "Одна из вершин не существует: " + from + ", " + to
            );
        }
        if (hasEdge(from, to)) {
            throw new IllegalArgumentException(
                    "Ребро уже существует: " + from + " -> " + to
            );
        }
        doAddEdge(from, to);
    }

    @Override
    public void removeEdge(V from, V to) {
        if (!hasEdge(from, to)) {
            throw new IllegalArgumentException(
                    "Ребра не существует: " + from + " -> " + to
            );
        }
        doRemoveEdge(from, to);
    }

    @Override
    public List<V> getNeighbors(V value) {
        if (!hasVertex(value)) {
            throw new IllegalArgumentException("Вершины не существует: " + value);
        }
        return doGetNeighbors(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Graph)) {
            return false;
        }
        Graph<?> other = (Graph<?>) o;
        return getVertices().equals(other.getVertices())
                && getEdges().equals(other.getEdges());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVertices(), getEdges());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()
                + "{vertices=" + getVertices()
                + ", edges=" + getEdges() + "}";
    }


    /**
     * Добавляет вершину без проверок.
     *
     * @param value значение вершины
     */
    protected abstract void doAddVertex(V value);

    /**
     * Удаляет вершину без проверок.
     *
     * @param value значение вершины
     */
    protected abstract void doRemoveVertex(V value);

    /**
     * Добавляет ребро без проверок.
     *
     * @param from начальная вершина
     * @param to   конечная вершина
     */
    protected abstract void doAddEdge(V from, V to);

    /**
     * Удаляет ребро без проверок.
     *
     * @param from начальная вершина
     * @param to   конечная вершина
     */
    protected abstract void doRemoveEdge(V from, V to);

    /**
     * Возвращает соседей без проверок.
     *
     * @param value значение вершины
     * @return список соседей
     */
    protected abstract List<V> doGetNeighbors(V value);
}