// Alien Generator

import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y21rCp2 {
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
        for(int i = 1; i <= t; i++){
            pw.print("Case #"+i+": ");
            solve();
        }

        pw.flush();
        pw.close();
    }

    private static void solve()throws IOException{
        long req = sc.nextLong(), val = 0;
        int ans = 0;
        
        for(long i = 1; val < req; i++){
            if(val > req)
                break;
            else if((req - val)%i == 0)
                ans++;
            
            val += i;
        }
        
        pw.println(ans);
    }
}