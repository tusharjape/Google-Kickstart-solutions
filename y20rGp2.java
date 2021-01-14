// Maximum coins

import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y20rGp2 {
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

        private int[] intArray(int n)throws IOException{
            next();
            int arr[] = new int[n];

            for(int i=0; i<n; i++)
                arr[i] = nextInt();

            return arr;
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
        int n = sc.nextInt(), matrix[][] = new int[n][n];

        for(int i = 0; i < n; i++)
            matrix[i] = sc.intArray(n);

        long ans = 0;

        for(int startCol = 0; startCol < n; startCol++){
            long currSum = 0;

            for(int i = 0, j = startCol; i < n && j < n; j++, i++)
                currSum = currSum + matrix[i][j];

            ans = Math.max(ans, currSum);
        }

        for(int startRow = 1; startRow < n; startRow++){
            long currSum = 0;

            for(int i = startRow, j = 0; i < n && j < n; j++, i++)
                currSum = currSum + matrix[i][j];

            ans = Math.max(ans, currSum);
        }

        pw.println(ans);
    }
}