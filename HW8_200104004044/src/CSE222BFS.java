import java.util.*;

public class CSE222BFS {

    public static void main(String[] args) {
        CSE222Map m = new CSE222Map("/home/atakan/Desktop/CSE-222_homeworks/HW8_200104004044/src/test.txt");
        CSE222Graph g = new CSE222Graph(m);
        CSE222BFS bfs = new CSE222BFS(g);
        bfs.findPath();
        List<Coordinate> path = bfs.getPath();  // Store the path
        for (Coordinate p1 : path) {
            System.out.println(p1.toString());
        }
    }

    private CSE222Graph graph;
    private List<Coordinate> path;

    public CSE222BFS(CSE222Graph graph) {
        this.graph = graph;
        this.path = new ArrayList<>();
    }

    public List<Coordinate> findPath() {
        Map<Coordinate, Boolean> visited = new HashMap<>();
        Map<Coordinate, Coordinate> parent = new HashMap<>();
        Queue<Coordinate> queue = new LinkedList<>();
        Coordinate start = this.graph.getMap().getStart();
        Coordinate end = this.graph.getMap().getEnd();

        for (Coordinate coordinate : this.graph.getGraph().keySet()) {
            visited.put(coordinate, false);
        }

        visited.put(start, true);
        queue.offer(start);

        while(!queue.isEmpty()) {
            Coordinate curr = queue.poll();

            if (curr.equals(end)) {
                break;
            }

            for (Coordinate neighbor : this.graph.getGraph().get(curr)) {
                if (visited.get(neighbor) == false) {
                    visited.put(neighbor, true);
                    parent.put(neighbor, curr);
                    queue.offer(neighbor);
                }
            }
        }

        Coordinate current = end;
        while (current != null) {
            this.path.add(0, current);
            current = parent.get(current);
        }

        return this.path;
    }

    public List<Coordinate> getPath() {
        return path;
    }
}
