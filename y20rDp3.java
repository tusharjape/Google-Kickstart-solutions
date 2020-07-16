import java.io.*;
import java.util.*;

public class y20rDp3{

    private static BufferedReader re;
    private static PrintWriter pw;

    private static int a, b, n, maxN = 5*(int)1e5+10;
    private static double ans;
    private static ArrayList<ArrayList<Integer>> childLs;
    private static int stack[], top, aChildCnt[], bChildCnt[];

    private static void init(){
        stack = new int[maxN];
        aChildCnt = new int[maxN];
        bChildCnt = new int[maxN];
        childLs = new ArrayList<>();
        for(int i=0; i<maxN; i++)
            childLs.add(new ArrayList<>());
    }

    private static void clear(){
        for(int i=0; i<maxN; i++){
            stack[i] = 0;
            aChildCnt[i] = 0;
            bChildCnt[i] = 0;

            childLs.get(i).clear();
        }
    }

    public static void main(String args[])throws IOException {
        try{
            re = new BufferedReader(new InputStreamReader(System.in));
            pw = new PrintWriter(System.out);

            init();

            int t = Integer.parseInt(re.readLine());
            for(int i=1; i<=t; i++)
            {
                pw.print("Case #"+i+": ");
                clear();
                solve();
            }

            pw.flush();
            pw.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void dfs(int node, int level){
        try{
            ans = ans + (double)n*(level/a + level/b + 2);
            stack[++top] = node;

            aChildCnt[node] = 1;
            bChildCnt[node] = 1;

            for(Integer child : childLs.get(node))
                dfs(child, level+1);

            if(top >= a){
                int athParent = stack[top-a];
                aChildCnt[athParent] += aChildCnt[node];
            }

            if(top >= b){
                int bthParent = stack[top-b];
                bChildCnt[bthParent] += bChildCnt[node];
            }

            ans = ans - ((double)aChildCnt[node] * bChildCnt[node]);

            top--;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void solve()throws IOException{
        try{
            StringTokenizer tk = new StringTokenizer(re.readLine());

            n = Integer.parseInt(tk.nextToken());
            a = Integer.parseInt(tk.nextToken());
            b = Integer.parseInt(tk.nextToken());
            ans = 0;

            tk = new StringTokenizer(re.readLine());
            for(int i=1; i<=n-1; i++){
                int parent = Integer.parseInt(tk.nextToken())-1;
                childLs.get(parent).add(i);
            }
                
            top = -1;
            dfs(0, 0);
            
            ans = ans / ((double)n * n);
            pw.println(+ans);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}