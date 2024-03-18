import java.util.*;

class TarjansCutVertices {
    static class Graph {
        private int V;
        private List<List<Integer>> adj;
        private int id, rootChildren;
        private int[] ids, low;
        private boolean[] visited;
        private boolean[] isCutVertex;

        Graph(int V) {
            this.V = V;
            adj = new ArrayList<>(V);
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }
        }

        void addEdge(int v, int w) {
            adj.get(v).add(w);
            adj.get(w).add(v); // For undirected graph
        }

        List<Integer> findCutVertices() {
            id = 0;
            ids = new int[V];
            low = new int[V];
            visited = new boolean[V];
            isCutVertex = new boolean[V];
            Arrays.fill(ids, -1);

            List<Integer> cutVertices = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                if (!visited[i]) {
                    rootChildren = 0;
                    dfs(i, -1, cutVertices);
                    isCutVertex[i] = (rootChildren > 1); // Check if root is cut vertex
                }
            }
            return cutVertices;
        }

        void dfs(int at, int parent, List<Integer> cutVertices) {
            visited[at] = true;
            ids[at] = low[at] = id++;

            for (int to : adj.get(at)) {
                if (to == parent) continue;

                if (!visited[to]) {
                    if (parent == -1) rootChildren++;

                    dfs(to, at, cutVertices);

                    low[at] = Math.min(low[at], low[to]);

                    // Check for cut vertex
                    if (ids[at] <= low[to]) {
                        isCutVertex[at] = true;
                    }
                } else {
                    low[at] = Math.min(low[at], ids[to]);
                }
            }

            if (parent != -1 && ids[at] <= low[at]) {
                cutVertices.add(at);
            }
        }
    }

    public static void main(String[] args) {
        int V = 7;
        Graph graph = new Graph(V);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);
        graph.addEdge(4, 5);

        List<Integer> cutVertices = graph.findCutVertices();
        System.out.println("Cut Vertices:");
        for (int vertex : cutVertices) {
            System.out.println(vertex);
        }
    }
}
