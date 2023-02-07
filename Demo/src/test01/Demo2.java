package test01;

import java.util.Scanner;

public class Demo2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println("f("+n+") = "+fibonacci(n-1));
    }

    private static int fibonacci(int n) {
        if (n <= 1) {return 1;}
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
