package leetcode;

import javax.swing.plaf.IconUIResource;

/**
 * @author 杨梓鹏
 */
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}



public class MyLinkedList {

    ListNode head;

    public MyLinkedList() {
        head = null;
    }

    public int get(int index) {
        int count = 1;
        ListNode res = head;
        while (res != null){
            if (count == index){
                return res.val;
            }
            res = res.next;
            count++;
        }
        return -1;
    }

    public void addAtHead(int val) {
        ListNode res = new ListNode(val);
        res.next = head;
    }

    public void addAtTail(int val) {
        while (head != null){
            if (head.next == null){
                ListNode res = new ListNode(val);
                head.next = res;
            }
            head = head.next;
        }
    }

    public void addAtIndex(int index, int val) {
        if (index < 0){
            addAtHead(val);
        }
        int count = 1;
        while (head != null){
            int count1 = count + 1;
            ListNode start = head.next;
            ListNode temp = new ListNode(val);
            if (count1 == index){
                head.next = temp;
                temp.next = start;
            }
            head = head.next;
            count++;
        }
    }

    public void deleteAtIndex(int index) {
        int count = 1;
        while (head != null){
            ListNode right = head.next;
            int count1 = count + 1;
            if (count1 == index){
                head.next = right.next;
            }
            head = head.next;
        }
    }
}
