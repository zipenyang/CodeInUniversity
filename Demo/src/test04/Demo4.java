package test04;

import java.util.ArrayList;

public class Demo4 {
    public static void main(String[] args) {
        int[] c = {8,7,4,2,5,1,9,3,10,6};
        ArrayList<Integer> net = new ArrayList<Integer>();
        int n = c.length;
        int[][]size=new int[n+1][n+1];
        mnset(c,size,n);
        traceback(c,size,net,n);
    }

    private static void traceback(int[] c, int[][] size, ArrayList<Integer> net, int n) {
        int j=n;
        for(int i=n;i>1;i--){
            if(size[i][j]!=size[i-1][j]){
                net.add(i);
                j=c[i-1]-1;
            }
        }
        if(j>=c[0])//处理i=1的情形
        {
            net.add(1);
        }
        int num=net.size();
        System.out.println("最大不相交连线分别为：");
        for(int i=net.size()-1;i>=0;i--){
            int a=net.get(i);
            System.out.println(c+"  "+c[a-1]);
        }
        System.out.println("最大不相交连线数目为："+num);
    }

    private static void mnset(int[] c, int[][] size, int n) {
        for(int j=0;j<c[0];j++){
            size[1][j]=0;
        }
        for(int j=c[1];j<=n;j++){
            size[1][j]=1;
        }
        for(int i=2;i<n;i++){
            for(int j=0;j<c[i-1];j++) {
                size[i][j] = size[i - 1][j];
            }
            for(int j=c[i-1];j<=n;j++){
                size[i][j]=Math.max(size[i-1][j], size[i-1][c[i-1]-1]+1);
            }
        }
        size[n][n]=Math.max(size[n-1][n], size[n-1][c[n-1]-1]+1);
    }
}
