import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnionByRank {
    public static void main(String[] args) {
        Data[] dsuf = new Data[5];
        for (int i = 0; i < dsuf.length; i++) {
            dsuf[i] = new Data(-1, 0);
        }
        List<List<Integer>> dis = new ArrayList<>();
        dis.add(Arrays.asList(0,1));
        dis.add(Arrays.asList(0,2));
        dis.add(Arrays.asList(2,3));
        dis.add(Arrays.asList(1,4));

        System.out.println(isCyclic(dis, dsuf));

    }

    public static int find(int v, Data[] dsuf) {
        if(dsuf[v].parent == -1)
            return v;
        return dsuf[v].parent = find(dsuf[v].parent, dsuf);
    }

    public static void union_op(int from, int to, Data[] dsuf) {
        if(dsuf[from].rank > dsuf[to].rank) {
            dsuf[to].parent = from;
        } else if(dsuf[from].rank < dsuf[to].rank) {
            dsuf[from].parent = to;
        } else {
            dsuf[from].parent = to;
            dsuf[to].rank += 1;
        }
    }

    public static boolean isCyclic(List<List<Integer>> edges, Data[] dsuf) {
        for(List<Integer> edge: edges) {
            int from = find(edge.get(0), dsuf);
            int to = find(edge.get(1), dsuf);
            if(from==to)
                return true;
            union_op(from, to, dsuf);
        }
        return false;
    }

}
class Data {
    int parent;
    int rank;
    public Data(int parent, int rank){
        this.parent = parent;
        this.rank = rank;
    }
}