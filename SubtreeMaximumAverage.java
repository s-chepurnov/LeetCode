package org.algs4;

import java.util.ArrayList;
import java.util.List;

public class SubtreeMaximumAverage {
    public static void main(String[] args) {

        //bottom
        MAryTreeNode node1 = new MAryTreeNode();
        node1.val = 1;
        node1.children = null;

        MAryTreeNode node2 = new MAryTreeNode();
        node2.val = 2;
        node2.children = null;

        MAryTreeNode node3 = new MAryTreeNode();
        node3.val = 4;
        node3.children = null;

        MAryTreeNode node4 = new MAryTreeNode();
        node4.val = -2;
        node4.children = null;

        //middle
        List<MAryTreeNode> children5 = new ArrayList<>();
        children5.add(node1);
        children5.add(node2);
        MAryTreeNode node5 = new MAryTreeNode();
        node5.val = -5;
        node5.children = children5;

        List<MAryTreeNode> children6 = new ArrayList<>();
        children6.add(node3);
        children6.add(node4/*null*/);
        MAryTreeNode node6 = new MAryTreeNode();
        node6.val = 13;
        node6.children = children6;

        MAryTreeNode node7 = new MAryTreeNode();
        node7.val = 4;
        node7.children = null;

        //root
        List<MAryTreeNode> children8 = new ArrayList<>();
        children8.add(node5);
        children8.add(node6);
        children8.add(node7);
        MAryTreeNode node8 = new MAryTreeNode();
        node8.val = 1;
        node8.children = children8;


        SolutionSubtreeMaxAvg sl = new SolutionSubtreeMaxAvg();
        MAryTreeNode maxAverageRoot = sl.subtreeMaxAvg(node8);
        System.out.println("max average subtree root: " + maxAverageRoot.val);
    }
}

class SolutionSubtreeMaxAvg {

    public MAryTreeNode subtreeMaxAvg(MAryTreeNode root) {

        if (root == null) return null;

        Result result = recursive(root);
        //System.out.println("max average value: " + result.maxAvg);
        return result.maxRoot;
    }

    public Result recursive(MAryTreeNode x) {

        if (x == null) {
            return new Result(0,0, Integer.MIN_VALUE, null);
        }

        if (x.children == null || x.children.isEmpty()) {
            return new Result(x.val, 1, x.val, x);
        }

        int sum = x.val;
        int num = 1;
        List<Result> childrenResults = new ArrayList<>();
        for (MAryTreeNode node : x.children) {
            Result result = recursive(node);
            childrenResults.add(result);

            sum+=result.sum;
            num+=result.num;
        }

        //calculate current average value
        int currentAvg = sum/num;
        //System.out.println("current average: " + currentAvg);

        //find max average from children
        Result maxChildrenResult = childrenResults.stream().max((o1,o2) -> Integer.compare(o1.maxAvg,o2.maxAvg)).get();


        MAryTreeNode totalMaxRoot = null;
        int totalMaxAvg = 0;

        //total max average tree
        if (maxChildrenResult.maxAvg > currentAvg) {
            totalMaxRoot = maxChildrenResult.maxRoot;
            totalMaxAvg = maxChildrenResult.maxAvg;
        } else {
            totalMaxRoot = x;
            totalMaxAvg = currentAvg;
        }

        return new Result(sum, num, totalMaxAvg, totalMaxRoot);
    }

}

class Result {

    public int sum;
    public int num;

    public int maxAvg;
    public MAryTreeNode maxRoot;

    public Result(int sum, int num, int maxAvg, MAryTreeNode maxRoot) {
        this.sum = sum;
        this.num = num;
        this.maxAvg = maxAvg;
        this.maxRoot = maxRoot;
    }
}

class MAryTreeNode {
    int val;
    List<MAryTreeNode> children;
}