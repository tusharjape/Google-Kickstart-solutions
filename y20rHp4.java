// Friends

import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y20rHp4 {
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

    private static boolean isSet(int mask, int bit){
        return (mask & (1 << bit)) > 0;
    }

    private static void solve()throws IOException{
        int n = sc.nextInt(), q = sc.nextInt();

        int mask[] = new int[n];

        for(int i = 0; i < n; i++){
            char name[] = sc.readString().toCharArray();

            for(char ch: name)
                mask[i] |= (1 << (ch - 'A'));
        }

        int distance[][] = new int[26][26];

        for(int i = 0; i < 26; i++)
            Arrays.fill(distance[i], Integer.MAX_VALUE);

        for(int i = 0; i < n; i++){

            for(int letter1 = 0; letter1 < 26; letter1++){

                if(isSet(mask[i], letter1)){
                    distance[letter1][letter1] = 0;

                    for(int letter2 = letter1 + 1; letter2 < 26; letter2++){

                        if(isSet(mask[i], letter2)){
                            distance[letter1][letter2] = 1;
                            distance[letter2][letter1] = 1;
                        }
                    }
                }
            }
        }

        for(int k = 0; k < 26; k++){
            for(int i = 0; i < 26; i++){
                for(int j = 0; j < 26; j++){
                    if(distance[i][k] != Integer.MAX_VALUE && distance[k][j] != Integer.MAX_VALUE)
                        distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }

        while(q-->0){
            int u = sc.nextInt() - 1, v = sc.nextInt() - 1;

            int distanceUV = Integer.MAX_VALUE;

            for(int letteru = 0; letteru < 26; letteru++){

                if(isSet(mask[u], letteru)){

                    for(int letterv = 0; letterv < 26; letterv++){

                        if(isSet(mask[v], letterv)){
                            distanceUV = Math.min(distance[letteru][letterv], distanceUV);
                        }
                    }
                }
            }

            if(distanceUV == Integer.MAX_VALUE) pw.print("-1 ");
            else    pw.print(distanceUV + 2 + " ");
        }

        pw.println();
    }
}