import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y20rAp3 {
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

    private static int n, k;
    private static long arr[];

    private static boolean check(long dist){
        long cnt = 0;

        for(int i=1; i<n; i++)
            cnt += (arr[i] - arr[i-1] - 1)/dist;

        //pw.println(+dist+" "+cnt);

        return cnt <= k;
    }

    private static long bs(){
        long low = 1, high = Long.MAX_VALUE/2;

        while(low <= high){
            long mid = low + (high-low)/2;

            if(check(mid))
                high = mid - 1;
            else
                low = mid + 1;
        }

        return low;
    }

    private static void solve()throws IOException{
        n = sc.nextInt();
        k = sc.nextInt();
        arr = sc.longArray(n);
        pw.println(bs());
    }
}