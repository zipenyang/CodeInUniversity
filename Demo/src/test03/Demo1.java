package test03;
import java.util.Scanner;
public class Demo1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int[] arr = new int[num+1];
        for (int i = 0; i <= num; i++){
            arr[i] = scanner.nextInt();
        }
        int[][] m = new int[num+1][num+1];
        int[][] s = new int[num+1][num+1];
        int res = matrixChain(arr,m,s);
        System.out.println(res);
        traceback(s,1,num);
    }
    public static int matrixChain(int[] arr,int[][] m,int[][] s){
        int n = arr.length-1;
        for (int i = 1; i <= n; i++){
            m[i][i] = 0;
        }
        for (int r = 2; r <= n; r++){
            for (int i = 1; i <= n-r+1; i++){
                int j = i + r - 1;
                m[i][j] = m[i+1][j] + arr[i-1] * arr[i] * arr[j];
                s[i][j] = i;
                for (int k = i+1; k < j; k++){
                    int t = m[i][k] + m[k+1][j] + arr[i-1] * arr[k] * arr[j];
                    if (t < m[i][j]){
                        m[i][j] = t;
                        s[i][j] = k;
                    }
                }
            }
        }
        return m[1][n];
    }
    public static void traceback(int[][] s,int i,int j){
        if (i == j){
            return;
        }else {
            traceback(s,i,s[i][j]);
            traceback(s,s[i][j]+1,j);
            System.out.println("Multiply A"+i+","+s[i][j]+"and A"+(s[i][j]+1)+","+j);
        }
    }
}
