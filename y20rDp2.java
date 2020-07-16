import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y20rDp2 {
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

    private static void solve()throws IOException{
        int n = sc.nextInt(), a[] = new int[n];

        for(int i=0; i<n; i++)  a[i] = sc.nextInt();

        int incCnt[] = new int[n], decCnt[] = new int[n], cnt = 0;

        for(int i=n-2; i>=0; i--){
            if(a[i] == a[i+1]){
                incCnt[i] = incCnt[i+1];
                decCnt[i] = decCnt[i+1];
            }
            else if(a[i] > a[i+1]){
                if(decCnt[i+1] == 0)
                    cnt += incCnt[i+1]/4;
                decCnt[i]  = decCnt[i+1] + 1;
            }
            else{
                if(incCnt[i+1] == 0)
                    cnt += decCnt[i+1]/4;
                incCnt[i] = incCnt[i+1] + 1;
            }
        }

        cnt += incCnt[0]/4 + decCnt[0]/4;

        pw.println(+cnt);
    }
}