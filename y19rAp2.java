// Parcels

import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y19rAp2 {
    private static PrintWriter pw = new PrintWriter(System.out);
    private static InputReader sc = new InputReader();

    private static final int intmax = Integer.MAX_VALUE, intmin = Integer.MIN_VALUE;

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

        private String readString()throws IOException{
            next();
            return tk.nextToken();
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
        final int row = sc.nextInt(), col = sc.nextInt();

        int grid[][] = new int[row][col], dist[][] = new int[row][col], maxDist = 0;

        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0; i < row; i++){
            char s[] = sc.readString().toCharArray();

            for(int j = 0; j < col; j++){
                grid[i][j] = s[j] - '0';
                
                if(grid[i][j] == 0)
                    dist[i][j] = intmax;
                else
                    queue.add(new int[]{i, j});
            }
        }

        while(!queue.isEmpty()){
            int point[] = queue.poll();

            maxDist = Math.max(maxDist, dist[point[0]][point[1]]);

            int dirs[][] = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

            for(int dir[]: dirs){
                int x = point[0] + dir[0], y = point[1] + dir[1];

                if(x >= 0 && x < row && y >= 0 && y < col && dist[x][y] > dist[point[0]][point[1]] + 1){
                    dist[x][y] = dist[point[0]][point[1]] + 1;
                    queue.add(new int[]{x, y});
                }
            }
        }

        ArrayList<int[]> distList[] = new ArrayList[maxDist + 1];

        for(int temp = 0; temp <= maxDist; temp++)
            distList[temp] = new ArrayList<>();
        
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++)
                distList[dist[i][j]].add(new int[]{i, j});
        }

        int low = 0, high = maxDist;

        bs:while(low <= high){
            int mid = low + (high - low)/2;

            if(mid == maxDist)
                high = mid - 1;
            else{
                int data1[] = {intmax, intmin}, data2[] = {intmax, intmin};

                for(int temp = mid + 1; temp <= maxDist; temp++){
                    for(int point[] : distList[temp]){

                        data1[0] = Math.min(data1[0], point[0] + point[1]);
                        data1[1] = Math.max(data1[1], point[0] + point[1]);

                        data2[0] = Math.min(data2[0], point[0] - point[1]);
                        data2[1] = Math.max(data2[1], point[0] - point[1]);
                    }
                }

                for(int i = 0; i < row; i++){
                    for(int j = 0; j < col; j++){
                        int currMaxDist = intmin;

                        currMaxDist = Math.max(currMaxDist, Math.abs(i + j - data1[0]));
                        currMaxDist = Math.max(currMaxDist, Math.abs(i + j - data1[1]));

                        currMaxDist = Math.max(currMaxDist, Math.abs(i - j - data2[0]));
                        currMaxDist = Math.max(currMaxDist, Math.abs(i - j - data2[1]));
   
                        if(currMaxDist <= mid){
                            high = mid - 1;
                            continue bs;
                        }
                    }
                }

                low = mid + 1;
            }
        }

        pw.println(low);
    }
}