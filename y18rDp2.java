// Paragliding

import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y18rDp2 {
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

    private static long add(long a, long b, long mod){
        return (a + b)%mod;
    }

    private static long mul(long a, long b, long mod){
        return (a * b)%mod;
    }

    static class CustomComparator implements Comparator<long[]>{
        public int compare(long a[], long b[]){
            if(a[0] != b[0])
                return Long.compare(a[0], b[0]);

            return Long.compare(a[1], b[1]);
        }
    }

    private static void solve()throws IOException{
        final int N = sc.nextInt(), K = sc.nextInt();

        long tower[][] = new long[N][2];
        long balloon[][] = new long[K][2];
        long A, B, C, M;

        for(int t = 0; t < 2; t++){
            tower[0][t] = sc.nextLong();
            tower[1][t] = sc.nextLong();
            A = sc.nextLong();
            B = sc.nextLong();
            C = sc.nextLong();
            M = sc.nextLong();
            for(int i = 2; i < N; i++)
                tower[i][t] = add(add(mul(A, tower[i - 1][t], M), mul(B, tower[i - 2][t], M), M), C, M) + 1;
        }

        for(int t = 0; t < 2; t++){
            balloon[0][t] = sc.nextLong();
            balloon[1][t] = sc.nextLong();
            A = sc.nextLong();
            B = sc.nextLong();
            C = sc.nextLong();
            M = sc.nextLong();
            for(int i = 2; i < K; i++)
                balloon[i][t] = add(add(mul(A, balloon[i - 1][t], M), mul(B, balloon[i - 2][t], M), M), C, M) + 1;
        }

        Arrays.sort(tower, new CustomComparator());
        Arrays.sort(balloon, new CustomComparator());  
        
        int possible[] = new int[K], ptr = N;
        long value = Long.MIN_VALUE;

        for(int i = K - 1; i >= 0; i--){
            while(ptr > 0 && tower[ptr - 1][0] >= balloon[i][0]){
                ptr--;
                value = Math.max(value, tower[ptr][1] - tower[ptr][0]);
            }

            if(balloon[i][1] - balloon[i][0] <= value)
                possible[i] = 1;
        }

        ptr = -1;
        value = Long.MIN_VALUE;

        for(int i = 0; i < K; i++){
            while(ptr + 1 < N && tower[ptr + 1][0] <= balloon[i][0]){
                ptr++;
                value = Math.max(value, tower[ptr][0] + tower[ptr][1]);
            }

            if(balloon[i][0] + balloon[i][1] <= value)
                possible[i] = 1;
        }

        int count = 0;
        for(int check: possible){
            if(check == 1)
                count++;
        }

        pw.println(count);
    }
}