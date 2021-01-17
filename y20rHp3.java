// Rugby

import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y20rHp3 {
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

    private static long sum(long pre[], int l, int r){
        return pre[r] - ((l == 0)?0:pre[l - 1]);
    }

    private static long calXHelper(long x[], long pos){
        long ans = 0;

        for(long coordinate: x){
            ans += Math.abs(coordinate - pos);

            pos++;
        }

        return ans;
    }

    private static long calX(long x[]){
        long low = -(long)1e10, high = (long)1e10;

        while(low <= high){
            long mid = (low + high)/2;

            long val1 = calXHelper(x, mid), val2 = calXHelper(x, mid + 1);

            if(val1 == val2)
                return val1;
            else if(val1 < val2)
                high = mid - 1;
            else
                low = mid + 1;
        }

        return Math.min(calXHelper(x, low), calXHelper(x, high));
    }

    private static long calY(long y[]){
        long ans = Long.MAX_VALUE;

        int n = y.length;

        if(n == 1)
            return 0;

        long pre[] = new long[n];

        pre[0] = y[0];
        for(int i = 1; i < n; i++)
            pre[i] = pre[i - 1] + y[i];
        
        ans = Math.min(sum(pre, 1, n - 1) - y[0] * (n - 1), y[n - 1] * (n - 1) - sum(pre, 0, n - 2));

        for(int i = 1; i + 1< n; i++){
            long curr = (y[i] * i) - sum(pre, 0, i - 1) + sum(pre, i + 1, n - 1) - (y[i] * (n - i - 1));

            ans = Math.min(ans, curr);
        }

        return ans;
    }

    private static void solve()throws IOException{
        int n = sc.nextInt();

        long x[] = new long[n], y[] = new long[n];

        for(int i = 0; i < n; i++){
            x[i] = sc.nextLong();
            y[i] = sc.nextLong();
        }

        Arrays.sort(x);
        Arrays.sort(y);

        long ans = calX(x) + calY(y);

        pw.println(ans);
    }
}