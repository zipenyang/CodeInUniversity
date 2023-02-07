package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }


class BSTIterator {
      private TreeNode cur;
      private Deque<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        cur = root;
        stack = new LinkedList<>();
    }
    public int next() {
        while (cur != null){
            stack.push(cur);
            cur = cur.left;
        }
        cur = stack.pop();
        int val = cur.val;
        cur = cur.right;
        return val;
    }
    public boolean hasNext() {
        return cur != null || !stack.isEmpty();
    }
}
