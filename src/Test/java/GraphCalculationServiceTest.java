import at.fhburgenland.ConsoleIOHandler;
import at.fhburgenland.Graph;
import at.fhburgenland.GraphCalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GraphCalculationServiceTest {

    private Graph graph;
    private GraphCalculationService service;

    @BeforeEach
    void setUp() {
        graph = new Graph(5);
        graph.addEdge(0, 1, 10);  // S - A
        graph.addEdge(1, 0, 10);  // A - S (Rückrichtung)

        graph.addEdge(0, 2, 3);   // S - B
        graph.addEdge(2, 0, 3);   // B - S (Rückrichtung)

        graph.addEdge(1, 2, 1);   // A - B
        graph.addEdge(2, 1, 1);   // B - A (Rückrichtung)

        graph.addEdge(1, 3, 2);   // A - C
        graph.addEdge(3, 1, 2);   // C - A (Rückrichtung)

        graph.addEdge(2, 3, 8);   // B - C
        graph.addEdge(3, 2, 8);   // C - B (Rückrichtung)

        graph.addEdge(2, 4, 2);   // B - Z
        graph.addEdge(4, 2, 2);   // Z - B (Rückrichtung)

        graph.addEdge(3, 4, 9);   // C - Z
        graph.addEdge(4, 3, 9);   // Z - C (Rückrichtung)


        ConsoleIOHandler consoleIOHandler = new ConsoleIOHandler();
        consoleIOHandler.printGraph(graph);
        service = new GraphCalculationService(graph);
    }

    @Test
    void testCalculateShortestPathFromSourceReturnsCorrectDistances() {
        service.calculateShortestPathFromSource(0);
        assertEquals(0, service.getDistanceTo(0));
        assertEquals(5, service.getDistanceTo(4));
    }

    @Test
    void testGetShortestPathToReturnsCorrectPath() {
        service.calculateShortestPathFromSource(0);
        assertEquals(Arrays.asList(0, 2, 4), service.getShortestPathTo(4));
    }

    @Test
    void testInvalidGraphThrowsExceptionOnNullGraph() {
        assertThrows(IllegalArgumentException.class, () -> {
            new GraphCalculationService(null);
        });
    }

    @Test
    void testGetDistanceToInvalidNodeThrowsExceptionOnInvalidNode() {
        service.calculateShortestPathFromSource(0);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            service.getDistanceTo(10);
        });
    }
}
