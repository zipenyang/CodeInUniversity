package test02;

import java.util.Scanner;

public class Demo1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] list = {"a","b","c"};
        int k = scanner.nextInt();
        int m = scanner.nextInt();
        perm(list,k,m);
    }

    private static void perm(String[] list, int k, int m) {
        if (k == m){
            for (int i = 0; i <= m; i++){
                System.out.print(list[i]+"    ");
            }
            System.out.println();
        }
        else {
            for (int i = k; i <= m; i++){
                Swap(list,k,i);
                perm(list,k+1,m);
                Swap(list,k,i);
            }
        }
    }

    private static void Swap(String[] list, int k, int i) {
        String a;
        a = list[k];
        list[k] = list[i];
        list[i] = a;
    }
}
