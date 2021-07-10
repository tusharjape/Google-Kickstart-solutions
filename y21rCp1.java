// Smaller strings

import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y21rCp1 {
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

        private String readString()throws IOException{
            next();
            return tk.nextToken();
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

    static final long mod = (long)1e9 + 7;

    private static long add(long a, long b){
        return (a + b)%mod;
    }

    private static long mul(long a, long b){
        return (a * b)%mod;
    }

    private static void solve()throws IOException{
        final int n = sc.nextInt(), k = sc.nextInt();
        final char str[] = sc.readString().toCharArray();

        long dp[] = new long[n + 2], ans = 0;

        dp[0] = 1;
        dp[1] = k;
        dp[2] = k;

        for(int i = 3; i < n; i++)
            dp[i] = mul(dp[i - 2], k);

        for(int left = 0, right = n - 1, rem = n - 2; left <= right; left++, right--, rem -= 2){
            rem = Math.max(rem, 0);
            
            int val = str[left] - 'a';

            ans = add(ans, mul(val, dp[rem]));
        }
        
        char temp[] = new char[str.length];
        
        for(int left = 0, right = n - 1; left <= right; left++, right--){
            temp[left] = str[left];
            temp[right] = str[left];
        }
        
        for(int i = 0; i < str.length; i++){
            if(temp[i] > str[i])
                break;
            else if(temp[i] < str[i]){
                ans = add(ans, 1);
                break;
            }
        }

        pw.println(ans);
    }
}