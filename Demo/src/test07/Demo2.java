package test07;

import java.util.LinkedList;
import java.util.Queue;

public class Demo2 {
    private static void enQueue(int wt, int i, QNode parent, boolean leftchild){
        if(i == n) {
            if(wt == bestw) {
                bestE = parent;
                bestx[n] = (leftchild)?1:0;
                for(int j = n-1; j >0; j--) {
                    bestx[j] = (bestE.leftChild)?1:0;
                    bestE = bestE.parent;
                }
            }
            return;
        }
        QNode b = new QNode(parent, leftchild, wt);
        queue.offer(b);

    }

    static int n;
    static int bestw;
    static Queue<QNode> queue;
    static QNode bestE;
    static int[] bestx;
    static int[] w;

    public static int maxLoading(int[] w, int c, int[] xx){
        queue = new LinkedList<QNode>();
        bestw = 0;
        queue.offer(null);
        QNode e = new QNode(null, true, 0);
        bestE = e;
        bestx = xx;
        int i = 1;
        int ew = 0;
        int r = 0;
        for(int j = 2; j <= n; j++)
            r += w[j];

        while(true){

            int wt = ew + w[i];
            if(wt <= c) {
                if(wt > bestw) {
                    bestw = wt;

                }
                enQueue(wt, i, e, true);
            }
            if(ew+r >= bestw)
                enQueue(ew, i, e, false);
            e =(QNode) queue.poll();
            if(e == null) {
                if(queue.isEmpty())
                    break;
                queue.offer(null);
                e = (QNode) queue.poll();
                i++;
                r-=w[i];
            }
            ew = e.weight;
        }
        System.out.println("最优解为：");
        for(int k = 1; k <= n; k++) {
            System.out.print(bestx[k] + " ");
        }
        System.out.println();
        return bestw;
    }

    public static void main(String[] args) throws Exception {
        int c = 70;
        int flag = -1;
        int[] ww= {flag, 20,10,26,15};
        w = ww;
        n = w.length-1;
        bestx = new int[w.length];
        int value = maxLoading(w, c, bestx);
        System.out.println("可装载的最大重量为：" + value);

    }
}

class QNode {
    QNode parent;
    boolean leftChild;
    int weight;

    QNode(QNode theParent, boolean theLeftChild, int theWeight) {
        this.parent = theParent;
        this.leftChild = theLeftChild;
        this.weight = theWeight;
    }
}
