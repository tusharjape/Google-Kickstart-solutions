// Rabbit House

import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y21rAp3 {
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
        for(int i=1; i<=t; i++){
            pw.print("Case #"+i+": ");
            solve();
        }

        pw.flush();
        pw.close();
    }

    private static void solve()throws IOException{
        final int r = sc.nextInt(), c = sc.nextInt();

        int grid[][] = new int[r][c];
        boolean mark[][] = new boolean[r][c];

        TreeMap<Integer, ArrayList<int[]>> map = new TreeMap<>();

        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                grid[i][j] = sc.nextInt();

                map.putIfAbsent(grid[i][j], new ArrayList<>());

                map.get(grid[i][j]).add(new int[]{i, j});
            }
        }

        final int dirs[][] = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        long ans = 0;

        while(!map.isEmpty()){
            int height = map.lastKey();

            ArrayList<int[]> set = map.get(height);

            map.remove(height);

            for(int[] pos: set){
                int x = pos[0], y = pos[1];

                if(mark[x][y])  continue;

                mark[x][y] = true;

                for(int[] dir: dirs){
                    int p = dir[0] + x, q = dir[1] + y;

                    if(p >= 0 && q >= 0 && p < r && q < c && grid[p][q] + 1 < grid[x][y]){
                        ans += grid[x][y] - 1 - grid[p][q];
                        grid[p][q] = grid[x][y] - 1;
                        
                        map.putIfAbsent(grid[p][q], new ArrayList<>());
                        
                        map.get(grid[p][q]).add(new int[]{p, q});
                    }
                }
            }
        }

        pw.println(ans);
    }
}