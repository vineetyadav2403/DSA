import java.util.*;

class BellmanFordShortestPath {
    static class Edge {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    static class Graph {
        int V, E;
        Edge[] edges;

        Graph(int v, int e) {
            V = v;
            E = e;
            edges = new Edge[E];
            for (int i = 0; i < e; ++i)
                edges[i] = new Edge(0, 0, 0);
        }

        void addEdge(int src, int dest, int weight, int edgeCount) {
            edges[edgeCount] = new Edge(src, dest, weight);
        }

        void bellmanFord(int source) {
            int[] dist = new int[V];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[source] = 0;

            // Relax all edges V-1 times
            for (int i = 0; i < V - 1; ++i) {
                for (int j = 0; j < E; ++j) {
                    int u = edges[j].src;
                    int v = edges[j].dest;
                    int weight = edges[j].weight;
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v])
                        dist[v] = dist[u] + weight;
                }
            }

            // Check for negative weight cycles
            for (int j = 0; j < E; ++j) {
                int u = edges[j].src;
                int v = edges[j].dest;
                int weight = edges[j].weight;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    System.out.println("Graph contains negative weight cycle");
                    return;
                }
            }

            // Print the shortest distances from source
            System.out.println("Shortest distances from source " + source + ":");
            for (int i = 0; i < V; ++i) {
                System.out.println("Vertex " + i + ": " + dist[i]);
            }
        }
    }

    public static void main(String[] args) {
        int V = 5;
        int E = 8;
        Graph graph = new Graph(V, E);
        graph.addEdge(0, 1, -1, 0);
        graph.addEdge(0, 2, 4, 1);
        graph.addEdge(1, 2, 3, 2);
        graph.addEdge(1, 3, 2, 3);
        graph.addEdge(1, 4, 2, 4);
        graph.addEdge(3, 2, 5, 5);
        graph.addEdge(3, 1, 1, 6);
        graph.addEdge(4, 3, -3, 7);

        graph.bellmanFord(0);
    }
}
