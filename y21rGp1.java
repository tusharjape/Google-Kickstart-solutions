// Dogs and cats 

import java.io.*;
import java.util.*;

public class y21rGp1{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        
        int t = sc.nextInt();
        for(int r = 1; r <= t; r++){
            String yes = "YES", no = "NO", res = "";
            
            int n = sc.nextInt(), i;
            long d = sc.nextLong(), c = sc.nextLong(), m = sc.nextLong();
            
            sc.nextLine();
            char s[] = sc.nextLine().toCharArray();
    
            for(i = 0; i < n; i++){
                if(s[i] == 'D'){
                    if(d > 0){
                        d--;
                        c += m;
                    }
                    else
                        break;
                }
                else{
                    if(c > 0)   c--;
                    else
                        break;
                }
            }
            
            for(; i < n; i++){
                if(s[i] == 'D') res = no;
            }
            
            if(res == "")   res = yes;
            
            System.out.printf("Case #%d: %s%n", r, res);
        }
    }
}
