package test02;

import java.util.Arrays;
import java.util.Scanner;

public class Demo4 {
    //设b[0:n-1]为数组，数组中含有n个数，参照课本2.7，试设计一个消去递归的合并排序算法。
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Comparable[] a = {4,8,3,7,1,5,6,2};
        mergeSort(a);
        System.out.println(Arrays.toString(a));
    }

    private static void mergeSort(Comparable[] a) {
        Comparable[] b = new Comparable[a.length];
        int s = 1;
        while (s < a.length){
            mergePass(a,b,s);
            s+=s;
            mergePass(b,a,s);
            s+=s;
        }
    }

    private static void mergePass(Comparable[] x, Comparable[] y, int s) {
        int i = 0;
        while (i <= x.length-2*s){
            merge(x, y, i, i + s - 1, i + 2 * s - 1);
            i = i+2*s;
        }
        if (i+s < x.length){
            merge(x,y,i,i+s-1,x.length-1);
        }else{
            for (int j = i; j < x.length; j++){
                y[j] = x[j];
            }
        }
    }
    private static void merge(Comparable[] c, Comparable[] d, int l, int m, int r) {
        int i = l,j = m + 1,k = l;
        while ((i <= m) && (j <=r)){
            if (c[i].compareTo(c[j])<=0){
                d[k++] = c[i++];
            }else {
                d[k++] = c[j++];
            }
        }
        if (i > m){
            for (int q = j; q <= r; q++){
                d[k++] = c[q];
            }
        }else {
            for (int q = i; q <= m;q++){
                d[k++] = c[q];
            }
        }
    }
}
