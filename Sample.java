// Question name

import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class Sample {
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

    private static void solve()throws IOException{

    }
}