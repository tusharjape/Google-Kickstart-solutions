// Banana Bunches

import java.io.*;
import java.util.*;

public class y21rGp3{

    private static int aux[];
    private static final int maxSum = (int)1e6 + 10;
    
    private static int solve(int arr[], int k){
        int n = arr.length, ans = n + 1;
        
        for(int i = 0; i < maxSum; i++)
            aux[i] = Integer.MAX_VALUE;
            
        for(int i = n - 1; i >= 0; i--){
            int sum = 0;
            
            for(int j = i; j >= 0; j--){
                sum += arr[j];
                
                if(sum >= maxSum || sum > k)    break;
                
                int req = k - sum;
                if(aux[req] != Integer.MAX_VALUE)
                    ans = Math.min(ans, i - j + 1 + aux[req]);
            }
            
            sum = 0;
            
            for(int j = i; j < n; j++){
                sum += arr[j];
                
                if(sum >= maxSum || sum > k)    break;
                
                aux[sum] = Math.min(aux[sum], j - i + 1);
                
                if(sum == k)
                    ans = Math.min(ans, j - i + 1);
            }
        }
        
        if(ans == n + 1)
            return -1;
        return ans;
    }

    public static void main(String []args){
    
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        aux = new int[maxSum];
        
        for(int r = 1; r <= t; r++){
            int n = sc.nextInt();
            int k = sc.nextInt(), arr[] = new int[n];
            
            for(int i = 0; i < n; i++)
                arr[i] = sc.nextInt();
            
            int ans = solve(arr, k);    
            
            System.out.printf("Case #%d: %d%n", r, ans);
        }

    }
}
