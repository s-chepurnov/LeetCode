package com.leetcode;

import java.util.LinkedList;
import java.util.Stack;

class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
//        String answer = solution.solve(1);
//        System.out.println(answer);

        TreeNode n0 = new TreeNode(3);
        TreeNode n1 = new TreeNode(20);
        TreeNode n2 = new TreeNode(9);
        TreeNode n3 = new TreeNode(15, n2,n1);
        TreeNode n4 = new TreeNode(7, n0,n3);

        BSTIterator obj = new BSTIterator(n4);

        while(obj.hasNext()) {
            int param_1 = obj.next();
            System.out.println(param_1);
        }
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class BSTIterator {
    TreeNode root;
    Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        this.root = root;
        stack = new Stack<>();
        getLeftMostNode(root);
    }

    public int next() {
        TreeNode x = stack.pop();
        if(x.right != null) {
            getLeftMostNode(x.right);
        }
        return x.val;
    }

    public boolean hasNext() {
        return !stack.empty();
    }

    public void getLeftMostNode(TreeNode x) {
        if(x == null) return;

        while(x.left!=null) {
            stack.push(x);
            x = x.left;
        }
        stack.push(x);
    }
}
