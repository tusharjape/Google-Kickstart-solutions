// Even digits

import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y18rAp1 {
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

    private static int[] noToArr(String s){
        int[] arr = new int[s.length()];

        for(int i = 0; i < s.length(); i++)
            arr[i] = s.charAt(i) - '0';

        return arr;
    }

    private static long arrToNo(int arr[]){
        long res = 0;

        for(int z: arr)
            res = res * 10 + z;

        return res;
    }

    private static long smaller(int arr[]){
        int copy[] = Arrays.copyOf(arr, arr.length);

        for(int i = 0; i < arr.length; i++){
            if(copy[i]%2 == 1){
                copy[i]--;

                for(int j = i + 1; j < copy.length; j++)
                    copy[j] = 8;

                break;
            }
        }

        return arrToNo(copy);
    }

    private static long larger(int arr[]){
        int copy[] = new int[arr.length + 1];

        for(int i = 1; i < copy.length; i++)
            copy[i] = arr[i - 1];

        for(int i = 0; i < copy.length; i++){
            if(copy[i]%2 == 1){
                if(copy[i] == 9){
                    if(copy[i - 1] == 8){
                        copy[i - 1] = 9;

                        for(int j = i; j < copy.length; j++)
                            copy[j] = 0;

                        return larger(copy);
                    }
                    else{
                        copy[i - 1] += 2;

                        for(int j = i; j < copy.length; j++)
                            copy[j] = 0;
                    }
                }else{
                    copy[i]++;

                    for(int j = i + 1; j < copy.length; j++)
                        copy[j] = 0;
                }

                break;
            }
        }

        return arrToNo(copy);
    }

    private static void solve()throws IOException{
        String s = sc.readString();

        int arr[] = noToArr(s);
        long n = Long.parseLong(s);

        pw.println(Math.min(n - smaller(arr), larger(arr) - n));
    }
}