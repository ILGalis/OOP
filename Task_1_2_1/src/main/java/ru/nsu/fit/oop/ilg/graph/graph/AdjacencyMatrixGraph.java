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
 * Граф, хранимый как матрица смежности.
 * Для каждой пары вершин хранится флаг наличия ребра.
 *
 * @param <V> тип значения вершины
 */
public class AdjacencyMatrixGraph<V> extends AbstractGraph<V> {
    private final Map<Vertex<V>, Map<Vertex<V>, Boolean>> matrix = new HashMap<>();

    @Override
    protected void doAddVertex(V value) {
        Vertex<V> vertex = new Vertex<>(value);
        matrix.put(vertex, new HashMap<>());
    }

    @Override
    protected void doRemoveVertex(V value) {
        Vertex<V> vertex = new Vertex<>(value);
        matrix.remove(vertex);

        for (Map<Vertex<V>, Boolean> row : matrix.values()) {
            row.remove(vertex);
        }
    }

    @Override
    protected void doAddEdge(V from, V to) {
        Vertex<V> fromVertex = new Vertex<>(from);
        Vertex<V> toVertex = new Vertex<>(to);
        matrix.get(fromVertex).put(toVertex, true);
    }

    @Override
    protected void doRemoveEdge(V from, V to) {
        Vertex<V> fromVertex = new Vertex<>(from);
        Vertex<V> toVertex = new Vertex<>(to);
        matrix.get(fromVertex).remove(toVertex);
    }

    @Override
    protected List<V> doGetNeighbors(V value) {
        List<V> result = new ArrayList<>();
        Map<Vertex<V>, Boolean> row = matrix.get(new Vertex<>(value));
        for (Vertex<V> neighbor : row.keySet()) {
            result.add(neighbor.getValue());
        }
        return result;
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
        return matrix.get(new Vertex<>(from))
                .containsKey(new Vertex<>(to));
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
        for (Map.Entry<Vertex<V>, Map<Vertex<V>, Boolean>> entry
                : matrix.entrySet()) {
            for (Vertex<V> neighbor : entry.getValue().keySet()) {
                edges.add(new Edge<>(entry.getKey(), neighbor, true));
            }
        }
        return edges;
    }

    @Override
    public int size() {
        return matrix.size();
    }
}