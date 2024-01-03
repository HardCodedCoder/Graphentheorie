package at.fhburgenland;

import at.fhburgenland.interfaces.Service;

import java.util.*;

/**
 * This service calculates the optimal route between two nodes in a graph using Dijkstra's algorithm.
 * It is designed to handle directed graphs and computes the shortest path from a single source node
 * to all other nodes in the graph.
 */
public class GraphCalculationService implements Service {

    /**
     * The graph on which to calculate the shortest paths.
     */
    private final Graph graph;

    /**
     * Shortest distances from the source node to every other node.
     */
    private final int[] distances;

    /**
     * Nodes for which the shortest distance has been found.
     */
    private final Set<Integer> settledNodes;

    /**
     * Nodes prioritized by shortest distance.
     */
    private final PriorityQueue<Node> priorityQueue;

    /**
     * Total number of nodes in the graph.
     */
    private final int numberOfNodes;

    /**
     * Previous node in the shortest path.
     */
    private final Map<Integer, Integer> predecessors;

    /**
     * Constructs a new GraphCalculationService with a specified graph.
     *
     * @param graph The graph on which the shortest paths will be calculated.
     * @throws IllegalArgumentException if the provided graph is null.
     */
    public GraphCalculationService(Graph graph) {
        if (graph == null)
            throw new IllegalArgumentException("Graph cannot be null.");
        this.graph = graph;
        this.numberOfNodes = graph.getNumberOfNodes();
        this.distances = new int[numberOfNodes];
        this.settledNodes = new HashSet<>();
        this.priorityQueue = new PriorityQueue<>(numberOfNodes, Comparator.comparingInt(node -> distances[node.nodeIndex]));
        this.predecessors = new HashMap<>();
    }

    /**
     * Executes the graph calculation service, finding the shortest path from a designated start node to all other nodes.
     * The results, including shortest distance and path to a specific node, are printed to the console.
     */
    @Override
    public void runService() {
        this.calculateShortestPathFromSource(0);
        int distanceToZ = this.getDistanceTo(9);
        List<Integer> pathToZ = this.getShortestPathTo(9);
        List<String> pathLabels = new ArrayList<>();
        for (Integer nodeIndex : pathToZ) {
            pathLabels.add(graph.getNodeLabelMap().get(nodeIndex));
        }
        System.out.println("Shortest distance from S to Z: " + distanceToZ);
        System.out.print("Shortest path from S to Z: ");
        for (String label : pathLabels) {
            if(!label.equals("Z")){
                System.out.print(label + "->");
            } else {
                System.out.print(label);
            }

        }
    }

    /**
     * Exits the graph calculation service.
     */
    @Override
    public void exit() {
    }

    /**
     * Calculates the shortest path from the start node to all other nodes using Dijkstra's algorithm.
     * This method initializes the distances, processes each node using a priority queue,
     * and updates distances and predecessors accordingly.
     *
     * @param startNodeIndex The index of the start node.
     */
    public void calculateShortestPathFromSource(int startNodeIndex) {
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startNodeIndex] = 0;
        priorityQueue.add(new Node(startNodeIndex));

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();

            if (!settledNodes.contains(currentNode.nodeIndex)) {
                settledNodes.add(currentNode.nodeIndex);
                evaluateNeighbors(currentNode);
            }
        }
    }

    /**
     * Evaluates the neighbors of the current node, updating their distances and predecessors if a shorter path is found.
     *
     * @param currentNode The current node being processed.
     */
    private void evaluateNeighbors(Node currentNode) {
        int edgeDistance;
        int newDistance;

        for (int destinationNodeIndex = 0; destinationNodeIndex < numberOfNodes; destinationNodeIndex++) {
            if (!settledNodes.contains(destinationNodeIndex)) {
                if (graph.getEdge(currentNode.nodeIndex, destinationNodeIndex) > 0) {
                    edgeDistance = graph.getEdge(currentNode.nodeIndex, destinationNodeIndex);
                    newDistance = distances[currentNode.nodeIndex] + edgeDistance;

                    if (newDistance < distances[destinationNodeIndex]) {
                        distances[destinationNodeIndex] = newDistance;
                        predecessors.put(destinationNodeIndex, currentNode.nodeIndex);
                        priorityQueue.add(new Node(destinationNodeIndex));
                    }
                }
            }
        }
    }

    /**
     * Returns the shortest path to the destination node as a list of node indices, reconstructed from the predecessors map.
     *
     * @param destinationIndex The index of the destination node.
     * @return A list of node indices representing the shortest path to the destination node, or an empty list if no path exists.
     */
    public List<Integer> getShortestPathTo(int destinationIndex) {
        LinkedList<Integer> path = new LinkedList<>();
        Integer step = destinationIndex;

        if (predecessors.get(step) == null) {
            return path; // Empty list signifies no path found
        }

        path.add(step);
        while ((step = predecessors.get(step)) != null) {
            path.add(step);
        }

        Collections.reverse(path);
        return path;
    }

    /**
     * Returns the distance to the destination node as calculated by Dijkstra's algorithm.
     *
     * @param destinationIndex The index of the destination node.
     * @return The shortest distance to the destination node, or Integer.MAX_VALUE if no path exists.
     */
    public int getDistanceTo(int destinationIndex) {
        return distances[destinationIndex];
    }


    /**
     * Represents a node within the graph, primarily used within the priority queue for Dijkstra's algorithm.
     */
    private static class Node {
        int nodeIndex;

        /**
         * Constructs a new Node with a specified index.
         *
         * @param nodeIndex The index of the node within the graph.
         */
        public Node(int nodeIndex) {
            this.nodeIndex = nodeIndex;
        }
    }
}
