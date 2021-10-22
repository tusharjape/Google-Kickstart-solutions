// Staying hydrated

import java.io.*;
import java.util.*;

public class y21rGp2{
    
    private static long midPoint(long segments[][]){
        long ans = Long.MAX_VALUE, pnt = 0;
        
        int prevCnt = 0, nxtCnt = segments.length;
        long prevSum = 0, nxtSum = 0;
        
        TreeMap<Long, int[]> map = new TreeMap<>();
        for(long segment[]: segments){
            long b = segment[0], e = segment[1];
            
            map.putIfAbsent(b, new int[2]);
            map.putIfAbsent(e, new int[2]);
            
            map.get(b)[0]++;
            map.get(e)[1]++;
            
            nxtSum += b;
        }
        
        for(Map.Entry<Long, int[]> entry: map.entrySet()){
            long p = entry.getKey();
            int cnt[] = entry.getValue();
            
            nxtCnt -= cnt[0];
            prevCnt += cnt[1];
            
            nxtSum -= p * cnt[0];
            prevSum += p * cnt[1];
            
            long curr = nxtSum - nxtCnt * p + prevCnt * p - prevSum;
            // ans = Math.min(ans, curr);
            if(ans > curr){
                ans = curr;
                pnt = p;
            }
        }
        
        return pnt;
    }
    
    private static long[] solve(long obj[][]){
        int n = obj.length;
        long seg1[][] = new long[n][2], seg2[][] = new long[n][2];
        
        for(int i = 0; i < n; i++){
            seg1[i][0] = obj[i][0];
            seg1[i][1] = obj[i][2];
            
            seg2[i][0] = obj[i][1];
            seg2[i][1] = obj[i][3];
        }
        
        return new long[]{midPoint(seg1), midPoint(seg2)};
    }
    
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        for(int r = 1; r <= t; r++){
            int n = sc.nextInt();
            long obj[][] = new long[n][4];
            
            for(int i = 0; i < n; i++){
                for(int j = 0; j < 4; j++)
                    obj[i][j] = sc.nextLong();
            }
            
            long ans[] = solve(obj);
            System.out.println("Case #"+r+": "+ans[0]+" "+ans[1]);
        }
    }
}
