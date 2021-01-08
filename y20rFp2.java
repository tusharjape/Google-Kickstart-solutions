// Metal harvest

import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y20rFp2 {
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

    private static void solve()throws IOException{
        int n = sc.nextInt();
        long k = sc.nextLong();

        PriorityQueue<long[]> priorityQueue = new PriorityQueue<>((i, j)->Long.compare(i[0], j[0]));

        for(int i = 0; i < n; i++){
            long interval[] = sc.longArray(2);
            priorityQueue.add(interval);
        }

        int countOfRobotsNeeded = 0;
        long robotDischargeTime = -1;
        
        for(int i = 0; i < n; i++){

            long interval[] = priorityQueue.poll();

            if(interval[1] > robotDischargeTime){
                long nextRobotBeginTime = Math.max(robotDischargeTime, interval[0]);
                long currNeed = (long)Math.ceil((double)(interval[1] - nextRobotBeginTime)/k);
                countOfRobotsNeeded += currNeed;
                robotDischargeTime = currNeed*k + nextRobotBeginTime;
            }
        }

        pw.println(countOfRobotsNeeded);
    }
}