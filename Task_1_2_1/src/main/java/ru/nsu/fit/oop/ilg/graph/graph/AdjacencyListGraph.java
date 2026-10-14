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
 * Граф, хранимый как список смежности.
 * Для каждой вершины — список соседей.
 *
 * @param <V> тип значения вершины
 */
public class AdjacencyListGraph<V> extends AbstractGraph<V> {
    private final Map<Vertex<V>, List<Vertex<V>>> adjacency = new HashMap<>();

    @Override
    protected void doAddVertex(V value) {
        adjacency.put(new Vertex<>(value), new ArrayList<>());
    }

    @Override
    protected void doRemoveVertex(V value) {
        Vertex<V> vertex = new Vertex<>(value);
        adjacency.remove(vertex);

        // Удаляем все рёбра, ведущие в эту вершину
        for (List<Vertex<V>> neighbors : adjacency.values()) {
            neighbors.remove(vertex);
        }
    }

    @Override
    protected void doAddEdge(V from, V to) {
        Vertex<V> fromVertex = new Vertex<>(from);
        Vertex<V> toVertex = new Vertex<>(to);
        adjacency.get(fromVertex).add(toVertex);
    }

    @Override
    protected void doRemoveEdge(V from, V to) {
        Vertex<V> fromVertex = new Vertex<>(from);
        Vertex<V> toVertex = new Vertex<>(to);
        adjacency.get(fromVertex).remove(toVertex);
    }

    @Override
    protected List<V> doGetNeighbors(V value) {
        List<V> result = new ArrayList<>();
        for (Vertex<V> neighbor : adjacency.get(new Vertex<>(value))) {
            result.add(neighbor.getValue());
        }
        return result;
    }

    @Override
    public boolean hasVertex(V value) {
        return adjacency.containsKey(new Vertex<>(value));
    }

    @Override
    public boolean hasEdge(V from, V to) {
        if (!hasVertex(from)) {
            return false;
        }
        return adjacency.get(new Vertex<>(from)).contains(new Vertex<>(to));
    }

    @Override
    public Set<V> getVertices() {
        Set<V> result = new HashSet<>();
        for (Vertex<V> vertex : adjacency.keySet()) {
            result.add(vertex.getValue());
        }
        return result;
    }

    @Override
    public Set<Edge<V>> getEdges() {
        Set<Edge<V>> edges = new HashSet<>();
        for (Map.Entry<Vertex<V>, List<Vertex<V>>> entry : adjacency.entrySet()) {
            for (Vertex<V> neighbor : entry.getValue()) {
                edges.add(new Edge<>(entry.getKey(), neighbor, true));
            }
        }
        return edges;
    }

    @Override
    public int size() {
        return adjacency.size();
    }
}