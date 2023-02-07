package leetcode;

import java.util.Stack;

public class IsValid {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{'){
                stack.push(s.charAt(i));
            }else {
                if (stack.size() == 0){
                    return false;
                }
                if (s.charAt(i) == ')'){
                    if (stack.peek() != '('){
                        return false;
                    }
                    else {stack.pop();}
                }
                if (s.charAt(i) == ']'){
                    if (stack.peek() != '['){
                        return false;
                    }
                    else {stack.pop();}
                }
                if (s.charAt(i) == '}'){
                    if (stack.peek() != '{'){
                        return false;
                    }
                    else {stack.pop();}
                }
            }

        }
        if (stack.size() == 0){
            return true;
        }else {
            return false;
        }
    }
}
