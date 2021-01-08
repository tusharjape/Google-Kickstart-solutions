// Alien Piano

import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y20rDp2 {
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
        int n = sc.nextInt(), a[] = new int[n];

        for(int i=0; i<n; i++)  a[i] = sc.nextInt();

        int incCnt[] = new int[n], decCnt[] = new int[n], cnt = 0;

        for(int i=n-2; i>=0; i--){
            if(a[i] == a[i+1]){
                incCnt[i] = incCnt[i+1];
                decCnt[i] = decCnt[i+1];
            }
            else if(a[i] > a[i+1]){
                if(decCnt[i+1] == 0)
                    cnt += incCnt[i+1]/4;
                decCnt[i]  = decCnt[i+1] + 1;
            }
            else{
                if(incCnt[i+1] == 0)
                    cnt += decCnt[i+1]/4;
                incCnt[i] = incCnt[i+1] + 1;
            }
        }

        cnt += incCnt[0]/4 + decCnt[0]/4;

        pw.println(+cnt);
    }
}