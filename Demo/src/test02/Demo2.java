package test02;
public class Demo2 {
    public static void main(String[] args) {
        int[] nums = {-2,11,-4,13,-5,-2};
        System.out.println(maxSubArray(nums));
    }
    public static int maxSubArray(int[] nums) {
        int[] arr = new int[nums.length];
        //count记录数组中负整数的值，最后进行验证
        int count = 0;
        if (nums[0] < 0){
            count = count + 1;
        }
        arr[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0){
                count = count + 1;
            }
            if (arr[i - 1] > 0) {
                arr[i] = arr[i - 1] + nums[i];
            } else {
                arr[i] = nums[i];
            }
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        if (count == nums.length){
            max = 0;
        }
        return max;
    }
}
