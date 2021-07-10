// Increasing Substring

import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y21rBp1 {
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
        int n = sc.nextInt(), ans[] = new int[n];

        char s[] = sc.readString().toCharArray();

        ans[0] = 1;

        for(int i = 1; i < n; i++){
            if(s[i] > s[i - 1])
                ans[i] = ans[i - 1] + 1;
            else
                ans[i] = 1;
        }

        for(Integer len: ans)
            pw.print(len+ " ");

        pw.println();
    }
}