// Kickstart Alarm

import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y18rCp3 {
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

    private static long inverse[];

    public static void main(String args[])throws IOException{

        inverse = new long[(int)1e6 + 10];

        for(int i = 1; i < inverse.length; i++)
            inverse[i] = pow(i, MOD - 2, MOD);

        int t = sc.nextInt();
        for(int i = 1; i <= t; i++){
            pw.print("Case #"+i+": ");
            solve();
        }

        pw.flush();
        pw.close();
    }

    private static long MOD = (long)1e9 + 7;

    private static long pow(long a, long b, long MOD){
        if(b == 0)
            return 1;
        
        if(b == 1)
            return a;

        long half = pow(a, b/2, MOD), ans = mul(half, half, MOD);

        if(b%2 == 1)
            ans = mul(ans, a, MOD);

        return ans;
    }

    private static long gpSum(int a, int K, long MOD){
        long ans = mul(a, inverse[a - 1], MOD);

        long temp = sub(pow(a, K, MOD), 1, MOD);
        
        ans = mul(ans, temp, MOD);

        return ans;
    }

    private static long add(long a, long b, long MOD){
        return (a + b)%MOD;
    }

    private static long mul(long a, long b, long MOD){
        return (a%MOD * b%MOD)%MOD;
    }

    private static long sub(long a, long b, long MOD){
        return add(a, (MOD - b%MOD)%MOD, MOD);
    }

    private static void solve()throws IOException{
        final int N = sc.nextInt(), K = sc.nextInt();

        long x[] = new long[N], y[] = new long[N], A[] = new long[N], ans = 0;

        x[0] = sc.nextLong();
        y[0] = sc.nextLong();

        final long C = sc.nextLong(), D = sc.nextLong(), E1 = sc.nextLong(), E2 = sc.nextLong(), F = sc.nextLong();

        A[0] = add(x[0], y[0], F);

        ans = mul(mul(A[0], K, MOD), N, MOD);

        long powerKSum = K;

        for(int i = 1; i < N; i++){

            x[i] = add(add(mul(C, x[i - 1], F), mul(D, y[i - 1], F), F), E1, F);
            y[i] = add(add(mul(D, x[i - 1], F), mul(C, y[i - 1], F), F), E2, F);

            A[i] = add(x[i], y[i], F);

            powerKSum = add(powerKSum, gpSum(i + 1, K, MOD), MOD);
            
            long temp = mul(A[i], mul(powerKSum, N - i, MOD), MOD);

            ans = add(ans, temp, MOD);
        }

        pw.println(ans);
    }
}