package com.amazon;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/copy-list-with-random-pointer
 */

public class CopyListwithRandomPointer {

    public static void main(String[] args) {
        //Input:
        //{{7,null},{13,0},{11,4},{10,2},{1,0}};

        //Expected:
        //{{7,null},{13,0},{11,4},{10,2},{1,0}};

        //translate indices to values
        //{7,null}, {13,7}, {11,1}, {10,11}, {1,7}

        //hard coded input
        Node head = new Node(7);
        Node node1 = new Node(13);
        Node node2 = new Node(11);
        Node node3 = new Node(10);
        Node node4 = new Node(1);

        head.random = null;
        head.next = node1;
        node1.random = head;
        node1.next = node2;
        node2.random = node4;
        node2.next = node3;
        node3.random = node2;
        node3.next = node4;
        node4.random = head;
        node4.next = null;

        SolutionCopyListwithRandomPointer sl = new SolutionCopyListwithRandomPointer();
        Node copyList = sl.copyRandomList(head);

        //print result
        System.out.println("# Copy List with Random Pointer: ");

        while(copyList != null) {
            System.out.println(copyList.val + " " + ((copyList.random == null) ? "null" : copyList.random.val));
            copyList = copyList.next;
        }
    }
}

class SolutionCopyListwithRandomPointer {

    public Node copyRandomList(Node head) {

        if (head == null) {
            return null;
        }

        Map<Node, Node> link = new HashMap<>();

        Node newHead = new Node(head.val);

        Node origin = head;
        Node copy = newHead;

        //copy nodes
        while (origin != null) {

            if (origin.next != null) {
                copy.next = new Node(origin.next.val);
                copy = copy.next;
            }

            origin = origin.next;
        }

        //create link between nodes in origin and copy lists
        origin = head;
        copy = newHead;
        while (origin != null) {
            link.put(origin, copy);

            copy = copy.next;
            origin = origin.next;
        }

        //copy random pointers
        origin = head;
        copy = newHead;
        while (copy != null) {
            copy.random = getNodeFromCopyList(origin.random, link);
            copy = copy.next;

            origin = origin.next;
        }

        return newHead;
    }

    public Node getNodeFromCopyList(Node originNode, Map<Node, Node> link) {

        if (originNode == null) {
            return null;
        }

        Node copyNode = link.get(originNode);
        return copyNode;
    }

}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}