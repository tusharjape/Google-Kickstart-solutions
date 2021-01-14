// No Nine

import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y18rBp1 {
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

    private static long data[][];

    public static void main(String args[])throws IOException{
        data = new long[19][9];

        data[0][0] = 1;

        for(int length = 1; length <= 18; length++){
            for(int oldrem = 0; oldrem <= 8; oldrem++){
                for(int currDigit = 0; currDigit <= 8; currDigit++){
                    int newrem = (oldrem + currDigit)%9;

                    data[length][newrem] += data[length - 1][oldrem];
                }
            }
        }

        int t = sc.nextInt();
        for(int i = 1; i <= t; i++){
            pw.print("Case #"+i+": ");
            solve();
        }

        pw.flush();
        pw.close();
    }

    private static int[] longToArr(long no){
        int arr[] = new int[Long.toString(no).length()];

        for(int ptr = arr.length - 1; ptr >= 0; ptr--, no /= 10)
            arr[ptr] = (int)(no%10);

        return arr;
    }

    private static long count(long no){
        long ans = 0;

        int arr[] = longToArr(no);

        for(int index = 0, sum = 0; index < arr.length; sum = (sum + arr[index++])%9){
            for(int digit = 0; digit < arr[index]; digit++){
                for(int remsum = 0; remsum <= 8; remsum++){
                    if((sum + digit + remsum)%9 != 0)
                        ans += data[arr.length - index - 1][remsum];
                }
            }
        }

        return ans;
    }

    private static void solve()throws IOException{
        long f = sc.nextLong(), l = sc.nextLong();

        pw.println(count(l) - count(f) + 1);
    }
}