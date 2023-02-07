package test02;

import java.util.Arrays;

public class Demo5 {
    public static void main(String[] args) {
        int[] nums = {-2,11,-4,13,-5,-2};
        int[] res = new int[2];
        maxAndSec(nums,res,0,nums.length-1);
        System.out.println(Arrays.toString(res));
    }

    private static void maxAndSec(int[] nums, int[] res, int left, int right) {
        if (left == right){
            res[0] = res[1] = left;
        } else if (left == right-1) {
            if (nums[left] > nums[right]){
                res[1] = left;
                res[0] = right;
            }else {
                res[1] = right;
                res[0] = left;
            }
        }
        else {
            int mid = (right - left + 1)/2;
            for (int i = left; i < left+mid; i++){
                if (nums[i] > nums[i+mid]){
                    int temp = nums[i];
                    nums[i] = nums[i+mid];
                    nums[i+mid] = temp;
                }
            }
            maxAndSec(nums,res,left+mid,right);
            if (nums[res[1]-mid] > nums[res[0]]){
                res[0] = res[1] - mid;
            }
        }
    }
}
