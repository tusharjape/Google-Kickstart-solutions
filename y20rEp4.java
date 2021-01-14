// Golden Stone

import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y20rEp4 {
    private static PrintWriter pw = new PrintWriter(System.out);
    private static InputReader sc = new InputReader();
    private static final long inf = Long.MAX_VALUE;
    private static long dist[][];

    static class Pair implements Comparable<Pair>{
        int first, second;
        Pair(int first, int second){
            this.first = first;
            this.second = second;
        }
        public int compareTo(Pair o){
            return Long.compare(dist[first][second], dist[o.first][o.second]);
        }
    }

    static class InputReader{
        private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        private static StringTokenizer tk;

        private void next()throws IOException{
            while(tk == null || !tk.hasMoreTokens())   
                tk = new StringTokenizer(r.readLine());
        }

        private int nextInt()throws IOException{
            next();
            return Integer.parseInt(tk.nextToken());
        }
    }

    public static void main(String args[])throws IOException{
        int t = sc.nextInt();

        for(int i=1; i<=t; i++){
            pw.print("Case #"+i+": ");
            solve();
        }

        pw.flush();
        pw.close();
    }

    static class Recipe{
        ArrayList<Integer> ingredients;
        int result;

        Recipe(ArrayList<Integer> ingredients, int result){
            this.ingredients = ingredients;
            this.result = result;
        }
    }

    private static void solve()throws IOException{
        int n = sc.nextInt(), m = sc.nextInt(), s  = sc.nextInt(), r = sc.nextInt();

        dist = new long[n+1][s+1];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        ArrayList<ArrayList<Recipe>> recipe = new ArrayList<>();
        boolean vis[][] = new boolean[n+1][s+1];

        for(int i=0; i<=n; i++){
            graph.add(new ArrayList<>());
            Arrays.fill(dist[i], inf);
        }

        for(int i=0; i<=s; i++)
            recipe.add(new ArrayList<>());

        while(m-->0){
            int u = sc.nextInt(), v = sc.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        for(int i=1; i<=n; i++){
            int cnt = sc.nextInt();
            while(cnt-->0){
                int sType = sc.nextInt();
                dist[i][sType] = 0;
                pq.offer(new Pair(i, sType));
            }
        }

        while(r-->0){
            int cnt = sc.nextInt();

            ArrayList<Integer> ingredients = new ArrayList<>();
            while(cnt-->0)
                ingredients.add(sc.nextInt());
            
            int result = sc.nextInt();
            Recipe currRecipe = new Recipe(ingredients, result);

            for(Integer ingredient: ingredients)
                recipe.get(ingredient).add(currRecipe);
        }

        while(!pq.isEmpty()){

            Pair curr = pq.poll();
            int u = curr.first, sType = curr.second;

            if(dist[u][sType] >= (long)1e12){
                pw.println(-1);
                return;
            }

            if(sType == 1){
                pw.println(dist[u][sType]);
                return;
            }

            if(vis[u][sType])
                continue;

            for(Integer v: graph.get(u)){
                if(dist[v][sType] > dist[u][sType] + 1){
                    dist[v][sType] = dist[u][sType] + 1;
                    pq.offer(new Pair(v, sType));
                }
            }

            for(Recipe cr: recipe.get(sType)){
                long cost = 0;

                for(Integer ingredient: cr.ingredients){
                    if(dist[u][ingredient] == inf){
                        cost = inf;
                        break;
                    }
                    else
                        cost += dist[u][ingredient];
                }

                if(cost == inf)
                    continue;

                if(cost < dist[u][cr.result]){
                    dist[u][cr.result] = cost;
                    pq.offer(new Pair(u, cr.result));
                }
            }

            vis[u][sType] = true;
        }
    }
}