import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


public class CSE222Graph {

    private Map<Coordinate, List<Coordinate>> graph;

    private CSE222Map map;

    public CSE222Graph(CSE222Map map) {
        this.map = map;
        this.graph = new HashMap<>();

        this.fillGraph();
    }

    private void fillGraph() {
        int[][] field = this.map.getMap();

        for (int y = 0; y < field.length; y++) {

            for (int x = 0; x < field[0].length; x++) {
                if (field[y][x] == 0) {
                    Coordinate curr = new Coordinate(y,x);
                    List<Coordinate> connectedNodes = new ArrayList<>();
                    int xBorder = field[0].length - 1;
                    int yBorder = field.length - 1;

                    if (y > 0 && field[y-1][x] == 0) {
                        connectedNodes.add(new Coordinate(y-1, x));
                    }
                    if (y < yBorder && field[y+1][x] == 0) {
                        connectedNodes.add(new Coordinate(y+1, x));
                    }
                    if (x > 0 && field[y][x-1] == 0) {
                        connectedNodes.add(new Coordinate(y, x-1));
                    }
                    if (x < xBorder && field[y][x+1] == 0) {
                        connectedNodes.add(new Coordinate(y, x+1));
                    }
                    if (y > 0 && x > 0 && field[y-1][x-1] == 0) {
                        connectedNodes.add(new Coordinate(y-1, x-1));
                    }
                    if (y > 0 && x < xBorder && field[y-1][x+1] == 0) {
                        connectedNodes.add(new Coordinate(y-1, x+1));
                    }
                    if (y < yBorder && x < xBorder && field[y+1][x+1] == 0) {
                        connectedNodes.add(new Coordinate(y+1, x+1));
                    }
                    if (y < yBorder && x > 0 && field[y+1][x-1] == 0) {
                        connectedNodes.add(new Coordinate(y+1, x-1));
                    }

                    this.graph.put(curr, connectedNodes);
                }
            }
        }
    }

    public CSE222Map getMap() {
        return map;
    }

    public Map<Coordinate, List<Coordinate>> getGraph() {
        return graph;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder("CSE222Graph{");
        sb.append("graph=").append(graph);
        sb.append('}');
        return sb.toString();
    }
}
