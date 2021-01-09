import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y20rCp3 {
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
        int n = sc.nextInt(), arr[] = sc.intArray(n);

        HashSet<Integer> list = new HashSet<>();
        int no = 0;

        while(no*no < (int)1e7){
            list.add(no*no);
            no++;
        }

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, sum = 0;
        for(int i=0; i<n; i++){
            sum += arr[i];
            min = Math.min(min, sum);
            max = Math.max(max, sum);
        }

        int freq[] = new int[max-min+1];
        sum = 0;

        long cnt = 0;

        for(int i=0; i<n; i++){
            sum += arr[i];

            if(list.contains(sum))
                cnt += 1;

            for(Integer sq : list){
                if(sum - sq - min >= 0)
                    cnt += freq[sum - sq - min];
            }
            freq[sum - min]++;
        }

        pw.println(+cnt);
    }
}