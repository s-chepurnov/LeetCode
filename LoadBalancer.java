package org.algs4;

/**
 * Write a function solution that, given an array A of N integers representing the time of execution of consecutive tasks,
 * returns true if it is possible for the load balancer to choose two requests that will determine an even distribution
 * of requests among three workers, or false otherwise.
 *
 * Examples:
 *
 * Given A = [1, 3, 4, 2, 2, 2, 1, 1, 2], the function should return true, as choosing requests 2 and 5 results
 * in a distribution giving 4 seconds of handling time to each worker.
 */
public class LoadBalancer {

    public static void main(String[] args) {
        SolutionLB solution = new SolutionLB();

        int[] A = {1, 3, 4, 2, 2, 2, 1, 1, 2}; //true
        //int[] A = {1, 1, 1, 1, 1, 1}; //false

        boolean result = solution.load_balance(A);
        System.out.println("isPossible: " + result);
    }

}

class SolutionLB {

    public boolean load_balance(int[] requests) {

        if(requests.length == 0)
            return false;

        int allSum = 0;
        for (int i = 0; i < requests.length; ++i) {
            allSum+=requests[i];
        }

        return recursive(requests, requests[0], 0, allSum, 1,requests.length-1);
    }


    public boolean recursive(int[] requests, int leftSum, int rightSum, int allSum, int left, int right) {

        int middleSum = allSum - leftSum - rightSum - requests[left] - requests[right];

        if (leftSum < rightSum) {
            leftSum+=requests[left];
            ++left;
        } else if(leftSum > rightSum) {
            rightSum+=requests[right];
            --right;
        } else if(leftSum == rightSum && leftSum == middleSum) {
            return true;
        } else if (left == right || leftSum < middleSum || rightSum > middleSum) {
            return false;
        }

        return recursive(requests, leftSum, rightSum, allSum, left, right);
    }


}