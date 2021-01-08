// Record Breaker

import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y20rDp1 {
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
        int n = sc.nextInt(), a[] = sc.intArray(n);
            
        int max = Integer.MIN_VALUE, count = 0;
        for(int j=0; j<n; j++){
            if(a[j] > max){
                max = a[j];
                
                if(j == n-1 || a[j] > a[j+1])
                    count++;
            }
        }

        pw.println(+count);
    }
}