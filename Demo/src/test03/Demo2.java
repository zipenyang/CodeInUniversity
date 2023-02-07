package test03;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Demo2 {
    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = new ArrayList<String>();
        wordDict.add("leet");
        wordDict.add("code");
        System.out.println(SinWordDict(s, wordDict));
    }

    private static boolean SinWordDict(String s, List<String> wordDict) {
        boolean[] res = new boolean[s.length()+1];
        res[0] = true;
        Set<String> hashset = new HashSet<String>();
        hashset.addAll(wordDict);
        for (int i =1; i <= s.length(); i++){
            for (int j = 0; j < i; j++){
                if (res[j] && hashset.contains(s.substring(j,i))){
                    res[i] = true;
                }
            }
        }
        return res[s.length()];
    }
}
