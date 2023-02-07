package leetcode;

import java.util.ArrayList;
import java.util.List;

public class IsAnagram {
    public static boolean isAnagram(String s, String t) {
        boolean res = true;
        List<Character> list1 = new ArrayList<>();
        for (int i =0; i < s.length(); i++){
            list1.add(s.charAt(i));
        }
        if (list1.size() == t.length()){
            for (int i = 0; i < t.length(); i++){
                for (int j = 0; j <= list1.size(); j++){
                    if (t.charAt(i) == list1.get(j)){
                        list1.remove(j);
                        break;
                    }
                }
            }
            if (list1.size() > 0){
                res = false;
            }
        }else {
            res = false;
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        System.out.println(isAnagram(s, t));
    }
}
