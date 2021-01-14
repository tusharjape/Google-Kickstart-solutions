// Kick_Start

import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y20rGp1 {
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

    private static void solve()throws IOException{
        String input = sc.readString();

        int startCount = 0;
        long ans = 0;

        for(int i = input.length() - 5; i >= 0; i--){
            String substringStartCandidate = input.substring(i, i + 5);

            if(substringStartCandidate.equals("START"))
                startCount++;

            String substringKickCandidate = input.substring(i, i + 4);

            if(substringKickCandidate.equals("KICK"))
                ans += startCount;
        }

        pw.println(ans);
    }
}