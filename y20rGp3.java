// Combination Lock

import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y20rGp3 {
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

    private static long pre[];

    private static long range(int l, int r){
        long ans = pre[r];

        if(l > 0)   ans -= pre[l - 1];

        return ans;
    }

    private static void solve()throws IOException{
        final int w = sc.nextInt();
        final long n = sc.nextLong();

        long x[] = sc.longArray(w);
        Arrays.sort(x);

        pre = new long[w];

        pre[0] = x[0];
        for(int i = 1; i < w; i++)
            pre[i] = pre[i - 1] + x[i];

        long ans = Long.MAX_VALUE;

        for(int i = 0; i < w; i++){
            long curr = 0;

            int low = i + 1, high = w - 1;

            while(low <= high){
                int mid = (low + high)/2;

                long possibilityDirect = x[mid] - x[i];
                long possibilityRound = n - possibilityDirect;

                if(possibilityDirect >= possibilityRound)
                    high = mid - 1;
                else
                    low = mid + 1;
            }

            if(low > i + 1)
                curr = range(i + 1, low - 1) - x[i] * (low - 1 - i);

            if(low < w)
                curr = curr + n * (w - low) - range(low, w - 1) + x[i] * (w - low);

            low = 0;
            high = i - 1;

            while(low <= high){
                int mid = (low + high)/2;

                long possibilityDirect = x[i] - x[mid];
                long possibilityRound = n - possibilityDirect;

                if(possibilityDirect >= possibilityRound)
                    low = mid + 1;
                else
                    high = mid - 1;
            }

            if(high < i - 1)
                curr = curr + x[i] * (i - high - 1) - range(high + 1, i - 1);

            if(high >= 0)
                curr = curr + range(0, high) + (high + 1) * (n - x[i]);

            ans = Math.min(ans, curr);
        }

        pw.println(ans);
    }
}