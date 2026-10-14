package ru.nsu.fit.oop.ilg.graph.core;

import java.util.List;
import java.util.Set;

/**
 * Интерфейс графа.
 *
 * @param <V> тип значения вершины
 */
public interface Graph<V> {

    /**
     * Добавляет вершину.
     *
     * @param value значение вершины
     */
    void addVertex(V value);

    /**
     * Удаляет вершину.
     *
     * @param value значение вершины
     */
    void removeVertex(V value);

    /**
     * Добавляет ребро.
     *
     * @param from начальная вершина
     * @param to   конечная вершина
     */
    void addEdge(V from, V to);

    /**
     * Удаляет ребро.
     *
     * @param from начальная вершина
     * @param to   конечная вершина
     */
    void removeEdge(V from, V to);

    /**
     * Возвращает список соседей вершины.
     *
     * @param value значение вершины
     * @return список соседей
     */
    List<V> getNeighbors(V value);

    /**
     * Проверяет наличие вершины.
     *
     * @param value значение вершины
     * @return {@code true}, если есть
     */
    boolean hasVertex(V value);

    /**
     * Проверяет наличие ребра.
     *
     * @param from начальная вершина
     * @param to   конечная вершина
     * @return {@code true}, если есть
     */
    boolean hasEdge(V from, V to);

    /**
     * Возвращает все вершины.
     *
     * @return множество вершин
     */
    Set<V> getVertices();

    /**
     * Возвращает все рёбра.
     *
     * @return множество рёбер
     */
    Set<Edge<V>> getEdges();

    /**
     * Возвращает количество вершин.
     *
     * @return количество
     */
    int size();
}