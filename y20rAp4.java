import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y20rAp4 {
    private static PrintWriter pw = new PrintWriter(System.out);
    private static InputReader sc = new InputReader();

    static class Pair<T1, T2> {
        T1 first;
        T2 second;
        Pair(T1 first, T2 second){
            this.first = first;
            this.second = second;
        }
    }

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

        private String readString()throws IOException{
            next();
            return tk.nextToken();
        }

        private double nextDouble()throws IOException{
            next();
            return Double.parseDouble(tk.nextToken());
        }

        private int[] intArray(int n)throws IOException{
            next();
            int arr[] = new int[n];

            for(int i=0; i<n; i++)
                arr[i] = nextInt();

            return arr;
        }

        private long[] longArray(int n)throws IOException{
            next();
            long arr[] = new long[n];

            for(int i=0; i<n; i++)
                arr[i] = nextLong();

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

    private static int sum, k;
    private static HashMap<Integer, String> map;

    private static ArrayList<Integer> input()throws IOException{
        int n = sc.nextInt();
        k = sc.nextInt();

        map = new HashMap<>();
        ArrayList<Integer> ls = new ArrayList<>();
        for(int i=0; i<n; i++){
            map.put(i, sc.readString());
            ls.add(i);
        }

        return ls;
    }

    private static void cal(ArrayList<Integer> ls, int ind){
        if(ls.size() < k)
            return;

        ArrayList<ArrayList<Integer>> chls = new ArrayList<>();

        for(int i=0; i<26; i++)
            chls.add(new ArrayList<>());

        for(Integer i : ls){
            if(map.get(i).length() > ind)
                chls.get(map.get(i).charAt(ind)-'A').add(i);
        }

        for(int i=0; i<26; i++){
            sum += chls.get(i).size()/k;
            cal(chls.get(i), ind+1);
        }
    }

    private static void solve()throws IOException{
        ArrayList<Integer> ls = input();
        sum = 0;
        cal(ls, 0);
        pw.println(+sum);
    }
}