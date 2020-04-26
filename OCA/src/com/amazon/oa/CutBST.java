package com.amazon.oa;

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
        */

        /*
        TreeNode node0 = new TreeNode(5);
        int k = 4;
        */


        TreeNode node0 = new TreeNode(10);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(12);

        TreeNode node3 = new TreeNode(0);
        TreeNode node4 = new TreeNode(5);

        TreeNode node5 = new TreeNode(11);
        TreeNode node6 = new TreeNode(16);

        node0.left = node1;
        node0.right = node2;

        node1.left = node3;
        node1.right = node4;

        node2.left = node5;
        node2.right = node6;
        int k = 7;


        SolutionCutBST sl = new SolutionCutBST();
        TreeNode[] array = sl.cutBST(node0, k);

        //print splitted tree
        System.out.println("left");
        print(array[0]);
        System.out.println("right");
        print(array[1]);
    }

    public static void print(TreeNode node) {
        if (node == null) return;
        System.out.println(node.val);
        print(node.left);
        print(node.right);
    }

}

class SolutionCutBST {

    //accepted O(n) time, O(h) memory
    public TreeNode[] cutBST(TreeNode root, int k) {
        int V = k;
        TreeNode[] res = new TreeNode[]{null, null};
        if (root == null) {
            return res;
        }
        if (root.val <= V) {
            res = cutBST(root.right, V);
            root.right = res[0];
            res[0] = root;
        } else {
            res = cutBST(root.left, V);
            root.left = res[1];
            res[1] = root;
        }
        return res;
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

/*
    def splitBST(self, root, V):
        """
        :type root: TreeNode
        :type V: int
        :rtype: List[TreeNode]
        """
        if not root: return [None, None]
        if root.val > V:
            left, right = self.splitBST(root.left, V)
            root.left = right
            return [left, root]

        left, right = self.splitBST(root.right, V)
        root.right = left
        return [root, right]
*/