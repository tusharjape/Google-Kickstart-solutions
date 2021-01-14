// Planet distance

import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y18rCp1 {
    private static PrintWriter pw = new PrintWriter(System.out);
    private static InputReader sc = new InputReader();

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
    }

    public static void main(String args[])throws IOException{
        int t = sc.nextInt();
        for(int i = 1; i <= t; i++){
            pw.print("Case #"+i+": ");
            solve();
        }

        pw.flush();
        pw.close();
    }

    private static void solve()throws IOException{
        int N = sc.nextInt();

        ArrayList<Integer> graph[] = new ArrayList[N];

        int distanceFromCycle[] = new int[N], adjacency[] = new int[N];

        for(int i = 0; i < N; i++){
            distanceFromCycle[i] = Integer.MAX_VALUE;
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < N; i++){
            int u = sc.nextInt() - 1, v = sc.nextInt() - 1;

            graph[u].add(v);
            graph[v].add(u);

            adjacency[u]++;
            adjacency[v]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> rq = new LinkedList<>();

        for(int i = 0; i < N; i++){
            if(adjacency[i] == 1)
                rq.add(i);
        }

        while(!rq.isEmpty()){
            int currNode = rq.poll();

            for(Integer neighbor: graph[currNode]){
                adjacency[neighbor]--;

                if(adjacency[neighbor] == 1)
                    rq.add(neighbor);
            }
        }

        for(int i = 0; i < N; i++){
            if(adjacency[i] == 2){
                queue.add(i);
                
                distanceFromCycle[i] = 0;
            }
        }

        while(!queue.isEmpty()){
            int currNode = queue.poll();

            for(Integer neighbor: graph[currNode]){
                if(distanceFromCycle[neighbor] > distanceFromCycle[currNode] + 1){
                    distanceFromCycle[neighbor] = distanceFromCycle[currNode] + 1;

                    queue.add(neighbor);
                }
            }
        }

        for(int distance: distanceFromCycle)
            pw.print(distance+" ");

        pw.println();
    }
}