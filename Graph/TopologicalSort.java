import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class TopologicalSort {
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
        List<Boolean> seen = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
            seen.add(false);
        }

        
        for(List<Integer> edge: edges){
            adj.get(edge.get(0)).add(edge.get(1));
        }
        List<Integer> ans = new ArrayList<>();

        if(cycle(n, adj)){
            return ans;
        }

        for (int i = 0; i < n; i++) {
            if(!seen.get(i))
                topologicalSort(adj, stack, i, seen);
        }

        while (!stack.empty()) {
            ans.add(stack.pop());
        }
        return ans;
    }

    public static void topologicalSort(List<List<Integer>> adj,
            Stack<Integer> stack,
            int i,
            List<Boolean> seen){

        seen.set(i, true);
        for(Integer node: adj.get(i)){
            if(!seen.get(node)){
                topologicalSort(adj, stack, node, seen);
            }
        }
        stack.push(i);
    }
    
    public static boolean cycle(int n, List<List<Integer>> adj) {
        List<Integer> seen = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            seen.add(0);
        }

        for (int i = 0; i < n; i++) {
            if(seen.get(i)==0){
                if(isCycle(adj, seen, i))
                    return true;
            }
        }
        return false;

    }

    public static boolean isCycle(List<List<Integer>> adj, List<Integer> seen, int node) {

        if(seen.get(node)==2)
            return true;

        seen.set(node, 2);

        for(Integer n: adj.get(node)){
            if(seen.get(n)!=1){
                if(isCycle(adj, seen, n)){
                    return true;
                }
            }
        }

        seen.set(node, 1);

        return false;
    }

}
