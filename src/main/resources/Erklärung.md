# Graph Calculation Service

Dieses Dokument erklärt die Implementierung des Dijkstra-Algorithmus in der Klasse `GraphCalculationService` in Java. Der Dijkstra-Algorithmus findet den kürzesten Weg von einem Startknoten zu allen anderen Knoten in einem Graphen.

## Klasse `GraphCalculationService`

### Variablen:
- `graph`: Der Graph, auf dem der Algorithmus ausgeführt wird.
- `distances`: Ein Array, das die kürzeste Distanz vom Startknoten zu jedem anderen Knoten speichert.
- `settledNodes`: Eine Menge von Knoten, deren kürzeste Distanzen bereits festgelegt wurden.
- `priorityQueue`: Eine Prioritätswarteschlange, die verwendet wird, um den nächsten zu verarbeitenden Knoten basierend auf der kürzesten Distanz auszuwählen.
- `numberOfNodes`: Die Anzahl der Knoten im Graphen.
- `predecessors`: Eine Map, die den Vorgänger jedes Knotens auf dem kürzesten Weg vom Startknoten speichert.

### Methoden:
- `GraphCalculationService(Graph graph)`: Konstruktor der Klasse. Initialisiert die Variablen und prüft, ob der übergebene Graph nicht null ist.

- `runService()`: Führt den Dijkstra-Algorithmus vom Startknoten aus und druckt die Ergebnisse. Berechnet den kürzesten Pfad vom Knoten S zum Knoten Z und druckt die Distanz sowie den Pfad.

- `exit()`: Eine Methode, die in diesem Fall leer ist, aber überschrieben werden kann, um zum Beispiel Ressourcen freizugeben oder den Service sauber zu beenden.

- `calculateShortestPathFromSource(int startNodeIndex)`: Die Hauptmethode des Dijkstra-Algorithmus. Berechnet die kürzesten Pfade vom angegebenen Startknoten zu allen anderen Knoten.

- `evaluateNeighbors(Node currentNode)`: Hilfsmethode, die von `calculateShortestPathFromSource` aufgerufen wird. Aktualisiert die kürzesten Distanzen zu den Nachbarn des aktuellen Knotens.

- `getShortestPathTo(int destinationIndex)`: Gibt den kürzesten Pfad zum angegebenen Zielknoten als Liste von Knotenindizes zurück. Es wird die Map `predecessors` verwendet, um den Pfad rückwärts zu rekonstruieren.

- `getDistanceTo(int destinationIndex)`: Gibt die kürzeste Distanz zum angegebenen Zielknoten zurück.

### Innere Klasse `Node`

- `nodeIndex`: Der Index des Knotens im Graphen.

- `Node(int nodeIndex)`: Konstruktor der Klasse.

### Funktionsweise des Dijkstra-Algorithmus:

1. **Initialisierung**: Alle Distanzen werden auf den maximal möglichen Wert gesetzt, außer die Distanz des Startknotens zu sich selbst, die auf 0 gesetzt wird.

2. **Hinzufügen des Startknotens zur Prioritätswarteschlange**: Der Startknoten wird zur Prioritätswarteschlange hinzugefügt.

3. **Bearbeitung der Knoten**: Solange die Prioritätswarteschlange nicht leer ist, wird der Knoten mit der kleinsten Distanz ausgewählt und verarbeitet. Das bedeutet, dass seine Distanz als endgültig betrachtet wird und seine Nachbarn evaluiert werden. Für jeden Nachbarknoten wird geprüft, ob ein kürzerer Pfad zum Nachbarknoten über den aktuellen Knoten führt. Wenn ja, wird die Distanz aktualisiert und der Nachbarknoten zur Prioritätswarteschlange hinzugefügt.

4. **Ergebnis**: Am Ende der Methode `calculateShortestPathFromSource` enthält das Array `distances` die kürzesten Distanzen zu jedem Knoten, und die Map `predecessors` enthält die Vorgänger auf den kürzesten Pfaden.


