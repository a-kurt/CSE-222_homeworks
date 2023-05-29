import java.util.*;

public class CSE222Dijkstra {
    public static void main(String[] args) {
        CSE222Map m = new CSE222Map("/home/atakan/Desktop/CSE-222_homeworks/HW8_200104004044/src/test.txt");
        CSE222Graph g = new CSE222Graph(m);
        CSE222Dijkstra dijkstra = new CSE222Dijkstra(g);
        dijkstra.findPath();
        List<Coordinate> path = dijkstra.getPath();  // Store the path
        for (Coordinate p1 : path) {
            System.out.println(p1.toString());
        }
    }

    private CSE222Graph graph;
    private List<Coordinate> path;

    public CSE222Dijkstra(CSE222Graph graph) {
        this.graph = graph;
        this.path = new ArrayList<>();
    }

    public List<Coordinate> findPath() {
        Map<Coordinate, Double> distances = new HashMap<>();
        Map<Coordinate, Coordinate> parent = new HashMap<>();
        PriorityQueue<Coordinate> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));
        Coordinate start = this.graph.getMap().getStart();
        Coordinate end = this.graph.getMap().getEnd();

        for (Coordinate coordinate : this.graph.getGraph().keySet()) {
            if (coordinate.equals(start))
                distances.put(coordinate, 0.0);
            else
                distances.put(coordinate, Double.MAX_VALUE);
            priorityQueue.offer(coordinate);
        }

        while (!priorityQueue.isEmpty()) {
            Coordinate curr = priorityQueue.poll();
            if (curr.equals(end)) {
                break;
            }

            List<Coordinate> neighbors = this.graph.getGraph().get(curr);
            for (Coordinate neighbor : neighbors) {
                double dist = Math.sqrt((curr.getX() - neighbor.getX()) * (curr.getX() - neighbor.getX()) + (curr.getY() - neighbor.getY()) * (curr.getY() - neighbor.getY()) );
                Double newDistance = distances.get(curr) + dist;
                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    parent.put(neighbor, curr);
                    priorityQueue.remove(neighbor);
                    priorityQueue.offer(neighbor);
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
