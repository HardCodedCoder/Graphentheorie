package at.fhburgenland;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a graph.
 */
@Slf4j
public class Graph {

    private int[][] adjacencyMatrix;
    private int numberOfNodes;

    private Map<Integer, String> nodeLabelMap;

    /**
     * Initializes a new instance of the Graph class.
     * @param numberOfNodes The number of nodes in the graph.
     */
    public Graph(int numberOfNodes) {
        log.info("Initializing graph...");
        if (numberOfNodes <= 0)
            throw new IllegalArgumentException("Number of nodes must be greater than 0.");
        log.info(String.format("Number of nodes: %d", numberOfNodes));
        this.numberOfNodes = numberOfNodes;
        this.nodeLabelMap = new HashMap<>();
        this.adjacencyMatrix = new int[numberOfNodes][numberOfNodes];
        this.initializeAdjacencyMatrix();
        this.applyLabels();
        log.info("Graph initialized.");
    }

    /**
     * Helper Method which initializes the adjacency matrix.
     */
    private void initializeAdjacencyMatrix() {
        log.info("Initializing adjacency matrix...");
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                adjacencyMatrix[i][j] = 0;
            }
        }
    }

    /**
     * Helper Method which applies labels to the nodes.
     */
    private void applyLabels() {
        nodeLabelMap.put(0, "S");

        if (numberOfNodes > 1) {
            nodeLabelMap.put(numberOfNodes - 1, "Z");
        }

        char label = 'A';
        for (int i = 1; i < numberOfNodes - 1; i++) {
            if (label == 'S')
                label++;

            nodeLabelMap.put(i, String.valueOf(label++));
        }
    }

    /**
     * Adds an edge to the graph.
     * @param row The y coordinate of the node to add the weight to.
     * @param column The x coordinate of the node to add the weight to.
     * @param weight The weight of the edge to add.
     */
    public void addEdge(int row, int column, int weight) {
        if  (row < 0 || row >= numberOfNodes || column < 0 || column >= numberOfNodes) {
            log.error("Invalid row or column. Node not added...");
            return;
        }

        adjacencyMatrix[row][column] = weight;
    }

    /**
     * Gets the weight of an edge.
     * @param row The y coordinate of the node to get the weight of.
     * @param column The x coordinate of the node to get the weight of.
     * @return The weight of the edge.
     */
    public int getEdge(int row, int column) {
        return adjacencyMatrix[row][column];
    }

    /**
     * Gets the number of nodes in the graph.
     * @return The number of nodes in the graph.
     */
    public int getNumberOfNodes() {
        return this.numberOfNodes;
    }

    public Map<Integer, String> getNodeLabelMap() {
        // TODO: Return only deep copy of nodeLabelMap.
        return this.nodeLabelMap;
    }
}
