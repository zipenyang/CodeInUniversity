package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyHashMap {

    List<int []> res;
    int[] mid;

    public MyHashMap() {
        res = new ArrayList<>();
        mid = new int[2];
    }

    public void put(int key, int value) {
        mid[0] = key;
        mid[1] = value;
        int[] temp = new int[2];
        int i = 0;
        for (; i < res.size(); i++){
            temp = res.get(i);
            if (temp[0] == key){
                res.remove(i);
                res.add(mid);
            }
        }
        if (temp[0] != key){
            res.add(mid);
        }
    }

    public int get(int key) {
        int result = -1;
        int i = 0;
        int[] temp = new int[2];
        for ( ; i < res.size(); i++){
            temp = res.get(i);
            if (temp[0] == key){
               result = temp[1];
            }
        }
        return result;
    }

    public void remove(int key) {
        for (int i = 0; i < res.size(); i++){
            if (i == key){
                res.remove(i);
            }
        }
    }
}
