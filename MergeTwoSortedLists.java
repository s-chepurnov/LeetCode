package com.amazon.oa;

public class MergeTwoSortedLists {

    public static void main(String[] args) {

        SolutionMergeTwoSortedLists sl = new SolutionMergeTwoSortedLists();
        //no test data here
        //test it on LeetCode:
        //https://leetcode.com/problems/merge-two-sorted-lists/

    }
}

class SolutionMergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if(l1 == null && l2 == null) {
            return null;
        }

        //define a new head
        ListNode head = null;

        if(l1 != null && l2 != null) {

            if (l1.val <= l2.val) {
                head = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                head = new ListNode(l2.val);
                l2 = l2.next;
            }

        } else if( l1 != null && l2 == null ) {

            head = new ListNode(l1.val);
            l1 = l1.next;

        } else if ( l1 == null && l2 != null ) {

            head = new ListNode(l2.val);
            l2 = l2.next;

        }

        //merge 2 lists to one list with new head
        solve(l1, l2, head);

        return head;
    }

    public void solve(ListNode l1, ListNode l2, ListNode target) {

        //copy from both lists
        while (l1 != null && l2 != null) {

            if (l1.val <= l2.val) {
                target.next = new ListNode(l1.val);

                target = target.next;
                l1 = l1.next;
            } else {
                target.next = new ListNode(l2.val);

                target = target.next;
                l2 = l2.next;
            }

        }

        //copy the rest of l1
        if( l1 != null && l2 == null ) {

            while (l1 != null) {
                target.next = new ListNode(l1.val);

                target = target.next;
                l1 = l1.next;
            }

        //copy the rest of l2
        } else if ( l1 == null && l2 != null ) {

            while (l2 != null) {
                target.next = new ListNode(l2.val);

                target = target.next;
                l2 = l2.next;
            }
        }
    }


}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
