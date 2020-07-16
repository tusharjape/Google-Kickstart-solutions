import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y20rCp4 {
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

        private char nextChar()throws IOException{
            return readString().charAt(0);
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

    static class Node{
        int begin, end;
        Node left, right;
        long sum[];

        Node(int b, int e){
            begin = b;
            end = e;
            sum = new long[2];

            if(begin != end){
                left = new Node(begin, (begin+end)/2);
                right = new Node((begin+end)/2+1, end);
            }
        }

        long query(int ch, int l, int r){
            if(l > r || l > end || begin > r)   return 0;

            if(l <= begin && end <= r)  return sum[ch];

            return left.query(ch, l, r) + right.query(ch, l, r);
        }

        void insert(int ch, int index, int val){
            if(index < begin || end < index)  return;

            if(begin == end){
                sum[ch] = val;
                return;
            }

            left.insert(ch, index, val);
            right.insert(ch, index, val);

            sum[0] = left.sum[0] + right.sum[0];
            sum[1] = left.sum[1] + right.sum[1];
        }
    }

    private static void solve()throws IOException{
        int n = sc.nextInt(), q = sc.nextInt();

        Node root = new Node(0, n-1);

        for(int i=0; i<n; i++){
            int input = sc.nextInt();

            root.insert(0, i, ((i % 2 == 0) ? 1 : -1)*input);
            root.insert(1, i, ((i % 2 == 0) ? 1 : -1)*input*(i+1));
        }

        // root.print();

        long s = 0;
        while(q-->0){
            char ch = sc.nextChar();

            switch(ch){
                case 'Q':
                    int begin = sc.nextInt()-1, end = sc.nextInt()-1;
                    long sum[] = {root.query(0, begin, end), root.query(1, begin, end)};
                    // pw.println("Sum: "+sum[0]+" "+sum[1]);
                    long ans = (begin % 2 == 0 ? 1 : -1) * (sum[1] - sum[0] * begin);
                    // pw.println("A: "+ans);
                    s += ans;
                break;
                case 'U':
                    int index = sc.nextInt() - 1, val = sc.nextInt();

                    root.insert(0, index, ((index % 2 == 0) ? 1 : -1)*val);
                    root.insert(1, index, ((index % 2 == 0) ? 1 : -1)*val*(index+1));
                break;
            }
        }

        pw.println(+s);
    }
}