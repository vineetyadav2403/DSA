import java.util.*;

class TarjansBridges {
    static class Graph {
        private int V;
        private List<List<Integer>> adj;
        private int id;
        private int[] low, ids;
        private boolean[] visited;
        private List<int[]> bridges;

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

        List<int[]> findBridges() {
            id = 0;
            low = new int[V];
            ids = new int[V];
            visited = new boolean[V];
            bridges = new ArrayList<>();
            Arrays.fill(ids, -1);

            for (int i = 0; i < V; i++) {
                if (!visited[i]) {
                    dfs(i, -1);
                }
            }
            return bridges;
        }

        void dfs(int at, int parent) {
            visited[at] = true;
            ids[at] = low[at] = id++;

            for (int to : adj.get(at)) {
                if (to == parent) continue;

                if (!visited[to]) {
                    dfs(to, at);
                    low[at] = Math.min(low[at], low[to]);
                    if (ids[at] < low[to]) {
                        bridges.add(new int[]{at, to});
                    }
                } else {
                    low[at] = Math.min(low[at], ids[to]);
                }
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

        List<int[]> bridges = graph.findBridges();
        System.out.println("Bridges:");
        for (int[] bridge : bridges) {
            System.out.println(bridge[0] + " - " + bridge[1]);
        }
    }
}
