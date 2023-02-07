package leetcode;

import java.util.*;

public class OrderTraversal {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


/*
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> pretree = new ArrayList<>();
        if (root == null){
            return pretree;
        }else {
            Traversal(root,pretree);
            }
        return pretree;
        }


    private void Traversal(TreeNode root, List<Integer> pretree) {
        while (root != null){
            pretree.add(root.val);
            if (root.left != null){
                Traversal(root.left,pretree);
            }else {
                Traversal(root.right,pretree);
            }
        }
    }
}
*/

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> pre = new ArrayList<>();
        if (root == null){
            return pre;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            pre.add(node.val);
            if (node.right != null){
                stack.push(node.right);
            }
            if (node.left != null){
                stack.push(node.left);
            }
        }
        return pre;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> in = new ArrayList<>();
        if (root == null){
            return in;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (stack.size() > 0 || root != null){
            if (root != null){
                stack.push(root);
                root = root.left;
            }else {
                TreeNode node = stack.pop();
                in.add(node.val);
                root = node.right;
            }
        }
        return in;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null){
            return list;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()){
            root = stack1.pop();
            stack2.push(root);
            if (root.left  != null){
                stack1.push(root.left);
            }
            if (root.right != null){
                stack1.push(root.right);
            }
        }
        while (!stack2.isEmpty()){
            list.add(stack1.pop().val);
        }
        return list;
    }

    /**
     * 写一下我的想法，用一个Map表来存每一层的数据，key是当前数据的值，value记录当前的层数
     * 先遍历左子树，再遍历右子树
     * 最后通过遍历map表，根据value值存入mid集合中，每产生一层的mid，就将此层的mid添加到list集合中
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderOne(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null){
            return list;
        }
        TreeNode temp = root;
        List<Integer> mid = new ArrayList<>();
        Map<Integer,Integer> map = new Map<Integer, Integer>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean containsKey(Object key) {
                return false;
            }

            @Override
            public boolean containsValue(Object value) {
                return false;
            }

            @Override
            public Integer get(Object key) {
                return null;
            }

            @Override
            public Integer put(Integer key, Integer value) {
                return null;
            }

            @Override
            public Integer remove(Object key) {
                return null;
            }

            @Override
            public void putAll(Map<? extends Integer, ? extends Integer> m) {

            }

            @Override
            public void clear() {

            }

            @Override
            public Set<Integer> keySet() {
                return null;
            }

            @Override
            public Collection<Integer> values() {
                return null;
            }

            @Override
            public Set<Entry<Integer, Integer>> entrySet() {
                return null;
            }
        };
        int i = 1;
        map.put(temp.val, i);
        while (temp.left != null || temp.right != null) {
            if (temp.left != null) {
                i++;
                map.put(temp.left.val, i);
                i--;
                temp = temp.left;
            }
            if (temp.right != null) {
                i++;
                map.put(temp.right.val, i);
                i--;
                temp = temp.right;
            }
        }
        int tp = 1;
        for (int j = 1; j <= map.size(); j++){
            if (map.get(j) == tp){
                mid.add(map.get(j));
            }
        }
        return list;
    }

    //思路是一样的思路，但就是自己没有实现，方法不对，区别是使用队列实现
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root == null){
            return list;
        }
        queue.add(root);
        while (queue.size() > 0){
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++){
                TreeNode node = queue.pop();
                temp.add(node.val);
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
            }
            list.add(temp);
        }
        return list;
    }

    public int maxDepth(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        int res = 0;
        if (root == null){
            return res;
        }
        queue.add(root);
        while (queue.size() > 0){
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++){
                TreeNode node = queue.pop();
                temp.add(node.val);
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
            }
            list.add(temp);
            res = res + 1;
        }
        return res;
    }

    public boolean isSymmetricOne(TreeNode root) {
        boolean res = false;
        LinkedList<TreeNode> queue1 = new LinkedList<>();
        LinkedList<TreeNode> queue2 = new LinkedList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list1.add(root.val);
        list2.add(root.val);
        /*TreeNode nodeleft,noderight;
        nodeleft = root.left;
        queue1.add(nodeleft);
        noderight = root.right;
        queue2.add(noderight);*/
        while (queue1.size() > 0){
            TreeNode node = queue1.pop();
            list1.add(node.val);
            if (node.left != null){
                queue1.add(node.left);
            } else{
                list1.add(-101);
            }
            if (node.right != null){
                queue1.add(node.right);
            }else{
                list1.add(-101);
            }
        }
        while (queue2.size() > 0){
            TreeNode node = queue2.pop();
            list2.add(node.val);
            if (node.right != null){
                queue2.add(node.right);
            }else{
                list2.add(-101);
            }
            if (node.left != null){
                queue2.add(node.left);
            }else{
                list2.add(-101);
            }
        }
        if (list1.equals(list2)){
            res = true;
        }else {
            res = false;
        }
        return res;
    }


    public boolean isSymmetric(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)){
            return true;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while (queue.size() > 0){
            TreeNode left = queue.removeFirst();
            TreeNode right =queue.removeFirst();
            if (left == null && right == null){
                continue;
            }
            if (left == null || right == null){
                return false;
            }
            if (left.val != right.val){
                return false;
            }
            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);
        }
        return true;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)){
            return root;
        }
       LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0){
            TreeNode temp = queue.pop();
            TreeNode left = temp.left;
            temp.left = temp.right;
            temp.right = left;
            if (temp.left != null){
                queue.add(temp.left);
            }
            if (temp.right != null){
                queue.add(temp.right);
            }
        }
        return root;
    }


    public boolean hasPathSumOne(TreeNode root, int targetSum) {
        boolean result = true;
        if (root == null){
            result =  false;
        }
        int res = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode left = root.left;
        TreeNode right = root.right;
        stack.push(left);
        stack.push(right);
        targetSum = targetSum - root.val;
        while (stack.size() > 0){
            TreeNode temp = stack.pop();
            targetSum = targetSum - temp.val;
            if (temp.left != null){
                stack.push(temp.left);
            }
            if (temp.right != null){
                stack.push(temp.right);
            }
            if (temp.right == null && temp.left == null){
                if (targetSum != 0){
                    targetSum = targetSum + temp.val;
                }else {
                    result = true;
                }
            }
        }
        return result;
    }
    /*boolean res = false;*/
    public boolean hasPathSum(TreeNode root, int targetSum) {
        boolean res = false;
        if (root == null){
            return res;
        }
        getSum(root,0,targetSum);
        return res;
    }
    private void getSum(TreeNode root, int sum, int targetSum) {
        boolean res = false;
        sum = sum + root.val;
        if (root.left == null && root.right == null){
            if (sum == targetSum){
                res = true;
                return;
            }
        }
        if (root.left != null){
            getSum(root.left,sum,targetSum);
        }
        if (root.right != null){
            getSum(root.right,sum,targetSum);
        }
    }


    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null){
            return root;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0){
            TreeNode temp = queue.pop();
            if (temp.val == val){
                root = temp;
                queue.add(temp);
                break;
            }
            if (temp.left != null){
                queue.add(temp.left);
            }
            if (temp.right != null){
                queue.add(temp.right);
            }
        }
        if (queue.size() > 0){
            return root;
        }else {
            return null;
        }
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode node = new TreeNode();
        node.val = val;
        if (root == null){
            return node;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0){
            TreeNode temp = queue.pop();
            if (temp.val > node.val){
                if (temp.left != null){
                    queue.add(temp.left);
                }else {
                    temp.left = node;
                }
            }
            if (temp.val < node.val){
                if (temp.right != null){
                    queue.add(temp.right);
                }else {
                    temp.right = node;
                }
            }
        }
        return root;
    }

    public boolean isValidBSTOne(TreeNode root) {
        boolean res = true;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0){
            TreeNode temp = queue.pop();
            /*if (temp.left.val > root.val || temp.right.val < root.val){
                res = false;
            }*/
            if (temp.left != null){
                if (temp.val < temp.left.val){
                    res =  false;
                }
                queue.add(temp.left);
            }
            if (temp.right != null){
                if (temp.val > temp.right.val){
                    res =  false;
                }
                queue.add(temp.right);
            }
        }
        return res;
    }


    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;// 左
            }
            // 中，处理
            TreeNode pop = stack.pop();
            if (pre != null && pop.val <= pre.val) {
                return false;
            }
            pre = pop;
            root = pop.right;// 右
        }
        return true;
    }

    public boolean findTarget(TreeNode root, int k) {
        if (root == null){
            return false;
        }
        TreeNode tree2 = new TreeNode();
        tree2 = root;
        LinkedList<TreeNode> queue1 = new LinkedList<>();
        queue1.add(root);
        while (!queue1.isEmpty()){
            TreeNode temp = queue1.pop();
            int num = k - temp.val;
            LinkedList<TreeNode> queue2 = new LinkedList<>();
            queue2.add(tree2);
            while (!queue2.isEmpty()){
                TreeNode temp2 = queue2.pop();
                if (temp2.val == num && temp2.val != temp.val){
                    return true;
                }
                if (temp2.left != null){
                    queue2.add(temp2.left);
                }
                if (temp2.right != null){
                    queue2.add(temp2.right);
                }
            }
            if (temp.left != null){
                queue1.add(temp.left);
            }
            if (temp.right != null){
                queue1.add(temp.right);
            }
        }
        return false;
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pp = getPath(root,p);
        List<TreeNode> qq = getPath(root,q);
        TreeNode ancestor = null;
        for (int i = 0; i < pp.size() && i < qq.size(); i++){
            if (pp.get(i) == qq.get(i)){
                ancestor = pp.get(i);
            }else {
                break;
            }
        }
        return ancestor;
    }

    private List<TreeNode> getPath(TreeNode root, TreeNode target) {
        List<TreeNode> path = new ArrayList<>();
        TreeNode node = root;
        while (node != target){
            path.add(root);
            if (target.val > node.val){
                root = root.right;
            }
            if (target.val < node.val){
                root = root.left;
            }
        }
        path.add(node);
        return path;
    }

    public TreeNode sortedArrayToBSTOne(int[] nums) {
        TreeNode root = new TreeNode(nums[nums.length/2]);
        if (nums.length >= 3){
            TreeNode left = new TreeNode(nums[nums.length/2 - 1]);
            left = root.left;
            TreeNode right = new TreeNode(nums[nums.length - 1]);
            right = root.right;
            for (int i = nums.length / 2 - 2; i >= 0 ; i--){
                TreeNode temp = new TreeNode(nums[i]);
                left.left = temp;
                left = left.left;
            }
            for (int i = nums.length - 2; i > nums.length/2; i--){
                TreeNode temp = new TreeNode(nums[i]);
                right.left = temp;
                right = right.left;
            }
        }else {
            TreeNode left = new TreeNode(nums[0]);
            left = root.left;
        }
        return root;
    }


    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums,0,nums.length - 1);
    }

    private TreeNode dfs(int[] nums, int left, int right) {
        if (left > right){
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = dfs(nums,left,mid - 1);
        root.right = dfs(nums,mid + 1,right);
        return root;
    }


    private Map<Integer,Integer> indexMap;

    private TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right){
            return null;
        }
        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);
        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder,inorder,preorder_left + 1,preorder_left + size_left_subtree,inorder_left,inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder,inorder,preorder_left + 1 + size_left_subtree,preorder_right,inorder_root + 1,inorder_right);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++){
            indexMap.put(inorder[i],i);
        }
        return myBuildTree(preorder,inorder,0,n-1,0,n-1);
    }

    public List<List<Integer>> zigzagLevelOrderOne(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
         boolean leftOrRight = true; //左边开始为false,右边开始为true
        if (root == null){
            return list;
        }
        stack.push(root);
        while (stack.size() > 0){
            int size = stack.size();
            List<Integer> temp = new ArrayList<>();
            if (leftOrRight == false){
                for (int i = 0; i < size; i++){
                    TreeNode node = stack.pop();
                    temp.add(node.val);
                    if (node.right != null){
                        stack.push(node.right);
                    }
                    if (node.left != null){
                        stack.push(node.left);
                    }
                }
                list.add(temp);
                leftOrRight = true;
            }else {
                for (int i = 0; i < size; i++){
                    TreeNode node = stack.pop();
                    temp.add(node.val);
                    if (node.left != null){
                        stack.push(node.left);
                    }
                    if (node.right != null){
                        stack.push(node.right);
                    }
                }
                list.add(temp);
                leftOrRight = false;
            }
        }
        return list;
    }

    //二叉树的锯齿状遍历，偶数次遍历过之后反转temp，最后再加入
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        boolean leftOrRight = false;
        if (root == null){
            return list;
        }
        queue.add(root);
        while (queue.size() > 0){
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++){
                TreeNode node = queue.pop();
                temp.add(node.val);
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
            }
            if (leftOrRight){
                Collections.reverse(temp);
            }
            leftOrRight = !leftOrRight;
            list.add(temp);
        }
        return list;
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root == null){
            return res;
        }
        queue.add(root);
        while (queue.size() > 0){
            int size = queue.size();
            int index = -101;
            for (int i = 0; i < size; i++){
                TreeNode node = queue.pop();
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
                index = node.val;
            }
            res.add(index);
        }
        return res;
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        List<Integer> path = new LinkedList<>();
        preorderdfs(root,targetSum,res,path);
        return res;
    }

    private void preorderdfs(TreeNode root, int targetSum, List<List<Integer>> res, List<Integer> path) {
        path.add(root.val);
        //遇到了叶子节点
        if (root.left == null && root.right == null){
            //找到和为targetSum的路径
            if (targetSum - root.val == 0){
                res.add(new ArrayList<>(path));
            }
            return;  // 找不到路径
        }

        if (root.left != null){
            preorderdfs(root.left,targetSum - root.val,res,path);
            path.remove(path.size() - 1);  //回溯
        }
        if (root.right != null){
            preorderdfs(root.right,targetSum - root.val,res,path);
            path.remove(path.size() - 1);  //回溯
        }
    }


    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null){
            return root;
        }
        if (root.val == key){
            if (root.left == null){
                return root.right;
            }
            if (root.right == null){
                return root.left;
            }
            if (root.left != null && root.right != null){
                TreeNode right = root.right;
                while (right.left != null){
                    right = right.left;
                }
                right.left = root.left;
                return root.right;
            }
        } else if (root.val < key) {
            root.right = deleteNode(root.right,key);
        }else {
            root.left = deleteNode(root.left,key);
        }
        return root;
    }

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> allnode = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode temp = queue.pop();
            allnode.add(temp.val);
            if (temp.left != null){
                queue.add(temp.left);
            }
            if (temp.right != null){
                queue.add(temp.right);
            }
        }
        Collections.sort(allnode);
        return allnode.get(k-1);
    }



}
