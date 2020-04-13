package com.amazon.oa;

import sun.reflect.generics.tree.Tree;

//https://aonecode.com/split-bst
public class CutBST {

    public static void main(String[] args) {

/*
 * Input:

         50

    /         \

   20          60

 /   \         /  \
 10   30      55   70

 k = 50


 output:

       20

     /   \

    10   30

     and

       60

     /   \

    55   70

 */

        TreeNode node0 = new TreeNode(50);
        TreeNode node1 = new TreeNode(20);
        TreeNode node2 = new TreeNode(60);
        TreeNode node3 = new TreeNode(10);
        TreeNode node4 = new TreeNode(30);
        TreeNode node5 = new TreeNode(55);
        TreeNode node6 = new TreeNode(70);

        node0.left = node1;
        node0.right = node2;

        node1.left = node3;
        node1.right = node4;

        node2.left = node5;
        node2.right = node6;

        int k = 50;

        SolutionCutBST sl = new SolutionCutBST();
        sl.cutBST(node0, k);
    }


}

class SolutionCutBST {

    public TreeNode[] cutBST(TreeNode root, int k) {
        //cut tree into two substrees A and B, where all nodes in A <= k and all nodes in B > k.

        //find the node that will split the tree into 2 subtrees
        find(root, k);


        return null;
    }

    public TreeNode find(TreeNode x, int k) {

        if (x == null) return null;

        find(x.left, k);
        find(x.right, k);

        return x;
    }

}
/*
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
*/