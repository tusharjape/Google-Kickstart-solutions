// Truck Delivery

import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y21rBp4 {
    private static PrintWriter pw = new PrintWriter(System.out);
    private static InputReader sc = new InputReader();

    private static final int intmax = 2*(int)1e6;

    static class Pair{
        int key, value;

        Pair(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    static class InputReader{
        private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        private static StringTokenizer tk;

        private void next()throws IOException{
            if(tk == null || !tk.hasMoreTokens())   
                tk = new StringTokenizer(r.readLine());
        }

        private int nextInt()throws IOException{
            next();
            return Integer.parseInt(tk.nextToken());
        }

        private long nextLong()throws IOException{
            next();
            return Long.parseLong(tk.nextToken());
        }
    }

    public static void main(String args[])throws IOException{
        int t = sc.nextInt();
        for(int i=1; i<=t; i++){
            pw.print("Case #"+i+": ");
            solve(i == 21);
        }

        pw.flush();
        pw.close();
    }

    static class Segment{
        int begin, end;
        long data;
        Segment left, right;

        Segment(int b, int e){
            begin = b;
            end = e;
            data = 0;
        }

        long gcd(long a, long b){
            if(b == 0)  return a;
            return gcd(b, a % b);
        }

        void update(int index, long value){
            if(begin == end){
                data = value;
                return;
            }

            if(left == null)
                left = new Segment(begin, (begin + end)/2);
            if(right == null)
                right = new Segment((begin + end)/2 + 1, end);

            if(index <= (begin + end)/2)
                left.update(index, value);
            else
                right.update(index, value);

            data = gcd(left.data, right.data);
        }

        long query(int lim){
            if(lim < begin || data == 0)
                return 0;
            
            if(end <= lim)
                return data;
            
            long ans = 0;

            if(left != null)    ans = gcd(ans, left.query(lim));
            if(right != null)   ans = gcd(ans, right.query(lim));

            return ans;
        }
    }

    static class Edge{
        long amt;
        int limit;

        Edge(int l, long a){
            this.amt = a;
            this.limit = l;
        }
    }

    private static ArrayList<ArrayList<Pair>> tree, queries;

    private static Segment root;
    private static Edge edges[];
    private static long ans[];

    private static void dfs(int node, int parent){
        for(Pair entry: queries.get(node)){
            int wt = entry.key, idx = entry.value;

            ans[idx] = root.query(wt);
        }

        for(Pair entry: tree.get(node)){
            int child = entry.key, edgeIdx = entry.value;

            if(child == parent) continue;

            Edge edge = edges[edgeIdx];

            root.update(edge.limit, edge.amt);

            dfs(child, node);

            root.update(edge.limit, 0);
        }
    }

    private static void solve(boolean flag)throws IOException{
        tree = new ArrayList<>();
        queries = new ArrayList<>();
        root = new Segment(1, intmax);

        int n = sc.nextInt(), q = sc.nextInt();
        
        ans = new long[q];
        edges = new Edge[n - 1];

        for(int i = 0; i < n; i++){
            tree.add(new ArrayList<>());
            queries.add(new ArrayList<>());
        }

        for(int i = 0; i < n - 1; i++){
            int x = sc.nextInt() - 1, y = sc.nextInt() - 1;

            edges[i] = new Edge(sc.nextInt(), sc.nextLong());

            tree.get(x).add(new Pair(y, i));
            tree.get(y).add(new Pair(x, i));
        }

        for(int i = 0; i < q; i++){
            int city = sc.nextInt() - 1, wt = sc.nextInt();

            queries.get(city).add(new Pair(wt, i));
        }

        dfs(0, -1);

        for(long a: ans)
            pw.print(a+" ");

        pw.println();
    }
}