package test02;

public class Demo3 {
    //超出时间限制
    /*public static void main(String[] args) {
        //int[] nums = {-2,11,-4,13,-5,-2};
        int[] nums = {7,5,6,4};
        reverse(nums);
    }

    private static void reverse(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            for (int j = i+1; j < nums.length; j++){
                if (nums[i] > nums[j]){
                    sum++;
                }
            }
        }
        System.out.println(sum);
    }*/

    public static void main(String[] args) {
        int[] nums = {-2,11,-4,13,-5,-2};
        System.out.println(reverse(nums));
    }

    private static int reverse(int[] nums) {
        int n_len = nums.length;;
        if (n_len < 2){
            return 0;
        }
        int[] tmp = new int[n_len];
        return mergeSort(nums,0,n_len-1,tmp);
    }

    private static int mergeSort(int[] nums, int left, int right, int[] tmp) {
        //只有一个元素 没有逆序对
        if (left == right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        //求出左边数组的逆序对
        int leftPairs = mergeSort(nums, left, mid, tmp);
        //求出右边数组的逆序对
        int rightParis = mergeSort(nums, mid + 1, right, tmp);
        if (nums[mid] <= nums[mid + 1]) {
            return leftPairs + rightParis;
        }
        int crossPairs = mergeAndCount(nums, left, mid, right, tmp);
        return leftPairs + rightParis + crossPairs;
    }

    private static int mergeAndCount(int[] nums, int left, int mid, int right, int[] tmp) {
        for (int i = left; i <= right; i++) {
            tmp[i] = nums[i];
        }
        int i = left;
        int j = mid + 1;
        int count = 0;
        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                //左数组已经全部遍历完毕
                nums[k] = tmp[j];
                j++;
            } else if (j == right + 1) {
                //右数组已经全部遍历完毕
                nums[k] = tmp[i];
                i++;
            } else if (tmp[i] <= tmp[j]) {
                //左节点小于等于右节点
                nums[k] = tmp[i];
                i++;
            } else {
                //左节点大于右节点 会贡献逆序对
                nums[k] = tmp[j];
                j++;
                count += (mid - i + 1);
            }
        }
        return count;
    }

}
