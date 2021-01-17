// Boring Numbers

import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y20rHp2 {
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
        pow5 = new long[20];
        sum5 = new long[20];

        pow5[0] = 1;
        sum5[0] = 0;

        for(int power = 1; power < 20; power++){
            pow5[power] = pow5[power - 1] * 5;
            sum5[power] = sum5[power - 1] + pow5[power];
        }

        int t = sc.nextInt();
        for(int i=1; i<=t; i++){
            pw.print("Case #"+i+": ");
            solve();
        }

        pw.flush();
        pw.close();
    }

    private static long pow5[], sum5[];

    private static long cal(int arr[]){
        long res = 0;

        if(arr.length > 1)
            res = sum5[arr.length - 1];

        int index;

        for(index = 0; index < arr.length; index++){
            int candidate = 1 - (index%2);

            for(; candidate < arr[index]; candidate += 2)
                res += pow5[arr.length - index - 1];

            if(candidate != arr[index])
                break;
        }

        if(index == arr.length)
            res++;

        return res;
    }

    private static int[] toArr(long no){
        String s = Long.toString(no);

        int arr[] = new int[s.length()];

        for(int i = 0; i < arr.length; i++)
            arr[i] = s.charAt(i) - '0';

        return arr;
    }

    private static void solve()throws IOException{
        long l = sc.nextLong(), r = sc.nextLong();

        pw.println(cal(toArr(r)) - cal(toArr(l - 1)));
    }
}