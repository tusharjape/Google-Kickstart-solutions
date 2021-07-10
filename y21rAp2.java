// L Shaped Plots

import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y21rAp2 {
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

    private static long cal(int l1, int l2){
        if(l1 < 2 || l2 < 2)
            return 0;

        long ans = 0;

        int maxlen = Math.min(l2/2, l1);

        if(maxlen >= 2) ans += maxlen - 1;
        
        maxlen = Math.min(l1/2, l2);

        if(maxlen >= 2) ans += maxlen - 1;

        return ans;
    }

    private static void solve()throws IOException{
        final int r = sc.nextInt(), c = sc.nextInt();

        int arr[][] = new int[r][c];
        int up[][] = new int[r][c];
        int left[][] = new int[r][c];
        int down[][] = new int[r][c];
        int right[][] = new int[r][c];
        long ans = 0;

        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                arr[i][j] = sc.nextInt();
            
                if(arr[i][j] == 1){
                    up[i][j] = 1 + ((i > 0)?up[i - 1][j]:0);
                    left[i][j] = 1 + ((j > 0)?left[i][j - 1]:0);
                }
            }
        }

        for(int i = r - 1; i >= 0; i--){
            for(int j = c - 1; j >= 0; j--){
                if(arr[i][j] == 1){
                    down[i][j] = 1 + ((i + 1 < r)?down[i + 1][j]:0);
                    right[i][j] = 1 + ((j + 1 < c)?right[i][j + 1]:0);

                    ans += cal(up[i][j], left[i][j]);
                    ans += cal(up[i][j], right[i][j]);
                    ans += cal(down[i][j], left[i][j]);
                    ans += cal(down[i][j], right[i][j]);
                }
            }
        }

        pw.println(ans);
    }
}