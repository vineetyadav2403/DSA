import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalBFS {
    public static void main(String[] args) {
        List<List<Integer>> dis = new ArrayList<>();
        dis.add(Arrays.asList(0,1));
        dis.add(Arrays.asList(0,2));
        dis.add(Arrays.asList(2,3));
        dis.add(Arrays.asList(1,3));

        System.out.println(findOrder(4, dis));
    }

    public static List<Integer> findOrder(int n, List<List<Integer>> edges) {
        List<List<Integer>> adj = new ArrayList<>();
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
            indegree[i] = 0;
        }

        for(List<Integer> edge: edges){
            adj.get(edge.get(0)).add(edge.get(1));
            indegree[edge.get(1)] += 1;
        }
        List<Integer> ans = new ArrayList<>();

        if(kahnsAlgo(adj, n, indegree, ans))
            return ans;
        return new ArrayList<>();
    }

    private static boolean kahnsAlgo(List<List<Integer>> adj, int n, int[] indegree, List<Integer> ans) {
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if(indegree[i]==0)
                queue.add(i);
        }
        int count = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for(Integer node: adj.get(current)){
                indegree[node] -= 1;
                if(indegree[node]==0)
                    queue.add(node);
            }
            ans.add(current);
            count++;
        }
        if(count!=n)
            return false;
        return true;
    }
}
