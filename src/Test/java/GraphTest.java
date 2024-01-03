import at.fhburgenland.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    private Graph graph;

    @BeforeEach
    void setUp() {
        graph = new Graph(5);
    }

    @Test
    void testGraphInitialization() {
        assertEquals(5, graph.getNumberOfNodes());
        for (int i = 0; i < graph.getNumberOfNodes(); i++) {
            for (int j = 0; j < graph.getNumberOfNodes(); j++) {
                assertEquals(0, graph.getEdge(i, j));
            }
        }
    }

    @Test
    void testAddEdge() {
        graph.addEdge(0, 1, 10);
        assertEquals(10, graph.getEdge(0, 1));
    }

    @Test
    void testGetEdge() {
        graph.addEdge(0, 1, 5);
        assertEquals(5, graph.getEdge(0, 1));
        assertEquals(0, graph.getEdge(1, 0));
    }

    @Test
    void testInvalidEdge() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            graph.getEdge(10, 10);
        });
    }

    @Test
    void testGetNodeLabelMap() {
        Map<Integer, String> labelMap = graph.getNodeLabelMap();
        assertEquals("S", labelMap.get(0));
        assertEquals("Z", labelMap.get(4));
    }
}
