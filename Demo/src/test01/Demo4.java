package test01;

import java.util.Scanner;

public class Demo4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int x = scanner.nextInt();
        int y = binarySearch(arr, x, n);
        if (y == -1){
            for (int i = 0; i < n; i++) {
                while (arr[i] < x && arr[i+1] > x) {
                    System.out.println("leftmax="+i+","+"rightmin="+(i+1));
                    break;
                }
            }
        }else{
            System.out.println(y);
        }
    }
    public static int binarySearch(int[] arr, int x, int n) {
        int left = 0;
        int right = n-1;
        while (left <= right){
            int mid = (left + right) / 2;
            if(arr[mid] == x){
                return mid;
            }
            if (arr[mid] < x){
                left = mid+1;
            }else {
                right = mid-1;
            }
        }
        return -1;
    }
}
