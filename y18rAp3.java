import java.io.*;
import java.util.*;

// Change class name to Solution before submitting
public class y18rAp3 {
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

        private String readString()throws IOException{
            next();
            return tk.nextToken();
        }

        private char readChar()throws IOException{
            next();

            return tk.nextToken().charAt(0);
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
    
    private static int countLength1Occurences(int s[], HashMap<Integer, HashMap<Integer, Integer>> codeMap){
        int count = 0, mask = 0;

        for(int ch: s)
            mask |= (1 << ch);

        int emptyArray[] = new int[26], emptyArrayCode = Arrays.hashCode(emptyArray);

        for(int letter = 0, key = 0; letter < 26; letter++, key += 27){
            if(codeMap.containsKey(key) && (mask & (1 << letter)) > 0){
                count += codeMap.get(key).get(emptyArrayCode);
                codeMap.remove(key);
            }
        }

        return count;
    }

    private static int countLength2Occurences(int s[], HashMap<Integer, HashMap<Integer, Integer>> codeMap){
        int count = 0;

        int emptyArray[] = new int[26], emptyArrayCode = Arrays.hashCode(emptyArray);

        for(int letter1 = 0; letter1 < 26; letter1++){
            for(int letter2 = 0; letter2 < 26; letter2++){

                int key = 27*letter1 + (letter2 + 1);

                if(codeMap.containsKey(key) && codeMap.get(key).containsKey(emptyArrayCode)){
                    count += codeMap.get(key).get(emptyArrayCode);

                    codeMap.get(key).remove(emptyArrayCode);
                }

            }
        }

        return count;
    }

    private static int countGreaterOccurences(int s[], HashMap<Integer, HashMap<Integer, Integer>> codeMap, int length){
        int count = 0;

        int freq[] = new int[26];

        for(int ptr = 1; ptr < length - 2; ptr++)
            freq[s[ptr]]++;

        for(int start = 0, end = length - 1; end < s.length; start++, end++){
            freq[s[end - 1]]++;

            int key = 27 * s[start] + (s[end] + 1);

            int code = Arrays.hashCode(freq);

            if(codeMap.containsKey(key) && codeMap.get(key).containsKey(code)){
                count += codeMap.get(key).get(code);

                codeMap.get(key).remove(code);
            }

            freq[s[start + 1]]--;
        }

        return count;
    }

    private static void solve()throws IOException{
        int l = sc.nextInt();

        HashMap<Integer, HashMap<Integer, Integer>> codeMap = new HashMap<>();
        HashSet<Integer> lengths = new HashSet<>();

        while(l-- > 0){
            char word[] = sc.readString().toCharArray();

            int key = 0, code = 0, freq[] = new int[26];

            for(int i = 0; i < word.length; i++){
                int ch = word[i] - 'a';

                if(i == 0)
                    key = 27*ch;
                else if(i == word.length - 1)
                    key += (ch + 1);
                else
                    freq[ch]++;
            }

            code = Arrays.hashCode(freq);

            // pw.println(word);
            // pw.println(key+" "+code);

            lengths.add(word.length);

            codeMap.putIfAbsent(key, new HashMap<>());

            int prevCodeFreq = codeMap.get(key).getOrDefault(code, 0);
            codeMap.get(key).put(code, prevCodeFreq + 1);
        }

        char s0 = sc.readChar(), s1 = sc.readChar();

        final int n = sc.nextInt();
        final long a = sc.nextLong(), b = sc.nextLong(), c = sc.nextLong(), d = sc.nextLong();
        
        int s[] = new int[n];
        s[0] = s0 - 'a';
        s[1] = s1 - 'a';

        long x[] = new long[n];
        x[0] = s0;
        x[1] = s1;

        for(int i = 2; i < n; i++){
            x[i] = (a*x[i - 1] + b*x[i - 2] + c)%d;

            s[i] = (int)(x[i]%26);
        }

        int count = 0;

        // pw.println(codeMap);
        
        for(Integer length: lengths){
            if(length == 1)
                count += countLength1Occurences(s, codeMap);
            else if(length == 2)
                count += countLength2Occurences(s, codeMap);
            else
                count += countGreaterOccurences(s, codeMap, length);
        }

        pw.println(count);
    }
}