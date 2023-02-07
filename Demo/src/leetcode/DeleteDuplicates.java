package leetcode;

public class DeleteDuplicates {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    public ListNode deleteDuplicates(ListNode head) {
        ListNode list = new ListNode();
        while (list != null && list.next != null){
            if (list.val == list.next.val){
                list.next = list.next.next;
            }else {
                list = list.next;
            }
        }
        return head;
    }
}
