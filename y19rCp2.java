// Circuit Board

import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y19rCp2 {
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

    public static void main(String args[])throws IOException{
        int t = sc.nextInt();
        for(int i=1; i<=t; i++){
            pw.print("Case #"+i+": ");
            solve();
        }

        pw.flush();
        pw.close();
    }
    
    private static int calculateLargestRectangle(int height[]){
        int n = height.length, left[] = new int[n], right[] = new int[n], ans = 0;
        
        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0; i < n; i++){
            while(!stack.isEmpty() && height[stack.peek()] >= height[i])
                stack.pop();
                
            if(stack.isEmpty())
                left[i] = 0;
            else
                left[i] = stack.peek() + 1;
                
            stack.push(i);
        }
        
        stack.clear();
        
        for(int i = n - 1; i >= 0; i--){
            while(!stack.isEmpty() && height[stack.peek()] >= height[i])
                stack.pop();
                
            if(stack.isEmpty())
                right[i] = n - 1;
            else
                right[i] = stack.peek() - 1;
                
            stack.push(i);
        }
        
        for(int i = 0; i < n; i++)
            ans = Math.max(ans, (right[i] - left[i] + 1)*height[i]);
        
        return ans;
    }

    private static void solve()throws IOException{
        final int row = sc.nextInt(), col = sc.nextInt(), k = sc.nextInt();
        
        int v[][] = new int[row][col];
        int runLength[][] = new int[row][col];
        
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++)
                v[i][j] = sc.nextInt();
        }
        
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                int min = v[i][j], max = v[i][j], ptr = j + 1;
                
                for(ptr = j + 1; ptr < col; ptr++){
                    if(Math.abs(v[i][ptr] - min) <= k && Math.abs(v[i][ptr] - max) <= k){
                        min = Math.min(min, v[i][ptr]);
                        max = Math.max(max, v[i][ptr]);
                    }
                    else
                        break;
                }
                
                runLength[i][j] = ptr - j;
            }
        }
        
        int ans = 0;
        
        for(int j = 0; j < col; j++){
            int height[] = new int[row];
            
            for(int i = 0; i < row; i++)
                height[i] = runLength[i][j];
                
            ans = Math.max(ans, calculateLargestRectangle(height));
        }
        
        pw.println(ans);
    }
}