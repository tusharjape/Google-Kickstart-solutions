// Candies

import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y18rDp1 {
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
        map = new TreeMap<>();

        int t = sc.nextInt();
        for(int i=1; i<=t; i++){
            pw.print("Case #"+i+": ");
            solve();
        }

        pw.flush();
        pw.close();
    }

    private static TreeMap<Long, Integer> map;

    private static void increment(long value){
        int prev = map.getOrDefault(value, 0);

        map.put(value, prev + 1);
    }

    private static void decrement(long value){
        int prev = map.get(value);

        if(prev == 1)
            map.remove(value);
        else
            map.put(value, prev - 1);
    }

    private static void solve()throws IOException{
        map.clear();

        final int N = sc.nextInt();
        int O = sc.nextInt();
        final long D = sc.nextLong();
        long ans = Long.MIN_VALUE;

        long x[] = new long[N], s[] = new long[N], pre[] = new long[N];
        x[0] = sc.nextLong();
        x[1] = sc.nextLong();

        final long A = sc.nextLong(), B = sc.nextLong(), C = sc.nextLong(), M = sc.nextLong(), L = sc.nextLong();

        s[0] = x[0] + L;
        pre[0] = s[0];
        s[1] = x[1] + L;
        pre[1] = pre[0] + s[1];

        for(int i = 2; i < N; i++){
            x[i] = (A * x[i - 1] + B * x[i - 2] + C)%M;
            s[i] = x[i] + L;
            pre[i] = pre[i - 1] + s[i];
        }

        boolean flag = false;

        for(int start = 0, end = -1; start < N; start++){
            if(end < start - 1){
                end = start - 1;
                map.clear();
            }

            while(end + 1 < N && (s[end + 1] % 2 == 0 || O > 0)){
                end++;

                increment(pre[end]);

                if(s[end]%2 != 0)
                    O--;
            }

            if(end >= start){
                long prev = (start > 0)?pre[start - 1]:0;

                if(map.floorKey(prev + D) != null){
                    flag = true;
                    ans = Math.max(ans, map.floorKey(prev + D) - prev);
                }
                decrement(pre[start]);

                if(s[start]%2 != 0)
                    O++;
            }
        }

        if(flag)
            pw.println(ans);
        else
            pw.println("IMPOSSIBLE");
    }
}