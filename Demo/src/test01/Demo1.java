package test01;

import java.util.Scanner;

public class Demo1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(factorial(n));
    }
    private static int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        if(n > 0) {
            return n*factorial(n-1);
        }
        return 0;
    }
}
