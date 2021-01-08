// Milk tea

import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y18rEp2 {
    private static PrintWriter pw = new PrintWriter(System.out);
    private static InputReader sc = new InputReader();

    static class Pair implements Comparable<Pair>{
        String str;
        int cost;
        Pair(String first, int second){
            this.str = first;
            this.cost = second;
        }
        public int compareTo(Pair o){
            return cost - o.cost;
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

    static class Node{
        boolean end;
        Node child[];

        Node(){
            end = false;
            child = new Node[2];
        }

        void insert(String word, int index){
            if(index == word.length()){
                end = true;
                return;
            }

            int ch = word.charAt(index) - '0';

            if(child[ch] == null)
                child[ch] = new Node();

            child[ch].insert(word, index+1);
        }

        boolean find(String word, int index){
            if(index == word.length())
                return end;

            int ch = word.charAt(index) - '0';

            if(child[ch] == null)   return false;

            return child[ch].find(word, index+1);
        }
    }

    private static void solve()throws IOException{
        int n = sc.nextInt(), m = sc.nextInt(), p = sc.nextInt();

        Node root = new Node();

        int price[] = new int[p];
        for(int i=0; i<n; i++){
            String word = sc.readString();

            for(int j=0; j<p; j++)
                if(word.charAt(j) == '1')   price[j]++;
        }

        ArrayList<Pair> list = new ArrayList<>();
        
        for(int i=0; i<m; i++){
            String word = sc.readString();
            root.insert(word, 0);
        }

        Pair tmp = new Pair("0", price[0]);
        list.add(tmp);

        tmp = new Pair("1", n-price[0]);
        list.add(tmp);

        Collections.sort(list);

        for(int i=1; i<p; i++){
            ArrayList<Pair> nxt = new ArrayList<>();

            for(Pair k : list){
                String str = k.str;
                int cost = k.cost;

                nxt.add(new Pair(str+"0", cost+price[i]));
                nxt.add(new Pair(str+"1", cost+n-price[i]));
            }

            Collections.sort(nxt);
            
            list.clear();
            
            for(int k=0; k<Math.min(nxt.size(), m+1); k++)
                list.add(nxt.get(k));
            
            nxt.clear();
        }

        for(Pair k : list){
            String str = k.str;
            int cost = k.cost;

            if(!root.find(str, 0)){
                pw.println(cost);
                break;
            }
        }
    }
}