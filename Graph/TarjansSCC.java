import java.util.*;

class TarjansAlgorithm {
    static class Graph {
        private int V;
        private List<List<Integer>> adj;
        private int id;
        private int[] ids, low;
        private boolean[] onStack;
        private Stack<Integer> stack;

        Graph(int V) {
            this.V = V;
            adj = new ArrayList<>(V);
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }
        }

        void addEdge(int v, int w) {
            adj.get(v).add(w);
        }

        List<List<Integer>> tarjansSCC() {
            id = 0;
            ids = new int[V];
            low = new int[V];
            onStack = new boolean[V];
            stack = new Stack<>();
            Arrays.fill(ids, -1);

            List<List<Integer>> sccs = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                if (ids[i] == -1) {
                    dfs(i, sccs);
                }
            }
            return sccs;
        }

        void dfs(int at, List<List<Integer>> sccs) {
            stack.push(at);
            onStack[at] = true;
            ids[at] = low[at] = id++;
            for (int to : adj.get(at)) {
                if (ids[to] == -1) {
                    dfs(to, sccs);
                }
                if (onStack[to]) {
                    low[at] = Math.min(low[at], low[to]);
                }
            }

            if (ids[at] == low[at]) {
                List<Integer> component = new ArrayList<>();
                while (true) {
                    int node = stack.pop();
                    component.add(node);
                    onStack[node] = false;
                    if (node == at) break;
                }
                sccs.add(component);
            }
        }
    }

    public static void main(String[] args) {
        int V = 8;
        Graph graph = new Graph(V);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 3);
        graph.addEdge(2, 6);
        graph.addEdge(6, 7);
        graph.addEdge(7, 6);
        graph.addEdge(4, 6);

        List<List<Integer>> sccs = graph.tarjansSCC();
        System.out.println("Strongly Connected Components:");
        for (List<Integer> scc : sccs) {
            System.out.println(scc);
        }
    }
}
