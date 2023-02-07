package test05;

public class Demo1 {
    public static void main(String[] args) {
        int a = 5, c = 8;
        int[] v = {0,2,1,4,3,5};
        int[] w = {0,1,4,2,3,5};
        int[][] m = new int[a+1][c+1];
        int[] x = new int[a+1];
        knapsack(v,w,c,m);
        traceback(m,w,c,x);
    }

    private static void knapsack(int[] v, int[] w, int c, int[][] m) {
        int n = v.length - 1;
        int jMax = Math.min(w[n]-1,c);
        for (int j = 0; j <= jMax; j++){
            m[n][j] = 0;
        }
        for (int j = w[n]; j <= c; j++){
            m[n][j] = v[n];
        }
        for (int i = n - 1; i > 1; i--){
            jMax = Math.min(w[i] - 1,c);
            for (int j = 0; j <= jMax; j++){
                m[i][j] = m[i+1][j];
            }
            for (int j = w[i]; j <= c; j++){
                m[i][j] = Math.max(m[i+1][j],m[i+1][j-w[i]]+v[i]);
            }
        }
        m[1][c] = m[2][c];
        if (c >= w[1]){
            m[1][c] = Math.max(m[1][c],m[2][c-w[1]]+v[1]);
        }
        System.out.println("m[i][j]矩阵：");
        for(int i = 0; i < 5; i++){
            for (int j = 0; j < 9; j++){
                System.out.print(m[i][j]+" ");
            }
            System.out.println();
        }
        System.out.print("装入背包中物品总价值最大为： ");
        System.out.println(m[1][c]);
    }

    private static void traceback(int[][] m, int[] w, int c, int[] x) {
        System.out.println("装入的物品的序号为：");
        int n = w.length - 1;
        for (int i = 1; i < n; i++){
            if(m[i][c] == m[i+1][c]){
                x[i] = 0;
            }else{
                x[i] = 1;
                c = c - w[i];
            }
        }
        x[n] = (m[n][c]>0)? 1 : 0;
        for (int i = 1; i <= n; i++){
            if (x[i] == 1){
                System.out.print(i+" ");
            }
        }
    }
}
