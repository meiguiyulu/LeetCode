package Hot100;

import BaseClass.ListNode;

public class ReverseList {

    /**
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode current = head;
        ListNode prev = null;
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    public ListNode reverseList2(ListNode head) {
        return recur(head, null);
    }

    private ListNode recur(ListNode current, ListNode prev) {
        if (current == null) return prev;
        ListNode next = recur(current.next, current);
        current.next = prev;
        return next;
    }

}
