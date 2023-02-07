package test05;

public class Demo2 {
    public static void main(String[] args) {
        int a = 5, C = 8, D = 8;
        int[] v = {0,2,1,4,3,5};
        int[] w = {0,1,4,2,3,5};
        int[] b = {0,2,3,5,2,4};
        int[][][] m = new int[a+1][C+1][D+1];
        int[] x = new int[a+1];
        knapsack(v,w,C,m,D,b);
        traceback(m,w,C,x,D,b);
    }

    private static void knapsack(int[] v, int[] w, int C, int[][][] m, int D,int[] b) {
        int n = v.length - 1;
        int jMax = Math.min(w[n]-1,C);
        int kMax = Math.min(b[n],D);
        for (int j = 0; j <= jMax; j++){
            for (int k = 0; k <= kMax; k++){
                m[n][j][k] = 0;
            }
        }
        for (int j = w[n]; j <= C; j++){
            for (int k = b[n]; k <= D; k++){
                m[n][j][k] = v[n];
            }
        }
        for (int i = n - 1; i > 1; i--){
            jMax = Math.min(w[i] - 1,C);
            for (int j = 0; j <= jMax; j++){
                for (int k = 0; k <= kMax; k++){
                    m[i][j][k] = m[i+1][j][k];
                }
            }
            for (int j = w[i]; j <= C; j++){
                for (int k = b[i]; k <= D; k++){
                    m[i][j][k] = Math.max(m[i+1][j][k],m[i+1][j-w[i]][k-b[i]]+v[i]);
                }
            }
        }
        m[1][C][D] = m[2][C][D];
        if (C >= w[1] && D >= b[1]){
            m[1][C][D] = Math.max(m[1][C][D],m[2][C-w[1]][D-b[1]]+v[1]);
        }
        System.out.print("装入背包中物品总价值最大为： ");
        System.out.println(m[1][C][D]);
    }

    private static void traceback(int[][][] m, int[] w, int C, int[] x, int D,int[] b) {
        System.out.println("装入的物品的序号为：");
        int n = w.length - 1;
        for (int i = 1; i < n; i++){
            if(m[i][C][D] == m[i+1][C][D]){
                x[i] = 0;
            }else{
                x[i] = 1;
                C = C - w[i];
            }
        }
        x[n] = (m[n][C][D]>0)? 1 : 0;
        for (int i = 1; i <= n; i++){
            if (x[i] == 1){
                System.out.print(i+" ");
            }
        }
    }
}
