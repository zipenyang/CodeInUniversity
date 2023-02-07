package leetcode;

import java.util.List;

public class mergeTwoListss {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null){
            return list2;
        }
        if (list2 == null){
            return list1;
        }
        if (list1.val < list2.val){
            list1.next = mergeTwoLists(list1.next,list2);
            return list1;
        }else {
            list2.next = mergeTwoLists(list2.next,list1);
            return list2;
        }
    }

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        ListNode list12 = new ListNode(2);
        ListNode list13 = new ListNode(4);
        list1.next = list12;
        list12.next = list13;

        ListNode list2 = new ListNode(1);
        ListNode list22 = new ListNode(3);
        ListNode list23 = new ListNode(4);
        list2.next = list22;
        list22.next = list23;

        System.out.println("合并后的链表：");
        ListNode res = mergeTwoLists(list1,list2);
        while (res != null){
            System.out.print(res.val+"  ");
            res = res.next;
        }
    }
}
