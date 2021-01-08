// Stable Wall

import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y20rCp2 {
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

    private static void solve()throws IOException{
        int r = sc.nextInt(), c = sc.nextInt();

        char grid[][] = new char[r][c];
        for(int i=0; i<r; i++)
            grid[i] = sc.readString().toCharArray();

        ArrayList<HashSet<Integer>> out = new ArrayList<>();
        HashSet<Character> set = new HashSet<>();
        int in[] = new int[26];

        for(int i=0; i<26; i++)
            out.add(new HashSet<>());

        for(int j=0; j<c; j++){
            for(int i=0; i<r-1; i++){
                set.add(grid[i][j]);
                if(grid[i][j] != grid[i+1][j]){
                    int ch1 = grid[i][j] - 'A', ch2 = grid[i+1][j] - 'A';
                    if(!out.get(ch2).contains(ch1)){
                        out.get(ch2).add(ch1);
                        in[ch1]++;
                    }
                }
            }
            set.add(grid[r-1][j]);
        }

        int cnt = 0;
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<26; i++){
            if(in[i] == 0 && set.contains((char)(i+'A'))){
                cnt++;
                q.add(i);
            }
        }

        StringBuilder builder = new StringBuilder("");
        while(!q.isEmpty()){
            int el = q.poll();
            builder.append((char)(el+'A'));

            for(Integer child : out.get(el)){
                in[child]--;
                if(in[child] == 0){
                    cnt++;
                    q.add(child);
                }
            }
        }

        if(cnt < set.size())    pw.println(-1);
        else    pw.println(builder.toString());
    }
}