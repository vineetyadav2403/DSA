import java.util.*;

class KruskalMinimumSpanningTree {
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.weight - edge.weight;
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

        int find(int[] parent, int i) {
            if (parent[i] != i)
                parent[i] = find(parent, parent[i]); // Path compression
            return parent[i];
        }

        void union(int[] parent, int[] rank, int x, int y) {
            int xRoot = find(parent, x);
            int yRoot = find(parent, y);

            if (rank[xRoot] < rank[yRoot])
                parent[xRoot] = yRoot;
            else if (rank[xRoot] > rank[yRoot])
                parent[yRoot] = xRoot;
            else {
                parent[yRoot] = xRoot;
                rank[xRoot]++;
            }
        }

        void kruskalMST() {
            Edge[] result = new Edge[V];
            int e = 0;
            int i = 0;
            for (i = 0; i < V; ++i)
                result[i] = new Edge(0, 0, 0);

            Arrays.sort(edges);

            int[] parent = new int[V];
            int[] rank = new int[V];

            for (int v = 0; v < V; ++v) {
                parent[v] = v;
                rank[v] = 0;
            }

            i = 0;

            while (e < V - 1 && i < E) {
                Edge nextEdge = edges[i++];
                int x = find(parent, nextEdge.src);
                int y = find(parent, nextEdge.dest);

                if (x != y) {
                    result[e++] = nextEdge;
                    union(parent, rank, x, y);
                }
            }

            System.out.println("Following are the edges in the constructed MST:");
            int minimumCost = 0;
            for (i = 0; i < e; ++i) {
                System.out.println(result[i].src + " - " + result[i].dest + ": " + result[i].weight);
                minimumCost += result[i].weight;
            }
            System.out.println("Total weight of MST: " + minimumCost);
        }
    }

    public static void main(String[] args) {
        int V = 4;
        int E = 5;
        Graph graph = new Graph(V, E);

        // Adding edges
        graph.edges[0] = new Edge(0, 1, 10);
        graph.edges[1] = new Edge(0, 2, 6);
        graph.edges[2] = new Edge(0, 3, 5);
        graph.edges[3] = new Edge(1, 3, 15);
        graph.edges[4] = new Edge(2, 3, 4);

        graph.kruskalMST();
    }
}
