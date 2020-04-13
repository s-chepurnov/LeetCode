package com.amazon.oa;

public class SubtreeofAnotherTree {

    public static void main(String[] args) {

        //Expected: true
        //Tree [3,4,5,1,2]
        //SubTree [4,1,2]

        TreeNode node0 = new TreeNode(0);

        TreeNode node1 = new TreeNode(1);
        node1.left = node0;

        TreeNode node2 = new TreeNode(2);

        TreeNode node4 = new TreeNode(4);
        node4.left = node1;
        node4.right = node2;

        TreeNode node5 = new TreeNode(5);

        TreeNode root = new TreeNode(3);
        root.left = node4;
        root.right = node5;

        //subtree
        TreeNode snode1 = new TreeNode(1);
        TreeNode snode2 = new TreeNode(2);
        TreeNode sroot = new TreeNode(4);

        sroot.left = snode1;
        sroot.right = snode2;

        SolutionSubtreeofAnotherTree sl = new SolutionSubtreeofAnotherTree();
        boolean isSubtree = sl.isSubtree(root, sroot);

        System.out.println("# " + isSubtree);
    }

}

class SolutionSubtreeofAnotherTree {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null)
            return false;

        return equal(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean equal(TreeNode s, TreeNode t) {

        if (s == null && t == null)
            return true;

        if (s == null || t == null)
            return false;

        return s.val == t.val && equal(s.left, t.left) && equal(s.right, t.right);
    }

}

class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;
    TreeNode(int x) { val = x; }

}
