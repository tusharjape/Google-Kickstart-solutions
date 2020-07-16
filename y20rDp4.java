import java.io.*;
import java.util.*;

public class y20rDp4 {
    private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tk;
    private static PrintWriter pw = new PrintWriter(System.out);

    static class Pair<T1, T2>{
        T1 first;
        T2 second;
        Pair(T1 first, T2 second){
            this.first = first;
            this.second = second;
        }
    }

    static class CustomComparator implements Comparator<Pair<Integer, Integer>> {
        public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2){
            int p1f = p1.first.intValue(), p1s = p1.second.intValue();
            int p2f = p2.first.intValue(), p2s = p2.second.intValue();

            if(p1f != p2f)  return p2f - p1f;
            return p1s - p2s;
        }
    }

    private static void next()throws IOException{
        if(tk == null || !tk.hasMoreTokens())   
            tk = new StringTokenizer(r.readLine());
    }

    private static int nextInt()throws IOException{
        next();
        return Integer.parseInt(tk.nextToken());
    }

    public static void main(String args[])throws IOException{     
        int t = nextInt();
        for(int i=1; i<=t; i++){
            pw.print("Case #"+i+": ");
            solve();
        }
        
        pw.flush();
        pw.close();
    }

    static class Info{
        int startIndex, begin, end;

        Info(int startIndex, int begin, int end){
            this.startIndex = startIndex;
            this.begin = begin;
            this.end = end;
        }

        int get(int index){
            index = index - startIndex;
            return begin + ((end > begin) ? 1 : -1)*index;
        }
    }

    static class Node{
        int begin, end;
        Node left, right;
        ArrayList<Info> ls;

        Node(int b, int e){
            begin = b;
            end = e;
            ls = new ArrayList<>();
            if(begin != end){
                left = new Node(begin, (begin+end)/2);
                right = new Node((begin+end)/2+1, end);
            }
        }

        int last(){
            int size = ls.size();
            return ls.get(size-1).startIndex;
        }

        int query(int start, int index){
            if(ls.isEmpty() || last() > index){
                if(start <= (begin+end)/2)  return left.query(start, index);
                else    return right.query(start, index);
            }
            
            int low = 0, high = ls.size()-1;
            while(low <= high){
                int mid = (low + high)>>1;

                if(ls.get(mid).startIndex > index) low = mid + 1;
                else    high = mid - 1;
            }

            return ls.get(low).get(index);
        }
    
        void insertInfo(int l, int r, Info info){
            if(l > r || l > end || begin > r)
                return;

            if(l <= begin && end <= r){
                ls.add(info);
                return;
            }

            left.insertInfo(l, r, info);
            right.insertInfo(l, r, info);
        }
    }

    private static void solve()throws IOException{
        int n = nextInt()-1, q = nextInt();

        Node root = new Node(0, n);
        ArrayList<Pair<Integer, Integer>> weights = new ArrayList<>();
        TreeSet<Integer> set = new TreeSet<>();

        for(int i=0; i<n; i++)
            weights.add(new Pair<Integer, Integer>(nextInt(), i));

        Collections.sort(weights, new CustomComparator());

        for(Pair<Integer, Integer> pair : weights){
            int index = pair.second;

            int begin = 1 + (set.floor(index) == null ? -1 : set.floor(index)),
                end = set.ceiling(index) == null ? n : set.ceiling(index);

            root.insertInfo(begin, index, new Info(index - begin + 2, index + 1, end));
            root.insertInfo(index + 1, end, new Info(end - index + 1, index, begin));

            set.add(index);
        }

        while(q-->0){
            int s = nextInt()-1, k = nextInt();
            if(k == 1)  pw.print(+(s+1)+" ");
            else    pw.print(+(root.query(s, k)+1)+" ");
        }

        pw.println();
    }
}