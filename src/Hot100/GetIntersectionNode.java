package Hot100;

import BaseClass.ListNode;

import java.util.HashSet;
import java.util.Set;

public class GetIntersectionNode {

    /**
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode a = headA;
        ListNode b = headB;
        while (a!=b) {
            if (a!=null)
                a = a.next;
            else
                a = headB;

            if (b!=null)
                b = b.next;
            else
                b = headA;
        }
        return headA;
    }

    /**
     * O(M+N)的时间复杂度  O(n)的空间复杂度
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        Set<ListNode> set = new HashSet<>();
        ListNode a = headA;
        ListNode b = headB;
        while(a!=null) {
            set.add(a);
            a = a.next;
        }
        while (b!=null) {
            if (set.contains(b)) return b;
            b = b.next;
        }
        return null;
    }

}