## Methode `runService()`

Die `runService()` Methode ist der Einstiegspunkt, der die Ausführung des Dijkstra-Algorithmus und die Ausgabe der Ergebnisse steuert. Hier ist die detaillierte Abfolge der Schritte und Methodenaufrufe:

1. **Ausführen des Dijkstra-Algorithmus:**
    - Zuerst wird der Dijkstra-Algorithmus durch einen Aufruf von `calculateShortestPathFromSource(0)` ausgeführt. Hier ist '0' der Index des Startknotens (angenommen S). Diese Methode ist der Kern des Dijkstra-Algorithmus und verantwortlich für die Berechnung der kürzesten Pfade.

2. **Details zu `calculateShortestPathFromSource(int startNodeIndex)`:**
    - Die Methode initialisiert zuerst alle Distanzen zu `Integer.MAX_VALUE`, außer die Distanz vom Startknoten zu sich selbst, die auf 0 gesetzt wird.
    - Der Startknoten wird dann in eine Prioritätswarteschlange eingefügt, die Knoten basierend auf ihrer aktuellen kürzesten Distanz ordnet.
    - Solange die Prioritätswarteschlange nicht leer ist, wird der Knoten mit der geringsten Distanz entnommen (`poll()`). Dieser Knoten ist der nächste "aktuelle Knoten".
    - Für den aktuellen Knoten werden dann alle noch nicht abgearbeiteten Nachbarn betrachtet. Dies geschieht in der Methode `evaluateNeighbors(Node currentNode)`. Für jeden Nachbarn wird überprüft, ob der Weg über den aktuellen Knoten zu einer kürzeren Distanz führt. Wenn ja, wird die Distanz des Nachbarn aktualisiert und er wird (wieder) zur Prioritätswarteschlange hinzugefügt.
    - Dieser Prozess wiederholt sich, bis die Prioritätswarteschlange leer ist, was bedeutet, dass die kürzesten Wege zu allen erreichbaren Knoten gefunden wurden.

3. **Erhalten der Ergebnisse:**
    - Nachdem `calculateShortestPathFromSource` fertig ist, werden die Ergebnisse der Berechnung genutzt. Zuerst wird die kürzeste Distanz zum Zielknoten (angenommen Z mit Index 9) mit `getDistanceTo(9)` abgerufen.
    - Anschließend wird der tatsächliche kürzeste Pfad zum Zielknoten mit `getShortestPathTo(9)` abgerufen. Diese Methode rekonstruiert den Pfad rückwärts von Z nach S mithilfe der `predecessors` Map und gibt ihn dann in umgekehrter Reihenfolge zurück.

4. **Konvertieren der Knotenindizes zu Labels:**
    - Der abgerufene Pfad besteht aus einer Liste von Knotenindizes. Da diese Indizes alleine nicht sehr aussagekräftig sind, werden sie in Labels (z.B. "S", "A", "B", ...) umgewandelt, indem die `getNodeLabelMap`-Methode des Graphen genutzt wird. Dies macht die Ausgabe benutzerfreundlicher.

5. **Ausgabe der Ergebnisse:**
    - Schließlich wird die kürzeste Distanz und der kürzeste Pfad (in Form von Knotenlabels) auf der Konsole ausgegeben. Die Distanz wird direkt ausgegeben, während für den Pfad eine Schleife durchläuft, um ihn als Folge von Knotenlabels (z.B. "S -> A -> B -> ... -> Z") zu präsentieren.


### Ausgabe:

In der Methode `runService()` wird der kürzeste Weg vom Startknoten zum Zielknoten berechnet, die kürzeste Distanz und der Pfad ausgegeben. Für den Pfad werden die Labels der Knoten anstatt ihrer Indizes verwendet, um die Ausgabe benutzerfreundlicher zu gestalten.
