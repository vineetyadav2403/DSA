import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetectCycle {
    public static void main(String[] args) {
        List<List<Integer>> dis = new ArrayList<>();
        dis.add(Arrays.asList(1,2));
        dis.add(Arrays.asList(2,4));
        dis.add(Arrays.asList(4,5));
        dis.add(Arrays.asList(3,1));
        System.out.println(cycle(6, dis));
    }

    public static boolean cycle(int n, List<List<Integer>> edges) {
        List<List<Integer>> adj = new ArrayList<>();
        List<Integer> seen = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
            seen.add(0);
        }

        for(List<Integer> edge: edges){
            adj.get(edge.get(0)).add(edge.get(1));
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
