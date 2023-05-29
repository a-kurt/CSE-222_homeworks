import java.util.List;

public class Main {
    public static void main(String[] args) {
        //testDijkstra("vatican.txt");
        testBFS("vatican.txt");
    }

    public static void testDijkstra(String fileName) {
        CSE222Map m = new CSE222Map(fileName);
        CSE222Graph g = new CSE222Graph(m);
        CSE222Dijkstra dijkstra = new CSE222Dijkstra(g);
        List<Coordinate> path = dijkstra.findPath();
        PNGGenerator pngGenerator = new PNGGenerator(m.getMap(), path);
        pngGenerator.convertPNG();
    }

    public static void testBFS(String fileName) {
        CSE222Map m = new CSE222Map(fileName);
        CSE222Graph g = new CSE222Graph(m);
        CSE222BFS bfs = new CSE222BFS(g);
        List<Coordinate> path = bfs.findPath();
        PNGGenerator pngGenerator = new PNGGenerator(m.getMap(), path);
        pngGenerator.convertPNG();
        m.writePath(path);
    }
}
