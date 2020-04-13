package com.amazon.oa;

import java.util.ArrayList;
import java.util.List;

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

        //int[] A = {4, 8, 4, 8, 4}; //true
        //int[] A = {1}; //false
        //int[] A = {1, 3, 4, 2, 2, 2, 1, 1, 2}; //true
        //int[] A = {1, 1, 1, 1, 1, 1}; //false
        //int[] A = {1, 1, 1, 1}; //false
        //int[] A = {2, 4, 5, 3, 3, 9, 2, 2, 2}; //true


        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; ++i) {
            list.add(1);
            list.add(2);
        }

        int[] A = list.stream().mapToInt(i->i).toArray(); //([1, 2] * 10000) // true

        boolean result = solution.loadBalance(A);
        System.out.println("isPossible: " + result);
    }
}

// N - size of array
// O(N) = N
// pivots from each side until they are meet each other
class SolutionLB {

    public boolean loadBalance(int[] arr) {

        if(arr.length < 5) // 8 1 8 1 8
            return false;

        int allSum = 0;
        for (int i = 0; i < arr.length; ++i) {
            allSum+=arr[i];
        }

        //init data
        int left = 0;
        int right = arr.length-1;

        int leftSum = arr[left];
        int rightSum = arr[right];

        //requests[left+1] - left pivot
        //requests[right-1] - right pivot
        while (left < right) {

            if (leftSum == rightSum) {
                int middleSum = allSum - leftSum - rightSum - arr[left+1] - arr[right-1];

                if (middleSum == leftSum && middleSum == rightSum && ((right - left) > 1)) {
                    return true;
                } else {
                    ++left;
                    --right;
                    leftSum+=arr[left];
                    rightSum+=arr[right];
                }

            } else if(leftSum < rightSum) {
                ++left;
                leftSum+=arr[left];
            } else {
                --right;
                rightSum+=arr[right];
            }
        }

        return false;
    }

}
