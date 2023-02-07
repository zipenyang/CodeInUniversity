package leetcode;

import java.util.ArrayList;

public class ReverseList {
      public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }
    public static ListNode reverseList(ListNode head) {
          ListNode list = new ListNode();
          ListNode temp = list;
          ArrayList<Integer> mid = new ArrayList<>();
        if (head == null){
           return null;
        }
        while (head != null){
           mid.add(head.val);
           head = head.next;
        }
        for (int i = mid.size() - 1; i >= 0; i--){
            ListNode node = new ListNode(mid.get(i));
            if (i == mid.size() - 1){
                temp.val = mid.get(i);
                continue;
            }
            temp.next = node;
            temp = node;
        }
        return list;
      }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode head1 = new ListNode(2);
        ListNode head2 = new ListNode(3);
        ListNode head3 = new ListNode(4);
        ListNode head4 = new ListNode(5);
        head.next = head1;
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        reverseList(head);

    }
}
