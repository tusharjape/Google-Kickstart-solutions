import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y18rAp2 {
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

        private long nextLong()throws IOException{
            next();
            return Long.parseLong(tk.nextToken());
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

    private static int bs(long v[], double limit){
        int low = 0, high = v.length - 1;

        while(low <= high){
            int mid = (low + high)/2;

            if(v[mid] > limit)
                high = mid - 1;
            else
                low = mid + 1;
        }

        return low;
    }

    private static void solve()throws IOException{
        int n = sc.nextInt(), k = sc.nextInt() + 1;

        long v[] = sc.longArray(n);
        
        Arrays.sort(v);

        long sum[] = new long[n];
        sum[n - 1] = v[n - 1];
        for(int i = n - 2; i >= 0; i--)
            sum[i] = sum[i + 1] + v[i];

        double prev = 0;

        while(k-->0){
            int pos = bs(v, prev);

            double avg = pos * prev;

            if(pos < n)
                avg += sum[pos];

            avg /= n;

            prev = avg;
        }

        pw.println(prev);
    }
}