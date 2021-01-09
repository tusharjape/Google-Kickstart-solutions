// Painter's Duel

import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y20rFp3 {
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
    }

    private static HashMap<Integer, int[]> idToPos;
    private static int[][] posToId;

    private static long longbit(int bit){
        return ((long)1) << bit;
    }

    private static boolean isSet(long mask, int bit){
        return (mask & longbit(bit)) > 0;
    }

    private static ArrayList<Integer> neighbors(long mask, int id){
        int pos[] = idToPos.get(id), row = pos[0], col = pos[1];

        ArrayList<Integer> neighbors = new ArrayList<>();

        if(id != 0 && id != 1 && id != 4 && id != 9 && id != 16 && id != 25 && isSet(mask, id - 1))
            neighbors.add(id - 1);
        
        if(id != 0 && id != 3 && id != 8 && id != 15 && id != 24 && id != 35 && isSet(mask, id + 1))
            neighbors.add(id + 1);

        if((row + col) % 2 == 0){
            if(isSet(mask, posToId[row - 1][col]))
                neighbors.add(posToId[row - 1][col]);
        }
        else if(row != S - 1){
            if(isSet(mask, posToId[row + 1][col]))
                neighbors.add(posToId[row + 1][col]);
        }

        return neighbors;
    }

    public static void main(String args[])throws IOException{
        cache = new HashMap<>();
        idToPos = new HashMap<>();
        posToId = new int[6][11];

        for(int row = 0, startCol = 5, endCol = 5, no = 0; row < 6; row++, startCol--, endCol++){
            for(int col = startCol; col <= endCol; col++, no++){
                posToId[row][col] = no;
                idToPos.put(no, new int[]{row, col});
            }
        }

        int t = sc.nextInt();
        for(int i=1; i<=t; i++){
            pw.print("Case #"+i+": ");
            solve();
        }

        pw.flush();
        pw.close();
    }

    private static int S;
    private static HashMap<Integer, Integer> cache;

    private static int score(long mask, int a, int b, int turn){
        long rep[] = {mask, a, b, turn};
        int repCode = Arrays.hashCode(rep);

        if(cache.containsKey(repCode))
            return cache.get(repCode);

        int score = (turn == 1)?Integer.MIN_VALUE:Integer.MAX_VALUE;

        ArrayList<Integer> neighbors = (turn == 1)?neighbors(mask, a):neighbors(mask, b);

        if(neighbors.isEmpty()){
            turn = 1 - turn;
            neighbors = (turn == 1)?neighbors(mask, a):neighbors(mask, b);
            score = (turn == 1)?Integer.MIN_VALUE:Integer.MAX_VALUE;
        }

        if(neighbors.isEmpty())
            score = 0;
        else{
            for(Integer neighbor: neighbors){
                int pss = 0;

                if(turn == 1){
                    pss = score(mask^longbit(neighbor), neighbor, b, 0) + 1;
                    score = Math.max(score, pss);
                }
                else{
                    pss = score(mask^longbit(neighbor), a, neighbor, 1) - 1;
                    score = Math.min(score, pss);
                }
            }
        }

        cache.put(repCode, score);
        
        return score;
    }

    private static void solve()throws IOException{
        cache.clear();

        S = sc.nextInt();
        
        int ra = sc.nextInt() - 1, ca = sc.nextInt() - 1, rb = sc.nextInt() - 1, cb = sc.nextInt() - 1, c = sc.nextInt();

        ca += 5 - ra;
        cb += 5 - rb;

        long mask = longbit(S*S) - 1;

        mask ^= longbit(posToId[ra][ca]);
        mask ^= longbit(posToId[rb][cb]);
        
        while(c-- > 0){
            int i = sc.nextInt() - 1, j = sc.nextInt() - 1;
            j += 5 - i;
            mask ^= longbit(posToId[i][j]);
        }

        pw.println(score(mask, posToId[ra][ca], posToId[rb][cb], 1));
    }
}