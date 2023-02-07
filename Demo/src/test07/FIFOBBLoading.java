package test07;

import java.util.Collections;
import java.util.LinkedList;

public class FIFOBBLoading {
    LinkedList<HeapNode> heap;

    public static class BBnode{
        BBnode parent;//父结点
        boolean leftChild;//左儿子结点标志
        //构造方法
        public BBnode(BBnode par,boolean ch){
            parent=par;
            leftChild=ch;
        }
    }
    public static class HeapNode implements Comparable{
        BBnode liveNode;
        int uweight;//活结点优先级（上界）
        int level;//活结点在子集树种所处的层序号
        public HeapNode(BBnode node,int up,int lev){
            liveNode=node;
            uweight=up;
            level=lev;
        }
        @Override
        public int compareTo(Object x) {//升序排列
            int xu=((HeapNode)x).uweight;
            if(uweight<xu) {return -1;}
            if(uweight==xu){return 0;}
            return 1;
        }
        public boolean equals(Object x){
            return uweight==((HeapNode)x).uweight;
        }
    }


    public void addLiveNode(int up,int lev,BBnode par,boolean ch){
        //将活结点加入到表示活结点优先队列的最大堆H中
        BBnode b=new BBnode(par,ch);
        HeapNode node=new HeapNode(b,up,lev);
        heap.add(node);
        Collections.sort(heap);
    }

    public int maxLoading(int[] w,int c,int[] bestx){
        //优先队列式分支限界法，返回最优重量，bestx返回最优解
        heap=new LinkedList<HeapNode>();
        int n=w.length-1;
        BBnode e=null;//当前扩展结点
        int i=1;//当前扩展结点所处的层
        int ew=0;//扩展结点所对应的载重量

        //定义剩余重量数组r
        int[] r=new int[n+1];
        for(int j=n-1;j>0;j--){
            r[j]=r[j+1]+w[j+1];
        }


        //搜索子集空间树
        while(i!=n+1){
            //非叶结点
            //检查当前扩展结点的儿子结点
            if(ew+w[i]<=c){
                //左儿子结点为可行结点
                addLiveNode(ew+w[i]+r[i],i+1,e,true);
            }
            //右儿子结点总为可行结点
            addLiveNode(ew+r[i],i+1,e,false);

            //取下一个结点
            HeapNode node=heap.pollLast();
            i=node.level;
            e=node.liveNode;
            ew=node.uweight-r[i-1];
        }
        for(int j=n;j>0;j--){
            bestx[j]=(e.leftChild)?1:0;
            System.out.print(bestx[j]+" ");
            e=e.parent;
        }
        System.out.println();
        return ew;
    }

    public static void main(String[] args) {
        int n=4;
        int c=70;
        int w[]={0,20,10,26,15};//下标从1开始
        int[] bestx=new int[n+1];
        FIFOBBLoading res = new FIFOBBLoading();
        System.out.println("最优装载顺序为（1表示装入，0表示未装入）：");
        int ew=res.maxLoading(w, c, bestx);
        System.out.println("最优装载重量为："+ew);
    }


}
