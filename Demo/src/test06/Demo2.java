package test06;

import java.util.Stack;

public class Demo2 {
    public static class Element implements Comparable{
        float w;
        int i;
        public Element(float ww,int ii){
            w = ww;
            i = ii;
        }

        @Override
        public int compareTo(Object x) {
            float xw = ((Element)x).w;
            if (w < xw){return -1;}
            if (w == xw){return 0;}
            return 1;
        }
    }

    public static float loading(float c,float[] w,int[] x){
        int n = w.length;
        Element[] d = new Element[n];
        for (int i = 0; i < n; i++){
            d[i] = new Element(w[i],i);
        }
        MergeSort(d);
        float opt = 0;
        for (int i = 0; i < n; i++) {x[i] = 0;}
        for (int i = 0; i < n && d[i].w <= c; i++){
            x[d[i].i] = 1;
            opt += d[i].w;
            c -= d[i].w;
        }
        return opt;
    }

    public static void MergeSort(Element[] d) {
        int n = d.length;
        Element temp;
        for (int i = 0; i < n - 1; i++) {
            boolean res = true;
            for (int j = 1; j < n - i; j++) {
                if (d[j - 1].w > d[j].w) {
                    temp = d[j];
                    d[j] = d[j - 1];
                    d[j - 1] = temp;
                    res = false;
                }
            }
            if (res) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int n = 4;
        float c = 5;
        float[] w = {2,3,1,1};
        int[] x = new int[n+1];
        float w_max = loading(c,w,x);
        System.out.println("最优装载重量为："+w_max);
        System.out.print("最优装载下被选中的集装箱序号为：");
        for (int i = 0; i < n; i++){
            if (x[i] == 1){
                System.out.print(i+" ");
            }
        }
    }


}
