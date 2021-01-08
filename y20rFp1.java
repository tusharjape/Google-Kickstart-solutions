// ATM Queue

import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y20rFp1 {
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

        private int[] intArray(int n)throws IOException{
            next();
            int arr[] = new int[n];

            for(int i=0; i<n; i++)
                arr[i] = nextInt();

            return arr;
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
        int n = sc.nextInt(), x = sc.nextInt(), arr[] = sc.intArray(n);

        TreeMap<Integer, List<Integer>> map = new TreeMap<>();

        for(int index = 1; index <= n; index++){
            int requiredRound = (int)Math.ceil((double)arr[index - 1]/x);

            map.putIfAbsent(requiredRound, new ArrayList<>());

            map.get(requiredRound).add(index);
        }

        for(Map.Entry<Integer, List<Integer>> entry: map.entrySet()){
            for(Integer index: entry.getValue())
                pw.print(+index+" ");
        }

        pw.println();
    }
}