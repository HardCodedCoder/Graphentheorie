package at.fhburgenland;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class of the application. Creates a sample graph and runs the GraphCalculationService.
 */
public class Main {
    public static void main(String[] args) {
        Graph graph = createSampleGraph();
        GraphCalculationService graphCalculationService = new GraphCalculationService(graph);
        graphCalculationService.runService();
    }

    public static Graph createSampleGraph() {
        Graph graph = new Graph(10);

        graph.addEdge(0, 1,  10);  // S - A
        graph.addEdge(0, 2,  11);  // S - B
        graph.addEdge(1, 3,  30);  // A - C
        graph.addEdge(1, 4,   2);  // A - D
        graph.addEdge(2, 4,   1);  // B - D
        graph.addEdge(2, 6,   4);  // B - F
        graph.addEdge(2, 5,   7);  // B - E
        graph.addEdge(3, 9, 400);  // C - Z
        graph.addEdge(4, 8,   1);  // D - H
        graph.addEdge(5, 6,   2);  // E - F
        graph.addEdge(5, 7,   1);  // E - G
        graph.addEdge(6, 9,   5);  // F - Z
        graph.addEdge(8, 6,   1);  // H - F

        graph.addEdge(9, 3, 400);  // Z - C
        graph.addEdge(9, 6,   5);  // Z - F
        graph.addEdge(3, 1,  30);  // C - A
        graph.addEdge(1, 0,  10);  // A - S
        graph.addEdge(6, 8,   1);  // F - H
        graph.addEdge(6, 4,   2);  // F - E
        graph.addEdge(7, 5,   1);  // G - E
        graph.addEdge(5, 2,   7);  // E - B
        graph.addEdge(6, 2,   4);  // F - B
        graph.addEdge(4, 2,   1);  // D - B
        graph.addEdge(2, 0,  11);  // B - S
        graph.addEdge(8, 4,   1);  // H - D
        graph.addEdge(4, 1,   2);  // D - A

        ConsoleIOHandler ioHandler = new ConsoleIOHandler();
        ioHandler.printGraph(graph);
        return graph;
    }
}
