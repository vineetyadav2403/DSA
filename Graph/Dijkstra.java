import java.util.*;

class DijkstraShortestPath {
    static class Node implements Comparable<Node> {
        int id, weight;

        Node(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    static class Graph {
        private int V;
        private List<List<Node>> adjList;

        Graph(int V) {
            this.V = V;
            adjList = new ArrayList<>(V);
            for (int i = 0; i < V; i++)
                adjList.add(new ArrayList<>());
        }

        void addEdge(int src, int dest, int weight) {
            adjList.get(src).add(new Node(dest, weight));
        }

        void dijkstra(int source) {
            PriorityQueue<Node> pq = new PriorityQueue<>();
            int[] dist = new int[V];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[source] = 0;
            pq.add(new Node(source, 0));

            while (!pq.isEmpty()) {
                Node node = pq.poll();
                int u = node.id;
                int uDist = node.weight;

                if (uDist > dist[u])
                    continue;

                for (Node neighbor : adjList.get(u)) {
                    int v = neighbor.id;
                    int weight = neighbor.weight;

                    if (dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                        pq.add(new Node(v, dist[v]));
                    }
                }
            }

            // Print the shortest distances from source
            System.out.println("Shortest distances from source " + source + ":");
            for (int i = 0; i < V; i++) {
                System.out.println("Vertex " + i + ": " + dist[i]);
            }
        }
    }

    public static void main(String[] args) {
        int V = 5;
        Graph graph = new Graph(V);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 5);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 1, 2);
        graph.addEdge(2, 3, 9);
        graph.addEdge(2, 4, 2);
        graph.addEdge(3, 4, 4);
        graph.addEdge(4, 3, 6);

        graph.dijkstra(0);
    }
}
