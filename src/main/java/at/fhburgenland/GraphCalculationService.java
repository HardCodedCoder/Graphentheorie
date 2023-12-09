package at.fhburgenland;

import at.fhburgenland.interfaces.Service;

/**
 * This service calculates the optimal route between two nodes.
 */
public class GraphCalculationService implements Service {

    /**
     * Holds the graph to perform the calculation on.
     */
    private Graph graph;

    /**
     * Initializes the service with the given graph.
     * @param graph The graph to perform the calculation on.
     */
    public GraphCalculationService(Graph graph) {
        if (graph == null)
            throw new IllegalArgumentException("Graph cannot be null.");
        this.graph = graph;
    }

    /**
     * Implements the main loop of the service.
     */
    @Override
    public void runService() {

    }

    /**
     * Exits the service.
     */
    @Override
    public void exit() {

    }
}
