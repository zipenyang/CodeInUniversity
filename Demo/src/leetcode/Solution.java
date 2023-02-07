package leetcode;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Solution {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }


    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }




    //最大子数字和(我的错误解法)
    /*public int maxSubArray(int[] nums) {
        //思路：设置最大值max用于动态更新子数组加法的和
        int max = -10000;
        if (nums.length == 1){
            return nums[0];
        }
        else{
            int i = 0;
            while(i != nums.length - 1){
                int sum = 0;
                for (; i < nums.length; i++){
                    sum = sum + nums[i];
                    if (sum > max){
                        max = sum;
                    }
                }
                i++;
            }
        }
       return max;
    }*/
    //正确解法
    public int maxSubArray(int[] nums) {
        int[] arr = new int[nums.length];
        arr[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (arr[i - 1] > 0) {
                arr[i] = arr[i - 1] + nums[i];
            } else {
                arr[i] = nums[i];
            }
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            /*if(arr[i] > max){
                max = arr[i];*/
            max = Math.max(max, arr[i]);
        }
        return max;
    }




    //一分钟解题，还行
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = m,j = 0;i < nums1.length; i++,j++) {
            nums1[i] = nums2[j];
        }
        Arrays.sort(nums1);
    }



    //9.18刷题
    //两个集合的交集（错误代码）
    /*public int[] intersect(int[] nums1, int[] nums2) {
        int len = Math.max( nums2.length, nums1.length);
        ArrayList arr = new ArrayList(len);
        for (int i = 0;i < nums1.length; i++){
            for(int j = 0; j < nums2.length; j++){
                if (nums1[i] == nums2[j]){
                    int a = nums1[i];
                    arr.add(a);
                    break;
                }
            }
        }
        int[] res = new int[len];
        for (int k = 0; k < len; k++){
            res[k] = (int) arr.get(k);
        }
        return res;
    }*/
    //hashmap初体验，理解代码
    public int[] intersect(int[] nums1, int[] nums2) {
        //比较两个数组的长度，用小的数组创建hashmap表更优
        if (nums1.length>nums2.length){
            return intersect(nums2,nums1);
        }
        //创建hashmap表，存入数据
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for (int num : nums1){
            int count = map.getOrDefault(num,0) + 1;
            //存入num和put的相关数值
            map.put(num,count);
        }
        //设置存入最终数据的数组,nums1更短，所以两个数组的交集最大就是nums1的全集
        int[] intersection = new int[nums1.length];
        int index = 0;
        for (int num : nums2){
            int count = map.getOrDefault(num,0);
            if (count > 0){
                //将公共值写入数组
                intersection[index++] = num;
                count--;
                if (count > 0){
                    //更新hashmap表
                    map.put(num,count);
                }else {
                    //count=0,从hashmap表中移除相关的num值
                    map.remove(num);
                }
            }
        }
        //将指定数组的指定范围复制到一个新数组，在这里用来返回指定数组一定范围里的值
        return Arrays.copyOfRange(intersection,0,index);
    }




    //121.买卖股票的最佳时机
    //动态规划问题用寻常解法解决(穷举法),超出时间限制
    /*public int maxProfit(int[] prices) {
        int mid = 0;
        int max = 0;
        for (int i = 0; i < prices.length; i++){
            for (int j = i + 1; j < prices.length; j++){
                if(prices[j] > prices[i]){
                    mid = prices[j] - prices[i];
                    if (mid > max){
                        max = mid;
                    }
                }
            }
        }
        return max;
    }*/
    //贪心算法
    public int maxProfit(int[] prices) {
        int max = 0;
        int mid = prices[0];
        for(int i : prices){
            if (i < mid){
                mid = i;
                continue;
            }
            if (i > mid){
                max = Math.max(max,i - mid);
            }
        }
        return  max;
    }



    //9.19刷题
    //566.重塑矩阵（不会）
    //思路：先遍历一次，把数值存在一维数组里面，再遍历一次，按格式存进输出结果数组里
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int len1 = mat.length;
        int len2 = mat[0].length;
        int[][] res = new int[r][c];
        if (len1*len2 != r*c){
            return mat;
        }
        for (int i = 0; i < r; i++){
            for (int j = 0; j < c; j++){
                int m = i * c + j;
                int a = m / len2;
                int b = m % len2;
                res[i][j] = mat[a][b];
            }
        }
        return res;
    }





    //118.杨辉三角
    //运行出错，格式转换错误
    /*public List<List<Integer>> generate(int numRows) {
        //两层数组，一层存储
        List<List<Integer>> res = new ArrayList<>();
        List<int[][]> tmp = new ArrayList<>();
        int[][] arr = new int[numRows][numRows];
        if (numRows == 1) {
            arr[0][0] = 1;
            tmp.add(arr);
        }else {
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numRows; j++) {
                    arr[i][0] = 1;
                    arr[i][numRows - 1] = 1;
                    arr[i][j] = arr[i-1][j-1]+arr[i-1][j];
                }
                tmp.add(arr);
            }
        }
        res.add(tmp);
        return res;
    }*/
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i =0; i < numRows; i++) {
            List<Integer> tmp = new ArrayList<>();
            //for (int j = 0; j < numRows; j++){
            // if (j == 0 || j == numRows-1){      这样不行，感觉数组没有溢出，但是实际报错显示超出数组范围
            /*java.lang.IndexOutOfBoundsException: Index -1 out of bounds for length 0
            at line 64, java.base/jdk.internal.util.Preconditions.outOfBounds
            at line 70, java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex
            at line 266, java.base/jdk.internal.util.Preconditions.checkIndex
            at line 359, java.base/java.util.Objects.checkIndex
            at line 427, java.base/java.util.ArrayList.get
            at line 10, Solution.generate
            at line 54, __DriverSolution__.__helper__
            at line 84, __Driver__.main*/
            for (int j = 0; j <= i; j++){
                if (j == 0 || j == i){
                    tmp.add(1);
                }else{
                    tmp.add(res.get(i-1).get(j-1)+res.get(i-1).get(j));
                }
            }
            res.add(tmp);
        }
        return res;
    }





    //36.有效的数独
    //用hashmap记录每一行，每一列出现的数字及其次数
    /*public boolean isValidSudoku(char[][] board) {
            Map<Integer,Integer> map = new HashMap<Integer,Integer>();
            for (int i = 0; i < board.length; i++){
                for (int j = 0; j < board[i].length; j++){
                    int count = 0;
                    count = map.getOrDefault(board[i][j],0) + 1;
                    map.put((int) board[i][j],count);
                    if (count <= 1){
                        return true;
                    }else {
                        return false;
                    }
                }
            }
        return false;
    }*/
    //过于繁琐，选择放弃
    /*public boolean isValidSudoku(char[][] board) {
        Feasible(0,3,0,3,board);
        Feasible(0,3,4,6,board);
        Feasible(0,3,7,9,board);
        Feasible(4,6,0,3,board);
        Feasible(4,6,4,6,board);
        Feasible(4,6,7,9,board);
        Feasible(7,9,0,3,board);
        Feasible(7,9,4,6,board);
        Feasible(7,9,7,9,board);
        Feasible(0,9,0,9,board);
        return false;
    }
    private void Feasible(int m,int n,int a,int b,char[][] board) {
        boolean res = false;
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for (int i = m; i < n; i++){
            for (int j = a; j < b; j++){
                int count = 0;
                count = map.getOrDefault(board[i][j],0) + 1;
                map.put((int) board[i][j],count);
                if (count <= 1){
                    res = true;
                }else {
                    res = false;
                }
            }
        }
    }*/
    //别人的解法
    public boolean isValidSudoku(char[][] board) {
        //对行、列、每个小方块创建hashset表
        Set<Character> rowSet = new HashSet<>();
        Set<Character> colSet = new HashSet<>();
        Set<Character> gridSet = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // 行校验
                //判断board[j][i]不是.且board[j][i]不能添加到hashset表中去，返回false
                if (board[j][i] != '.' && !rowSet.add(board[j][i]))
                    {return false;}
                // 列校验
                if (board[i][j] != '.' && !colSet.add(board[i][j]))
                    {return false;}
                // 九宫格校验
                if (board[i % 3 * 3 + j / 3][i / 3 * 3 + j % 3] != '.' && !gridSet.add(board[i % 3 * 3 + j / 3][i / 3 * 3 + j % 3]))
                    {return false;}

            }
            //这个clear()方法用于从该集合中删除所有元素。此调用返回后，该集合将为空。
            rowSet.clear();
            colSet.clear();
            gridSet.clear();
        }
        return true;
    }





    //矩阵置0
    //初期解法，超出时间限制
    /*public void setZeroes(int[][] matrix) {
        List<Integer> row = new ArrayList<Integer>();
        List<Integer> col = new ArrayList<Integer>();
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                if (matrix[i][j] == 0){
                    row.add(i);
                    col.add(j);
                }
            }
        }
        int i = 0, j = 0;
        for (int a = 0; a < matrix.length; a++){
            for (int b = 0; b < matrix[0].length; b++){
            while (a == row.get(i)) {
                matrix[a][b] =0;
                }
            while (b == col.get(j)){
                matrix[a][b] = 0;
            }
            }
        }
    }*/
    //尝试用hashmap解题
    public void setZeroes(int[][] matrix) {
        int row_len = matrix.length;
        int col_len = matrix[0].length;
        Set<Integer> row = new HashSet<Integer>();
        Set<Integer> col = new HashSet<Integer>();
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                if (matrix[i][j] == 0){
                    row.add(i);
                    col.add(j);
                }
            }
        }
        for (int i = 0; i < row_len; i++){
            for (int j = 0; j < col_len; j++){
                if (row.contains(i) || col.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
        System.out.println(matrix);
    }




    //字符串中的第一个唯一字符
    public static int firstUniqChar(String s) {
        //hashmap中插入数据的顺序是无序的，这种情况下可以使用LinkedHashmap来实现有序插入
        Map<Character, Integer> map = new LinkedHashMap<Character, Integer>();
        char mid = 0;
        int res = -1;
            for (int i = 0; i < s.length(); i++) {
                int count = map.getOrDefault(s.charAt(i), 0) + 1;
                //存入i和put的相关数值
                map.put(s.charAt(i),count);
            }
            for (char a : map.keySet()) {
                if (map.get(a) == 1) {
                    mid = a;
                    break;
                }
            }
            for (int j = 0; j < s.length(); j++){
                if(s.charAt(j) == mid) {
                    res = j;
                    break;
                }
            }
            return res;
        }


    /*
     给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。

    如果可以，返回 true ；否则返回 false 。

    magazine 中的每个字符只能在 ransomNote 中使用一次。

     */


/*    public boolean canConstruct(String ransomNote, String magazine) {
        boolean res = true;
        Map<Character,Integer> map = new HashMap<>();
        int count = 0;
        *//*将magazine的数据存在hashmap中*//*
        for (int i = 0; i < magazine.length(); i++){
            count = map.getOrDefault(magazine.charAt(i),0) + 1;
            map.put(magazine.charAt(i),count);
        }
        for (int i = 0; i < ransomNote.length(); i++){
            if (!map.containsKey(ransomNote.charAt(i))){
                res = false;
            }
            count = map.get(ransomNote.charAt(i)) - 1;
            map.put(ransomNote.charAt(i),count);
            *//*for (Character s : map.keySet()){
                if (ransomNote.charAt(i) == s){
                    count = map.get(ransomNote.charAt(i)) - 1;
                    map.put(s,count);
                }else {
                    res = false;
                }
            }*//*
        }
        Set<Character> keyset = map.keySet();
        Iterator<Character> it = keyset.iterator();
        while (it.hasNext()){
            Character key = it.next();
            Integer values = map.get(key);
            if (values > 0){
                res = false;
            }
        }
        return res;
    }*/


    public static boolean canConstruct(String ransomNote, String magazine) {
        boolean res = true;
        List<Character> list1 = new ArrayList<>();
        List<Character> list2 = new ArrayList<>();
        for (int i =0; i < ransomNote.length(); i++){
            list1.add(ransomNote.charAt(i));
        }
        for (int i =0; i < magazine.length(); i++){
            list2.add(magazine.charAt(i));
        }
        for (int i = 0; i < ransomNote.length(); i++){
            int  j = 0;
            while (j < list2.size()){
                if (list1.get(0).equals(list2.get(j))){
                    list1.remove(0);
                    list2.remove(j);
                    break;
                }
                j++;
            }
        }
        if (list1.size() > 0 ){
            res = false;
        }
        return res;
    }


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


    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    public boolean hasCycle(ListNode head) {
        boolean res = false;
        Set<ListNode> Node = new HashSet<ListNode>();
        while (head != null){
            if (!Node.add(head)){
                res = true;
            }
            head = head.next;
        }
        return res;
    }


/*    public static Integer getKey(HashMap<Integer, Integer> map, Integer value) {
        int key = -100000;
        //Map,HashMap并没有实现Iteratable接口,不能用增强for循环
        for (Integer getkey : map.keySet()) {
            if (map.get(getkey).equals(value)) {
                key = getkey;
            }
        }
        return key;
        //这个key,肯定是最后一个满足条件的key
    }*/

    public static int singleNumber(int[] nums) {
        int res = -100000;
        Map<Integer,Integer> hashmap = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            int count = hashmap.getOrDefault(nums[i],0)+1;
            hashmap.put(nums[i],count);
        }
        res = getKey(hashmap,1);
        return res;
    }

    private static Integer getKey(Map<Integer, Integer> map, Integer value) {
        int key = -100000;
        //Map,HashMap并没有实现Iteratable接口,不能用增强for循环
        for (Integer getkey : map.keySet()) {
            if (map.get(getkey).equals(value)) {
                key = getkey;
            }
        }
        return key;
    }

    public static int singleNumber2(int[] nums) {
        int res = -100000;
        Map<Integer,Integer> hashmap = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            int count = hashmap.getOrDefault(nums[i],0)+1;
            hashmap.put(nums[i],count);
        }
        for (Integer i : hashmap.keySet()){
            Integer count = hashmap.get(i);
            if (count == 1){
                res =  i;
            }
        }
        return res;
    }

    public int majorityElement(int[] nums) {
        int size = nums.length/2;
        Map<Integer,Integer> hashmap = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            int count = hashmap.getOrDefault(nums[i],0)+1;
            hashmap.put(nums[i],count);
        }
        for (Integer i : hashmap.keySet()){
            Integer count = hashmap.get(i);
            if (count > size){
                return i;
            }
        }
        return -1;
    }


    public List<List<Integer>> threeSumMy(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n; i++){
            for (int j = i+1; j < n; j++){
                for (int k = j+1; k < n; k++){
                    List<Integer> mid = new ArrayList<>();
                    if (nums[i] + nums[j] + nums[k] == 0){
                        mid.add(nums[i]);
                        mid.add(nums[j]);
                        mid.add(nums[k]);
                    }
                    res.add(mid);
                }
            }
        }
        return res;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        if (nums == null || n < 3){
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < n; i++){
            if (nums[i] > 0){
            return res;
            }
            if (i > 0 && nums[i] == nums[i-1]){continue;}
            int l = i + 1;
            int r = n - 1;
            while (l < r){
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0){
                    res.add(Arrays.asList(nums[i],nums[l],nums[r]));
                    while (l < r && nums[l] == nums[l+1]){
                        l++;
                    }
                    while (l < r && nums[r] == nums[r-1]){
                        r--;
                    }
                    l++;
                    r--;
                } else if (sum < 0) {
                    l++;
                } else if (sum > 0) {
                    r--;
                }
            }
        }
        return res;
    }


    //冒泡排序
    public void sortColorsOne(int[] nums) {
        int n = nums.length;
        while (n > 0){
            for (int i = 0; i < n-1; i++){
                int next = i + 1;
                if (nums[i] > nums[next]){
                    int temp = nums[next];
                    nums[next] = nums[i];
                    nums[i] = temp;
                }
                System.out.print(nums[i]+",");
            }
            n--;
        }
    }
    //快速排序的partition过程
    public void sortColors(int[] nums) {
        int n = nums.length;
        if (n < 2){
            return;
        }
        int zero = 0;
        int two = n;
        int i = 0;
        while (i < two){
            if (nums[i] == 0){
                swap(nums,i,zero);
                i++;
                zero++;
            } else if (nums[i] == 1) {
                i++;
            }else {
                two--;
                swap(nums,i,two);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        Arrays.sort(intervals,(x,y)->x[0]-y[0]);
        int l = intervals[0][0];
        int r = intervals[0][1];
        for (int i = 0; i < intervals.length; i++){
            if (intervals[i][0] > r){
                res.add(new int[]{l,r});
                l = intervals[i][0];
                r = intervals[i][1];
            }else {
                r = Math.max(r,intervals[i][1]);
            }
        }
        res.add(new int[]{l,r});
        return res.toArray(new int[0][]);
    }

    public List<Integer> getRow(int rowIndex) {
        if (rowIndex == 0){
            return Arrays.asList(1);
        }
        if (rowIndex == 1){
            return Arrays.asList(1,1);
        }
        List<Integer> res = new ArrayList<>();
        int[] Pascal = new int[rowIndex+1];
        Pascal[0] = 1;
        Pascal[1] = 1;
        for (int i = 2; i <= rowIndex; i++){
            for (int j = i-1; j >= 1; j--){
               Pascal[j] = Pascal[j] + Pascal[j-1];
            }
            Pascal[i] = 1;
        }
        for (int i = 0; i <= rowIndex; i++){
            res.add(Pascal[i]);
        }
        return res;
    }

    public void rotate(int[][] matrix) {
        int n = matrix.length;
/*        for (int i = 0; i < n / 2; i++){
            for (int j = 0; j < (n+1) / 2; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n-j-1];
                matrix[i][n-j-1] = matrix[n-i-1][n-j-1];
                matrix[n-i-1][n-j-1] = matrix[n-i-1][j];
                matrix[n-i-1][j] = temp;
            }
        }*/
        for (int i = 0; i < n / 2; i++){
            for (int j = 0; j < (n+1) / 2; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-j-1][i];
                matrix[n-j-1][i] = matrix[n-i-1][n-j-1];
                matrix[n-i-1][n-j-1] = matrix[j][n-i-1];
                matrix[j][n-i-1] = temp;
            }
        }
    }

    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int loop = 0;
        int start = 0;
        int count = 1;
        int i,j;
        while (loop++ < n / 2){
            for (j = start; j < n - loop; j++){
                res[start][j] = count++;
            }
            for (i = start; i < n - loop; i++){
                res[i][j] = count++;
            }
            for (;j >= loop; j--){
                res[i][j] = count++;
            }
            for (; i >= loop; i--){
                res[i][j] = count++;
            }
            start++;
        }
        if (n % 2 == 1){
            res[start][start] = count;
        }
        return res;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int  i = 0;
        int j = matrix[0].length - 1;
        while (i < matrix.length && j >= 0){
            if (target > matrix[i][j]){
                i++;
            }else if (target < matrix[i][j]){
                j--;
            }else {
                return true;
            }
        }
        return false;
    }


    public int eraseOverlapIntervals(int[][] intervals) {
        int count = 0;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int min_end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++){
            if (intervals[i][0] < min_end){
                count++;
                min_end = Math.min(min_end,intervals[i][1]);
            }else {
                min_end = intervals[i][1];
            }
        }
        return count;
    }


    public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3){
            return false;
        }
        int n = nums.length;
        int second = Integer.MAX_VALUE;
        int first = nums[0];
        for (int i = 0; i < n; i++){
            int num = nums[i];
            if (num > second){
                return true;
            } else if (num > first) {
                second = num;
            }else {
                first = num;
            }
        }
        return false;
    }

    public static int[] productExceptSelfOne(int[] nums) {
        int n = nums.length;
        int res = 1;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++){
            int temp = nums[i];
            temp = 1;
            for (int j = 0; j < n; j++){
                if (j != i){
                    res = res * nums[j];
                }else {
                    res = res * 1;
                }
            }
            answer[i] = res;
            res = 1;
        }
        return answer;
    }

    public int[] productExceptSelf(int[] nums) {
        int[] L = new int[nums.length];
        int[] R = new int[nums.length];
        int[] anwser = new int[nums.length];

        L[0] = 1;
        for (int i = 1; i < nums.length; i++){
            L[i] = L[i-1] * nums[i-1];
        }

        R[nums.length-1] = 1;
        for (int i = nums.length - 2; i >=0; i--){
            R[i] = R[i+1] * nums[i+1];
        }

        for (int i = 0; i < nums.length; i++){
            anwser[i] = L[i] * R[i];
        }
        return anwser;
    }

    public int subarraySumOne(int[] nums, int k) {
        int result = 0;
        if (nums.length <= 1 && nums[0] != k){
            return result;
        }
        for (int i = 0; i < nums.length; i++){
            int target = k - nums[i];
            if (target == 0){
                result++;
            }else if (target > 0){
                while (target > 0 && i < nums.length - 1){
                    i++;
                    target = target - nums[i];
                    if (target == 0){
                        result++;
                    }else if (target < 0){
                        break;
                    }
                }
            }else {
                continue;
            }
        }
        return result;
    }

    public int subarraySum(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        int preSum = 0;
        int count = 0;
        map.put(0,1);
        for (int num : nums) {
            preSum += num;
            // 目标前缀和：使得当前前缀和-目标前缀和=k
            int target = preSum - k;
            // 查看是否有我们的目标前缀和，如果有，将他出现的次数加入到count
            count += map.getOrDefault(target,0);
            // 更新当前前缀和出现的次数
            map.put(preSum,map.getOrDefault(preSum,0)+1);
        }
        return count;
    }

    public String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder("");
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0){
            int n1 = i >= 0 ? num1.charAt(i) - '0': 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0': 0;
            int temp = n1 + n2 + carry;
            carry = temp / 10;
            res.append(temp % 10);
            i--;
            j--;
        }
        if (carry == 1){
            res.append(1);
        }
        return res.reverse().toString();
    }



    public static int longestPalindromea(String s) {
        if (s.length() == 1){
            return 1;
        }
        int res = 0;
        Map<Character,Integer> map = new HashMap<>();
        ArrayList<Integer> odd = new ArrayList<>();
        for (int i = 0; i < s.length(); i++){
            int count = map.getOrDefault(s.charAt(i),0)+1;
            map.put(s.charAt(i),count);
        }
        for (Integer sum : map.values()) {
            if (sum % 2 == 0){
                res = res + sum;
            }else {
                odd.add(sum);
            }
        }
        Arrays.sort(new ArrayList[]{odd});
        for (int i = 0; i < odd.size(); i++){
            if (i == 0){
                res = res + odd.get(i);
            }else {
                res = res + odd.get(i) - 1;
            }
        }
        return res;
    }


    public static boolean wordPatternOne(String pattern, String s) {
        Map<Character,String> map = new HashMap<>();
        int flag = 0;
        String temp = "";
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) != ' '){
                temp = temp + s.charAt(i);
            }else {
                if (!map.containsKey(pattern.charAt(flag))){
                    map.put(pattern.charAt(flag),temp);
                    flag++;
                    temp = "";
                }else {
                    if (!map.containsValue(temp)){
                        return false;
                    }
                }
                continue;
            }
        }
        return true;
    }

    public static boolean wordPattern(String pattern, String s) {
        Map<Character,String> map = new HashMap<>();
        String[] array = s.split(" ");
        if (pattern.length() != array.length){
            return false;
        }
        for (int i = 0; i < pattern.length(); i++){
            char key = pattern.charAt(i);
            if (map.containsKey(key)){
                if (!map.get(key).equals(array[i])){
                    return false;
                }
            }else {
                if (map.containsValue(array[i])){
                    return false;
                }
                map.put(key,array[i]);
            }
        }
        return true;
    }


    public List<Integer> partitionLabels(String s) {
        List<Integer> res = new ArrayList<>();
        int last = -1;
        for (int i = 0; i < s.length(); i++){
            char temp = s.charAt(i);
            for (int j = s.length()-1; j >= i; j--){
                if (temp == s.charAt(j)){
                    last = Math.max(last,j+1);
                }
                if (i == last - 1){
                    if (!res.contains(last)){
                        res.add(last);
                    }
                }
            }
        }
        for (int i = res.size()-1; i > 0; i--){
            res.set(i, res.get(i) - res.get(i - 1));
        }
        return res;
    }


    public List<List<String>> groupAnagramsOne(String[] strs) {
        if (strs == null){
            return null;
        }
        List<List<String>> res = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        if (strs.length == 1){
            res.add(Arrays.asList(strs));
            return res;
        }
        Map<Character,Integer> hashmap = new HashMap<>();
        for (int i = 0; i < strs.length; i++){
            String s = strs[i];
            if (i == 0){
                for (int j = 0; j < s.length(); j++){
                    int count = hashmap.getOrDefault(s.charAt(j),0) + 1;
                    hashmap.put(s.charAt(j),count);
                    temp.add(s);
                }
            }else {
                for (int j = 0; j < s.length(); j++){
                    if (hashmap.containsKey(s.charAt(j))){
                        int count = hashmap.get(s.charAt(j)) - 1;
                        if (count == 0){
                            hashmap.remove(s.charAt(i));
                        }else {
                            hashmap.put(s.charAt(j),count);
                        }
                    }
                }
                //思路错误，不继续往下写了
                for (Integer value : hashmap.values()) {
                    if (value > 0){
                    }
                }
            }
        }
        return res;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    public static String multiplyOne(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        String res = "0";
        if (num2.length() > num1.length()){
            String mid = num2;
            num2 = num1;
            num1 = mid;
        }
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int q = num2.length() - 1;
        int carry = 0;
        int temp = 0;
        while (i >= 0){
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            Stack<Integer> stack = new Stack<>();
            StringBuilder aaa = new StringBuilder("");
            for (int k = q; k > i; k--) {
                aaa.append(0);
            }
            while (j >= 0){
                int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
                temp = n1 * n2 + carry;
                carry = temp / 10;
                stack.push(temp % 10);
                j--;
            }
            if (carry != 0) {
                aaa.append(carry % 10);
            }
            res = addStrings2(res, aaa.reverse().toString());
            i--;
            j = q;
            temp = 0;
            carry = 0;
        }
        return res;
    }

    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        String ans = "0";
        int m = num1.length(), n = num2.length();
        for (int i = n - 1; i >= 0; i--) {
            StringBuffer curr = new StringBuffer();
            int add = 0;
            for (int j = n - 1; j > i; j--) {
                curr.append(0);
            }
            int y = num2.charAt(i) - '0';
            for (int j = m - 1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';
                int product = x * y + add;
                curr.append(product % 10);
                add = product / 10;
            }
            if (add != 0) {
                curr.append(add % 10);
            }
            ans = addStrings2(ans, curr.reverse().toString());
        }
        return ans;
    }

    public static String addStrings2(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        ans.reverse();
        return ans.toString();
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        Map<String,Integer> map = new HashMap<>();
        for (int i = 0; i <= s.length() - 10; i++){
            String aaa = s.substring(i,i+10);
            int count = map.getOrDefault(aaa,0) + 1;
            map.put(aaa,count);
            if (map.get(aaa) == 2){
                res.add(aaa);
            }
        }
        return res;
    }

    public static String longestPalindromeaa(String s) {
        String res = "";
        if (s == null || s.length() < 1){
            return res;
        }
        boolean flag = false;
        for(int i = 0; i < s.length(); i++){
            int end = s.length() - 1;
            StringBuilder temp = new StringBuilder("");
            while (end >= i){
                if (s.charAt(end) == s.charAt(i)){
                    temp.append(s.charAt(i));
                    i++;
                }
                end--;
                if (end == i){
                    flag = true;
                    res = String.valueOf(temp);
                    res = res + s.substring(i,i + temp.length() + 1);
                    break;
                }
            }
            if (flag == true){
                break;
            }
        }
        return res;
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1){
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++){
            int len1 = expandAroundCenter(s,i,i);
            int len2 = expandAroundCenter(s,i,i+1);
            int len = Math.max(len1,len2);
            if (len > end - start){
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start,end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            --left;
            ++right;
        }
        return right - left - 1;
    }

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode res = null;
        int num1 = 0, num2 = 0;
        int count1= 0, count2= 0;
        while (l1 != null || l2 != null){
            if (l1 != null){
                if (count1 == 0){
                    num1 = l1.val;
                    count1++;
                }else {
                    num1 = (int) (num1 + l1.val * Math.pow(10,count1));
                }
            }
            if (l2 != null){
                if (count2 == 0){
                    num1 = l1.val;
                    count2++;
                }else {
                    num2 = (int) (num2 + l1.val * Math.pow(10,count2));
                }
            }
        }
        int sum = count1 + count2;
        String s = String.valueOf(sum);
        for (int i = s.length() - 1; i >= 0; i--){
            res.val = s.charAt(i);
            res = res.next;
        }
        return res;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode temp = res;
        int carry = 0;
        while (l1 != null || l2 != null){
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;
            carry = sum / 10;
            sum = sum % 10;
            temp.next = new ListNode(sum);
            temp = temp.next;
            if (l1 != null){
                l1 = l1.next;
            }
            if (l2 != null){
                l2 = l2.next;
            }
        }
        if (carry == 1){
            temp.next = new ListNode(carry);
        }
        return res.next;
    }

    public ListNode detectCycle(ListNode head) {
        ListNode res = head;
        Set<ListNode> map = new HashSet<>();
        while (res != null){
            if (map.contains(res)){
                return res;
            }else {
                map.add(res);
            }
            res = res.next;
        }
        return null;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        Set<ListNode> map = new HashSet<>();
        while (a != null){
            map.add(a);
            a = a.next;
        }
        while (b != null){
            if (map.contains(b)){
                return b;
            }else {
                b = b.next;
            }
        }
        return null;
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode res = new ListNode(-101);
        ListNode then = res;
        while (head != null){
            if (head.next == null || head.val != head.next.val){
                then.next = head;
                then = then.next;
            }
            while (head.next != null && head.val == head.next.val){
                head = head.next;
            }
            head = head.next;
        }
        then.next = null;
        return res.next;
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode temp = pre;
        while (temp.next != null && temp.next.next != null){
            ListNode start = temp.next;
            ListNode end = temp.next.next;
            temp.next = end;
            start.next = end.next;
            end.next = start;
            temp = start;
        }
        return pre.next;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        Deque<ListNode> stack = new ArrayDeque<ListNode>();
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        while (true){
            int count = 0;
            ListNode temp = head;
            while (temp != null && count < k){
                stack.add(temp);
                temp = temp.next;
                count++;
            }
            if (count != k){
                p.next = head;
                break;
            }
            while (!stack.isEmpty()){
                p.next = stack.pollLast();
                p = p.next;
            }
            p.next = temp;
            head = temp;
        }
        return dummy.next;
    }

    public String minRemoveToMakeValidOne(String s) {
        StringBuilder res = new StringBuilder("");
        Deque<Character> queue = new ArrayDeque<Character>();
        int temp = 0;
        for (int i = 0; i < s.length(); i++){
            if(s.charAt(i) != '(' && s.charAt(i) != ')'){
                res.append(s.charAt(i));
            }
            if (s.charAt(i) == '('){
                int count = 0;
                for (int j = i; j < s.length(); j++){
                    if (s.charAt(j) == '('){
                        if (s.substring(j,s.length() - 1).contains(")")){
                            count++;
                        }else {
                            continue;
                        }
                    }
                    if (s.charAt(j) == ')'){
                        if (count > 0){
                            count--;
                        }else {
                            continue;
                        }
                    }
                    queue.add(s.charAt(j));
                }
                temp = count;
                break;
            }
            if (s.charAt(i) == ')'){
                continue;
            }
        }
        while (!queue.isEmpty()){
            res.append(queue.pop());
        }
        return String.valueOf(res);
    }


    public String minRemoveToMakeValid(String s) {
        Set<Integer> indexesToRemove = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '('){
                stack.push(i);
            }
            if (s.charAt(i) == ')'){
                if (stack.isEmpty()){
                    indexesToRemove.add(i);
                }else {
                    stack.pop();
                }
            }
        }
        while (!stack.isEmpty()){
            indexesToRemove.add(stack.pop());
        }
        StringBuilder res = new StringBuilder("");
        for (int i = 0; i < s.length(); i++){
            if (!indexesToRemove.contains(i)){
                res.append(s.charAt(i));
            }
        }
        return res.toString();
    }

    public static int findTheWinner(int n, int k) {
        if (k == 1){
            return n;
        }
        ArrayList<Integer> findWinner = new ArrayList<>();
        int count = 0;
        for (int i = 1; i <= n; i++){
            count++;
            if (count == k){
                count = 0;
                continue;
            }
            findWinner.add(i);
        }
        while (findWinner.size() != 1){
            for (int i = 0; i < findWinner.size(); i++){
                count++;
                if (count == k){
                    count = 0;
                    findWinner.remove(findWinner.get(i));
                    i = i - 1;
                }
            }
        }
        return findWinner.get(0);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == p || root == q || root == null){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        // 如果left 和 right都不为空，说明此时root就是最近公共节点
        // 如果left为空，right不为空，就返回right，说明目标节点是通过right返回的，反之亦然。
        if (left != null && right != null){
            return root;
        }
        if (left == null){
            return right;
        }
        return left;
    }

    public int findJudge(int n, int[][] trust) {
        int[] in = new int[n + 1], out = new int[n + 1];
        for (int[] t : trust) {
            int a = t[0], b = t[1];
            in[b]++;
            out[a]++;
        }
        for (int i = 1; i <= n; i++){
            if (in[i] == n - 1 && out[i] == 0){
                return i;
            }
        }
        return -1;
    }

    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        List<Integer> res = new ArrayList<>();
        int[] in = new int[n];
        int[] out = new int[n];
        for (List<Integer> edge : edges) {
            int a = edge.get(0), b = edge.get(1);
            in[b]++;
            out[a]++;
        }
        for (int j = 0; j < n; j++){
            if (in[j] == 0){
                res.add(j);
            }
        }
        return res;
    }

    public static boolean canVisitAllRoomsOne(List<List<Integer>> rooms) {
        boolean[] flag = new boolean[rooms.size()];
        flag[0] = true;
        for (List<Integer> room : rooms) {
            for (int i = 0; i < room.size(); i++){
                flag[room.get(i)] = true;
            }
        }
        for (boolean b : flag) {
            if (b != true){
                return false;
            }
        }
        return true;
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size(), num = 0;
        boolean[] visited = new boolean[n];
        Deque<Integer> queue = new LinkedList<>();
        visited[0] = true;
        queue.offer(0);
        while (!queue.isEmpty()){
            int x = queue.pop();
            num++;
            for (int it : rooms.get(x)) {
                if (!visited[it]){
                    visited[it] = true;
                    queue.offer(it);
                }
            }
        }
        return num == n;
    }

    public int findKthLargest(int[] nums, int k) {
        if (nums.length == 1 && k == 1){
            return nums[0];
        }
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        Map<Integer,Integer> hashmap = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            int count = hashmap.getOrDefault(nums[i],0) + 1;
            hashmap.put(nums[i],count);
        }
        Set<Map.Entry<Integer,Integer>> entries = hashmap.entrySet();
        // 根据map的value值，构建于一个大顶堆（o1 - o2: 小顶堆， o2 - o1 : 大顶堆）
        PriorityQueue<Map.Entry<Integer,Integer>> queue = new PriorityQueue<>(((o1, o2) -> o2.getValue() - o1.getValue()));
        for (Map.Entry<Integer, Integer> entry : entries) {
            queue.offer(entry);
        }
        for (int i = k - 1; i >= 0; i--){
            res[i] = queue.poll().getKey();
        }
        return res;
    }

    public String frequencySortOne(String s) {
        String res = "";
        Map<Character,Integer> hashmap = new HashMap<>();
        for (int i = 0; i < s.length(); i++){
            int count = hashmap.getOrDefault(s.charAt(i),0) + 1;
            hashmap.put(s.charAt(i),count);
        }
        Set<Map.Entry<Character,Integer>> entries = hashmap.entrySet();
        // 根据map的value值，构建于一个大顶堆（o1 - o2: 小顶堆， o2 - o1 : 大顶堆）
        PriorityQueue<Map.Entry<Character,Integer>> queue = new PriorityQueue<>(((o1, o2) -> o2.getValue() - o1.getValue()));
        for (Map.Entry<Character, Integer> entry : entries) {
            queue.offer(entry);
        }
        for (int i = hashmap.size() - 1; i >= 0; i--){
            int num = queue.poll().getValue();
            for (int j = 0; j < num; j++){
                res  += queue.poll().getKey();
            }
        }
        return res;
    }

    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        // 1. 统计词频
        for (Character c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        /*
        哈希表使用 entrySet() 获取键值对集合
        遍历键值对集合， 利用键值对Map.Entry 的方法分别去取对应的键 getKey()getKey() 、值 getValue()getValue()
        */
        // 2. 按词频将字符降序排列-->此处将Map.Entry转存成了数组（对应字符即转化成ASCII码存入）
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> {
            return a[1] != b[1] ? b[1] - a[1] : a[0] - b[0];
        });
        for (Map.Entry<Character, Integer> e : map.entrySet()) {
            q.offer(new int[]{e.getKey(), e.getValue()});
        }
        // 2. 将降序排列字符按序按词频构造新字符串
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int c = cur[0], cnt = cur[1];
            while (cnt -- > 0){
                sb.append((char)c);
            }
        }
        return sb.toString();
    }

    public int[][] kClosest(int[][] points, int k) {
        int[][] res = new int[k][2];
        int[] array = new int[points.length];
        Map<int[],Integer> hashmap = new HashMap<>();
        for (int i = 0; i < points.length; i++){
            int a = points[i][0], b = points[i][1];
            int ojld = a*a + b*b;
            hashmap.put(points[i],ojld);
            array[i] = ojld;
        }
        Arrays.sort(array);
        for (int i : array) {

        }
        return res;
    }

    public int[][] kClosestCopy(int[][] points, int k) {
        // 默认是小根堆，实现大根堆需要重写一下比较器。
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);
        for (int[] point: points) {
            if (pq.size() < k) { // 如果堆中不足 K 个，直接将当前 point 加入即可
                pq.offer(point);
            } else if (pq.comparator().compare(point, pq.peek()) > 0) { // 否则，判断当前点的距离是否小于堆中的最大距离，若是，则将堆中最大距离poll出，将当前点加入堆中。
                pq.poll();
                pq.offer(point);
            }
        }

        // 返回堆中的元素
        int[][] res = new int[pq.size()][2];
        int idx = 0;
        for (int[] point: pq) {
            res[idx++] = point;
        }
        return res;
    }

    public int searchOne(int[] nums, int target) {
        if (nums[0] > target){
            return -1;
        }
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == target){
                return i;
            }
            if(nums[i] > target){
                return -1;
            }
        }
        return -1;
    }

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = (left + right) / 2;
            if (nums[mid] == target){
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while (left < right){
            int mid = (left + right) / 2;
            /*if (isBadVersion(mid)){
                right = mid;
            }else {
                left = mid + 1;
            }*/
        }
        return right;
    }

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = (right + left) / 2;
            if (nums[mid] == target){
                return mid;
            }else if (nums[mid] > target){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return left;
    }

    public int[] sortedSquaresOne(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++){
            res[i] = nums[i] * nums[i];
        }
        Arrays.sort(res);
        return res;
    }

    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0, j = n - 1, pos = n - 1;i <= j;){
            if (nums[i] * nums[i] > nums[j] * nums[j]){
                ans[pos] = nums[i] * nums[i];
                ++i;
            }else {
                ans[pos] = nums[j] * nums[j];
                --j;
            }
            --pos;
        }
        return ans;
    }

    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++){
            ans[(i + k) % n] = nums[i];
        }
        /*int count = 0;
        for (int i = nums.length - k; i < nums.length; i++){
            ans[count] = nums[i];
            count++;
        }
        for (int i = 0; i < nums.length - k; i++){
            ans[count] = nums[i];
            count++;
        }*/
        System.arraycopy(ans,0,nums,0,n);
    }

    public void moveZeroes(int[] nums) {
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == 0){
                for (int j = i + 1; j < nums.length; j++){
                    if (nums[j] != 0){
                        int temp = nums[j];
                        nums[j] = nums[i];
                        nums[i] = temp;
                        break;
                    }
                }
            }
        }
    }

    public int[] twoSum167(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        while (i < j){
            int sum = numbers[i] + numbers[j];
            if (sum < target){
                i++;
            } else if (sum > target) {
                j--;
            }else {
                return new int[]{i+1,j+1};
            }
        }
        return new int[]{-1,-1};
    }

    public void reverseString(char[] s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s) {
            stack.push(c);
        }
        char[] temp = new char[s.length];
        int i = 0;
        while (!stack.isEmpty()) {
            s[i] = stack.pop();
            i++;
        }
    }


    public static String reverseWordsOne(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) != ' '){
                stack.push(s.charAt(i));
            }else {
                while (!stack.isEmpty()){
                    ans.append(stack.pop());
                }
                if(i != s.length() - 1){
                ans.append(" ");
                }
            }

        }
        return ans.toString();
    }

    public String reverseWords(String s) {
        String[] s1 = s.split(" ");
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s1.length; i++){
            StringBuilder s2 = new StringBuilder(s1[i]);
            ans.append(s2.reverse());
            if (i != s1.length - 1){
                ans.append(" ");
            }
        }
        return ans.toString();
    }

    public ListNode middleNode(ListNode head) {
        Map<Integer,ListNode> map = new HashMap<>();
        int count = 0;
        while (head != null){
            map.put(count,head);
            head = head.next;
            if (head != null){
                count++;
            }
        }
        return map.get(count / 2);
    }

    public ListNode removeNthFromEndOne(ListNode head, int n) {
        ListNode ans = new ListNode(-1);
        List<Integer> list = new ArrayList<>();
        while (head != null){
            list.add(head.val);
        }
        list.remove(list.size() - n);
        for (int i = 0; i < list.size(); i++){
            ListNode temp = new ListNode(list.get(i));
            ans.next = temp;
            ans = ans.next;
        }
        return ans.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length = 0;
        while (head != null){
            length++;
            head = head.next;
        }
        ListNode cur = dummy;
        for (int i = 1;i < length - n + 1; i++){
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return dummy.next;
    }

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0){
            return 0;
        }
        Map<Character,Integer> map = new HashMap<>();
        int left = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++){
            if (map.containsKey(s.charAt(i))){
                left = Math.max(left,map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i - left + 1);
        }
        return max;
    }

    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        if (n > m){
            return false;
        }
        int[] cn1 = new int[26];
        int[] cn2 = new int[26];
        for (int i = 0; i < n; ++i){
            ++cn1[s1.charAt(i) - 'a'];
            ++cn2[s2.charAt(i) - 'a'];
        }
        if (Arrays.equals(cn1,cn2)){
            return true;
        }
        for (int i = n; i < m; i++){
            ++cn2[s2.charAt(i) - 'a'];
            --cn2[s2.charAt(i - n) - 'a'];
            if (Arrays.equals(cn1,cn2)){
                return true;
            }
        }
        return false;
    }

    public String minWindow(String s, String t) {
        int n = s.length();
        int m = t.length();
        if (n == 0 || m == 0 || m > n){
            return "";
        }
        char[] charArrayS = s.toCharArray();
        char[] charArrayT = t.toCharArray();

        int[] winFreq = new int[128];
        int[] tFreq = new int[128];
        for (char c : charArrayT) {
            tFreq[c]++;
        }

        int distance = 0;
        int minLen = n + 1;
        int begin = 0;

        int left = 0;
        int right = 0;
        while (right < n){
            if (tFreq[charArrayS[right]] == 0){
                right++;
                continue;
            }
            if (winFreq[charArrayS[right]] < tFreq[charArrayS[right]]){
                distance++;
            }
            winFreq[charArrayS[right]]++;
            right++;

            while (distance == m){

                if (right - left < minLen){
                    minLen = right - left;
                    begin = left;
                }

                if (tFreq[charArrayS[left]] == 0){
                    left++;
                    continue;
                }
                if (winFreq[charArrayS[left]] == tFreq[charArrayS[left]]){
                    distance--;
                }
                winFreq[charArrayS[left]]--;
                left++;
            }
        }
        if (minLen == n + 1){
            return "";
        }
        return s.substring(begin,begin + minLen);
    }

    public boolean isPowerOfTwo(int n) {
        if (n <= 0){
            return false;
        }
        //对n取余，如过余数不是0，则 n == 1 直接进行判断，返回true或者false
        //如过余数是0，则执行 n = n / 2;执行到最后再由 n == 1 进行判断，返回true或者false
        while (n % 2 == 0){
            n = n / 2;
        }
        return n == 1;
    }

    public boolean isPalindromeOne(ListNode head) {
        Deque<ListNode> stack = new ArrayDeque<>();
        boolean flag = false;
        while (head != null){
            if (!stack.contains(head)){
                stack.push(head);
            }else {
                while (!stack.isEmpty()){
                    int temp = stack.pop().val;
                    if (temp != head.val){
                        return false;
                    }
                    head = head.next;
                }
                flag = true;
            }
            head = head.next;
        }
        if (flag == false){
            return false;
        }
        return true;
    }

    public boolean isPalindromeOld(ListNode head) {
        StringBuilder s = new StringBuilder("");
        while (head != null){
            s.append(head.val);
            head = head.next;
        }
        return s.toString().equals(s.reverse().toString());
    }

    public boolean isPalindrome(ListNode head) {
        List<Integer> nodes = new ArrayList<>();
        while (head != null){
            nodes.add(head.val);
            head = head.next;
        }
        int end = nodes.size() - 1;
        for (int i = 0; i < nodes.size() / 2; i++){
            if (!Objects.equals(nodes.get(i), nodes.get(end))){
                return false;
            }
            end--;
        }
        return true;
    }

    public boolean isPowerOfThree(int n) {
        if (n <= 0){
            return false;
        }
        while (n % 3 == 0){
            n = n / 3;
        }
        return n == 1;
    }

    public boolean isPowerOfFour(int n) {
        if (n <= 0){
            return false;
        }
        while (n % 4 == 0){
            n = n / 4;
        }
        return n == 1;
    }

    public int fib(int n) {
        if (n == 0){
            return 0;
        }
        if (n == 1){
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public int[] reversePrint(ListNode head) {
        List<Integer> temp = new ArrayList<>();
        while (head != null){
            temp.add(head.val);
            head = head.next;
        }
        int[] res = new int[temp.size()];
        int flag = 0;
        for (int i = temp.size() - 1; i >= 0; i--){
            res[flag] = temp.get(i);
            flag++;
        }
        return res;
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode dummy = res;
        while (l1 != null && l2 != null){
            if (l1.val > l2.val){
                res.next = l2;
                l2 = l2.next;
                res = res.next;
            }else {
                res.next = l1;
                l1 = l1.next;
                res = res.next;
            }
        }
        if (l2 != null){
            res.next = l2;
        }
        if (l1 != null){
            res.next = l1;
        }
        return dummy.next;
    }

    public int lastRemaining(int n, int m) {
        /*官方解答就扔了个公式，还写错了(划掉，官解没错了，因为。。它改了)，然后就得结论了。我们现在来捋一捋这公式到底咋推出来的。

我们有n个数，下标从0到n-1，然后从index=0开始数，每次数m个数，最后看能剩下谁。我们假设能剩下的数的**下标**为y，则我们把这件事表示为

f(n,m) = y
这个y到底表示了啥呢？注意，y是下标，所以就意味着你从index=0开始数，数y+1个数，然后就停，停谁身上谁就是结果。

行了，我们假设f(n-1,m)=x，然后来找一找f(n,m)和f(n-1,m)到底啥关系。

f(n-1,m)=x意味着啥呢？意味着有n-1个数的时候从index=0开始数，数x+1个数你就找到这结果了。那我不从index=0开始数呢？比如我从index=i开始数？那
很简单，你把上面的答案也往后挪i下，就得到答案了。当然了，你要是挪到末尾了你就取个余，从头接着挪。

于是我们来思考f(n,m)时考虑以下两件事：

有n个数的时候，要划掉一个数，然后就剩n-1个数了呗，那划掉的这个数，**下标**是多少？
划完了这个数，往后数，数x+1个数，停在谁身上谁就是我们的答案。当然了，数的过程中你得取余
**问题一**：有n个数的时候，划掉了谁？**下标**是多少？

因为要从0数m个数，那最后肯定落到了下标为m-1的数身上了，但这个下标可能超过我们有的最大下标（n-1）了。所以攒满n个就归零接着数，逢n归零，所以要模n。

所以有n个数的时候，我们划掉了下标为(m-1)%n的数字。

**问题二**：我们划完了这个数，往后数x+1下，能落到谁身上呢，它的下标是几？

你往后数x+1，它下标肯定变成了(m-1)%n +x+1，和第一步的想法一样，你肯定还是得取模，所以答案为[(m-1)%n+x+1]%n，则

f(n,m)=[(m-1)%n+x+1]%n
其中x=f(n-1,m)

我们化简它！

定理一：两个正整数a，b的和，模另外一个数c，就等于它俩分别模c，模完之后加起来再模。

(a+b)%c=((a%c)+(b%c))%c
定理二：一个正整数a，模c，模一遍和模两遍是一样的。

a%c=(a%c)%c
你稍微一琢磨就觉得，嗯，说得对。

所以

f(n,m)=[(m-1)%n+x+1]%n
      =[(m-1)%n%n+(x+1)%n]%n
      =[(m-1)%n+(x+1)%n]%n
      =(m-1+x+1)%n
      =(m+x)%n
剩下的故事你们就都知道了。*/
        return f(n, m);
    }

    public int f(int n, int m) {
        if (n == 1) {
            return 0;
        }
        int x = f(n - 1, m);
        return (m + x) % n;
    }

    public ListNode reverseListTwo(ListNode head) {
        if (head == null){
            return head;
        }
        ListNode res = new ListNode(0);
        ListNode dummy = res;
        Deque<ListNode> stack = new ArrayDeque<>();
        while (head != null){
            stack.push(head);
            head = head.next;
        }
        while (stack.size() != 1){
            res.next = stack.pop();
            res = res.next;
        }
        /*Error - Found cycle in the ListNode
        刷力扣时遇到这个错误，节点成环
        自己摸索了一下发现确实形成循环，原题是206反转链表，我用的是栈，先将链表节点依次进栈，然后依次出栈链接，构成反转。但是我忽略了第一个进栈
        的节点ListNode1，它作为新链表的尾，next指针本应该是null，但是它原来是指向ListNode2的，如果不人为置空，就会发生新链表最后一个节点指
        向倒数第二个节点，以此类推，出现环。*/
        res.next = stack.pop();
        res = res.next;
        res.next = null;
        return dummy.next;
    }

    public boolean isPalindromeTwo(ListNode head) {
        if (head == null){
            return false;
        }
        StringBuilder aaa = new StringBuilder();
        while (head != null){
            aaa.append(head.val);
            head = head.next;
        }
        return aaa.toString().equals(aaa.reverse().toString());
    }


    public List<String> alertNames(String[] keyName, String[] keyTime){
        int n = keyName.length;
        List<String> ans = new ArrayList<>();
        Map<String,List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++){
            String name = keyName[i];
            String time = keyTime[i];
            int minutes = timeTOMinutes(time);
            map.computeIfAbsent(name,key -> new ArrayList<>()).add(minutes);
        }
        for (String name : map.keySet()) {
            List<Integer> list = map.get(name);
            int size = list.size();
            if (size < 3){
                continue;
            }
            Collections.sort(list);
            for (int i = 2; i < size; i++){
                if (list.get(i) - list.get(i - 2) <= 60){
                    ans.add(name);
                    break;
                }
            }
        }
        Collections.sort(ans);
        return ans;
    }

    private int timeTOMinutes(String time) {
        char[] cs = time.toCharArray();
        int hour = (cs[0] - '0') * 10 + (cs[1] - '0');
        int minutes = (cs[3] - '0') * 10 + (cs[4] - '0');
        return hour * 60 + minutes;
    }


}