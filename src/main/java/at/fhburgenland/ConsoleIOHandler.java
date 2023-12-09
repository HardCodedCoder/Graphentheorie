package at.fhburgenland;

import java.util.Map;

public class ConsoleIOHandler {
    public void printGraph(Graph graph) {
        int numberOfNodes = graph.getNumberOfNodes();
        Map<Integer, String> labelsMap = graph.getNodeLabelMap();

        for (int i = 0; i < numberOfNodes; i++) {
            String label = labelsMap.get(i);
            System.out.println("Knoten " + label + " verbindet zu:");

            for (int j = 0; j < numberOfNodes; j++) {
                int weight = graph.getEdge(i, j);
                if (weight != 0) {
                    System.out.println(" -> " + labelsMap.get(j) + " mit Gewicht " + weight);
                }
            }

            System.out.println();
        }
    }
}
