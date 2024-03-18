import java.util.*;

class KosarajuAlgorithm {
    static class Graph {
        private int V;
        private List<List<Integer>> adj;

        Graph(int v) {
            V = v;
            adj = new ArrayList<>(v);
            for (int i = 0; i < v; i++) {
                adj.add(new ArrayList<>());
            }
        }

        void addEdge(int v, int w) {
            adj.get(v).add(w);
        }

        void DFS(int v, boolean visited[], Stack<Integer> stack) {
            visited[v] = true;
            for (Integer i : adj.get(v))
                if (!visited[i])
                    DFS(i, visited, stack);
            stack.push(v);
        }

        void fillOrder(int v, boolean visited[], Stack<Integer> stack) {
            visited[v] = true;
            for (Integer i : adj.get(v))
                if (!visited[i])
                    fillOrder(i, visited, stack);
            stack.push(v);
        }

        Graph getTranspose() {
            Graph g = new Graph(V);
            for (int v = 0; v < V; v++) {
                for (Integer i : adj.get(v)) {
                    g.addEdge(i, v);
                }
            }
            return g;
        }

        void printSCCs() {
            Stack<Integer> stack = new Stack<>();
            boolean visited[] = new boolean[V];
            for (int i = 0; i < V; i++)
                visited[i] = false;
            for (int i = 0; i < V; i++)
                if (!visited[i])
                    fillOrder(i, visited, stack);

            Graph transposedGraph = getTranspose();

            for (int i = 0; i < V; i++)
                visited[i] = false;

            while (!stack.isEmpty()) {
                int v = stack.pop();
                if (!visited[v]) {
                    List<Integer> component = new ArrayList<>();
                    transposedGraph.DFS(v, visited, stack);
                    while (!stack.isEmpty()) {
                        component.add(stack.pop());
                    }
                    System.out.println(component);
                }
            }
        }
    }

    public static void main(String[] args) {
        int V = 5;
        Graph graph = new Graph(V);
        graph.addEdge(1, 0);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);

        graph.printSCCs();
    }
}
