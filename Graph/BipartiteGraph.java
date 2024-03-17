import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BipartiteGraph {
    public static void main(String[] args) {
        List<List<Integer>> dis = new ArrayList<>();
        dis.add(Arrays.asList(1,2));
        dis.add(Arrays.asList(2,4));
        dis.add(Arrays.asList(4,5));
        dis.add(Arrays.asList(3,5));
        System.out.println(possibleBipartition(5, dis));
    }
    public static boolean possibleBipartition(int N, List<List<Integer>> dislikes) {
        List<List<Integer>> graph = new ArrayList<>();

        for(int i=0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for(List<Integer> edge: dislikes) {
            graph.get(edge.get(0)).add(edge.get(1));
            graph.get(edge.get(1)).add(edge.get(0));
        }
        Integer[] color = new Integer[N+1];
        Arrays.fill(color, -1);

        for(int i=1; i <= N; i++){
            if(color[i]==-1){
                if(!isBipartite(graph, N, i, color)){
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean isBipartite(List<List<Integer>> graph, Integer N, Integer node, Integer[] color){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        color[node] = 1;
        while (!queue.isEmpty()) {

            int n = queue.poll();
            for(int ele: graph.get(n)) {
                if(color[ele]==color[n]){
                    return false;
                } 
                if(color[ele]==-1) {
                    color[ele] = 1-color[n];
                    queue.add(n);
                }
            }          
        }
        return true;
    }
}