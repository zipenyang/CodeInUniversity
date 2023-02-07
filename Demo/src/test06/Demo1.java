package test06;

public class Demo1 {
    static class Element {
        float v_w;
        float w;
        float v;
        float index;

        public Element(float vw, float ww, float vv, float ii) {
            this.v_w = vw;
            this.w = ww;
            this.v = vv;
            this.index = ii;
        }
    }
    public static float knapsack(float[] v, float[] w, float c, float[] x) {
        int n = v.length;
        Element[] d = new Element[n];
        for (int i = 0; i < n; i++) {
            d[i] = new Element(v[i] / w[i], w[i], v[i], i);
        }
        MergeSort(d);
        int i;
        float opt = 0;
        for (i = 0; i < n; i++) {x[i] = 0;}
        for (i = 0; i < n; i++) {
            if (d[i].w > c) {break;}
            x[i] = 1;
            opt += d[i].v;
            c -= d[i].w;
        }
        if (i < n) {
            x[i] = c / d[i].w;
            opt += d[i].v * x[i];
        }
        return opt;
    }

    public static void MergeSort(Element[] d) {
        int n = d.length;
        Element temp;
        for (int i = 0; i < n - 1; i++) {
            boolean res = true;
            for (int j = 1; j < n - i; j++) {
                if (d[j - 1].v_w < d[j].v_w) {
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
        int n = 3;
        float[] w = {10, 20, 30};
        float[] v = {60, 100, 120};
        float c = 50;
        float[] x = new float[v.length];
        float v_max = knapsack(v, w, c, x);
        System.out.println("装载物品价值最大为：" + v_max);
        System.out.println("依次装入背包的物品为:");
        for (int i =0; i < n; i++){
            System.out.println("第"+(i+1)+"个物品:"+x[i]+"个");
        }
    }
}
