package ru.nsu.fit.oop.ilg.graph.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import ru.nsu.fit.oop.ilg.graph.core.Edge;
import ru.nsu.fit.oop.ilg.graph.core.Vertex;

/**
 * Граф, хранимый как матрица инцидентности.
 * Строки — вершины, столбцы — рёбра.
 *
 * @param <V> тип значения вершины
 */
public class IncidenceMatrixGraph<V> extends AbstractGraph<V> {
    private final Map<Vertex<V>, Map<Edge<V>, Integer>> matrix = new HashMap<>();

    @Override
    protected void doAddVertex(V value) {
        matrix.put(new Vertex<>(value), new HashMap<>());
    }

    @Override
    protected void doRemoveVertex(V value) {
        Vertex<V> vertex = new Vertex<>(value);

        // Собираем рёбра, инцидентные этой вершине
        Set<Edge<V>> edgesToRemove = new HashSet<>(matrix.get(vertex).keySet());
        matrix.remove(vertex);

        // Удаляем эти рёбра из всех строк
        for (Edge<V> edge : edgesToRemove) {
            for (Map<Edge<V>, Integer> row : matrix.values()) {
                row.remove(edge);
            }
        }
    }

    @Override
    protected void doAddEdge(V from, V to) {
        Vertex<V> fromVertex = new Vertex<>(from);
        Vertex<V> toVertex = new Vertex<>(to);
        Edge<V> edge = new Edge<>(fromVertex, toVertex, true);

        matrix.get(fromVertex).put(edge, 1);
        matrix.get(toVertex).put(edge, 1);
    }

    @Override
    protected void doRemoveEdge(V from, V to) {
        Vertex<V> fromVertex = new Vertex<>(from);
        Vertex<V> toVertex = new Vertex<>(to);
        Edge<V> edge = new Edge<>(fromVertex, toVertex, true);

        matrix.get(fromVertex).remove(edge);
        matrix.get(toVertex).remove(edge);
    }

    @Override
    protected List<V> doGetNeighbors(V value) {
        Vertex<V> vertex = new Vertex<>(value);
        Set<V> neighbors = new HashSet<>();

        for (Edge<V> edge : matrix.get(vertex).keySet()) {
            if (edge.getFrom().equals(vertex)) {
                neighbors.add(edge.getTo().getValue());
            } else {
                neighbors.add(edge.getFrom().getValue());
            }
        }

        return new ArrayList<>(neighbors);
    }

    @Override
    public boolean hasVertex(V value) {
        return matrix.containsKey(new Vertex<>(value));
    }

    @Override
    public boolean hasEdge(V from, V to) {
        if (!hasVertex(from)) {
            return false;
        }
        Vertex<V> fromVertex = new Vertex<>(from);
        Vertex<V> toVertex = new Vertex<>(to);

        for (Edge<V> edge : matrix.get(fromVertex).keySet()) {
            if (edge.getFrom().equals(fromVertex)
                    && edge.getTo().equals(toVertex)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Set<V> getVertices() {
        Set<V> result = new HashSet<>();
        for (Vertex<V> vertex : matrix.keySet()) {
            result.add(vertex.getValue());
        }
        return result;
    }

    @Override
    public Set<Edge<V>> getEdges() {
        Set<Edge<V>> edges = new HashSet<>();
        for (Map<Edge<V>, Integer> row : matrix.values()) {
            edges.addAll(row.keySet());
        }
        return edges;
    }

    @Override
    public int size() {
        return matrix.size();
    }
}