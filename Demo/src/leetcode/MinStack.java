package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

class MinStack {

    Deque<Integer> stack;
    Deque<Integer> minsatck;


    public MinStack() {
        stack = new ArrayDeque<Integer>();
        minsatck = new ArrayDeque<Integer>();
        minsatck.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        stack.push(val);
        minsatck.push(Math.min(minsatck.peek(),val));
    }

    public void pop() {
        stack.pop();
        minsatck.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minsatck.peek();
    }
}
