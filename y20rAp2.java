import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y20rAp2 {
    private static PrintWriter pw = new PrintWriter(System.out);
    private static InputReader sc = new InputReader();

    static class Pair<T1, T2> {
        T1 first;
        T2 second;
        Pair(T1 first, T2 second){
            this.first = first;
            this.second = second;
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

        private String readString()throws IOException{
            next();
            return tk.nextToken();
        }

        private double nextDouble()throws IOException{
            next();
            return Double.parseDouble(tk.nextToken());
        }

        private int[] intArray(int n)throws IOException{
            next();
            int arr[] = new int[n];

            for(int i=0; i<n; i++)
                arr[i] = nextInt();

            return arr;
        }

        private long[] longArray(int n)throws IOException{
            next();
            long arr[] = new long[n];

            for(int i=0; i<n; i++)
                arr[i] = nextLong();

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

    private static int n, k, p, s[][];

    private static void solve()throws IOException{
        n = sc.nextInt();
        k = sc.nextInt();
        p = sc.nextInt();

        s = new int[n][k+1];

        for(int i=0; i<n; i++){
            s[i][0] = 0;

            for(int j=1; j<=k; j++)
                s[i][j] = s[i][j-1] + sc.nextInt();
        }
        gen();
    }

    private static void gen(){
        int dp[] = new int[p+1];

        for(int j=0; j<=Math.min(k, p); j++)
            dp[j] = s[0][j];

        for(int i=1; i<n; i++){
            int temp[] = new int[p+1];
            temp[0] = 0;

            for(int j=1; j<=p; j++){
                temp[j] = -1;

                for(int q=0; q<=Math.min(j, k); q++){
                    if(dp[j-q] != -1)
                        temp[j] = Math.max(temp[j], dp[j-q] + s[i][q]);
                }
            }
            dp = temp;
        }

        pw.println(dp[p]);
    }
}