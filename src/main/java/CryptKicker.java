import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

class CryptKicker {
    
    private static String getPattern(String word){
        
        int[] arr = new int[30];
        Arrays.fill(arr, -1);
        
        char[]chs = word.toCharArray();
        StringBuffer res = new StringBuffer();
        res.append(chs.length);
        for(int i = 0, idx = 0; i < chs.length; i++){
            if(arr[chs[i]-'a'] == -1){
                arr[chs[i]-'a'] = idx++;
            }
            res.append(arr[chs[i]-'a']);
        }
        return res.toString();
    }
    
    public static class Item{
        List<String> words = new LinkedList<String>();
        Set<String> usedWords = new HashSet<String>();
    }

    private static boolean solve(int idx, String[] ewords, Map<String, Item> patToWords, Map<Character, Character> mapping) {
        if(idx >= ewords.length){
            return true;
        }
        String pat = getPattern(ewords[idx]);
        Item item = patToWords.get(pat);
        if(item == null){
            return false;
        }
        
        char[]ewordChars = ewords[idx].toCharArray();

        main: for(String trial : item.words){
            if(!item.usedWords.contains(trial)){
                Map<Character, Character> currMapping = new HashMap<Character, Character>();
                currMapping.putAll(mapping);
                
                if(trial.length() != ewordChars.length){
                    throw new RuntimeException("Problem");
                }
                
                char[]trialChars = trial.toCharArray();
                
                for(int i = 0; i < trialChars.length; i++){
                    Character mappedCh = currMapping.get(ewordChars[i]);
                    if(mappedCh != null && mappedCh != trialChars[i]){
                        continue main;
                    }
                    currMapping.put(ewordChars[i], trialChars[i]);
                }
                item.usedWords.add(trial);
                if(solve(idx+1, ewords, patToWords, currMapping)){
                    mapping.putAll(currMapping);
                    item.usedWords.remove(trial);
                    return true;
                }
                item.usedWords.remove(trial);
            }
        }
        return false;
    }


    public static void main(String[] args) throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);

        //int n = Integer.parseInt(br.readLine());
        int n = sc.nextInt();
                
        Map<String, Item> patToWords = new HashMap<String, Item>();
        String[] dictWords = new String[n];
        
        for(int i = 0; i < n; i++){
            //String word = br.readLine();
            String word = sc.next();
            System.out.println("Next word:" + word);
            if(word == null || word.trim().length() == 0){
                --i;
                continue;
            }
            word = word.trim();
            String pat = getPattern(word);
            Item item = patToWords.get(pat);
            if(item == null){
                item = new Item();
            }
            item.words.add(word);
            patToWords.put(pat, item);
            dictWords[i] = word;
        }
        
        Arrays.sort(dictWords);
        
        //while(null != (str = br.readLine())){
        while(sc.hasNextLine()) {
            String str = sc.nextLine();
            if(str.trim().length() == 0)continue;
            //String[]ps = str.split("\\s+");
            System.out.println("Next line:" + str);
            String[] ps = str.split("\\s+");
            Set<String> ewordsSet = new HashSet<String>();
            for(String p : ps){
                ewordsSet.add(p);
            }
            String[]ewords = ewordsSet.toArray(new String[0]);
            
            Arrays.sort(ewords, new MyCmp());

            Map<Character, Character> mapping = new HashMap<Character, Character>();

            boolean success = solve(0, ewords, patToWords, mapping);

            StringBuffer sb = new StringBuffer();
            for(char ch : str.toCharArray()){
            //System.out.println(Arrays.toString(dictWords));
                if(Character.isWhitespace(ch)){
                    sb.append(ch);
                } else {
                    Character dech = success ? mapping.get(ch) : '*';
                    if(dech == null)throw new RuntimeException("no char");
                    sb.append(dech);
                }
            }
            System.out.println(sb.toString());
        }
    }

    private static class MyCmp implements java.util.Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return -Integer.valueOf(o1.length()).compareTo(o2.length());
        }
    }
}
