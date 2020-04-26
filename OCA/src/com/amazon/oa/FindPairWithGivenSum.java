package com.amazon.oa;

import java.util.*;

// see the similar task "Moies on flight", but here we should return indices
// SolutionMoviesOnFlight.solve() returns values

public class FindPairWithGivenSum {
    public static void main(String[] args) {

        //Output: [2, 3]
//        int[] nums = {1, 10, 25, 35, 60};
//        int target = 90;

        //Output:  [0, 1]
        int[] nums = {2, 7, 11, 15};
        int target = 39;

        SolutionFindPairWithGivenSum sl = new SolutionFindPairWithGivenSum();
        int[] result = sl.solve(target, nums);

        System.out.println("# " + Arrays.toString(result));
    }
}

class SolutionFindPairWithGivenSum {

    public int[] solve(int target, int[] nums) {

        int[] arr = new int[2];
        arr[0] = -1;
        arr[1] = -1;

        int maxTarget = target - 30;
        int maxWithMinDifference = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; ++i) {
            for (int j = 0; j < nums.length; ++j) {

                //You cannot pick the same element twice.
                if(i == j) continue;

                int sum = nums[i]+nums[j];

                if (sum == maxTarget) {
                    arr[0] = i;
                    arr[1] = j;
                    return arr;
                } else if (sum < maxTarget && (maxTarget - sum) < maxWithMinDifference) {
                    arr[0] = i;
                    arr[1] = j;
                    maxWithMinDifference = (maxTarget - sum);
                } else {
                    continue;
                }

            }
        }

        if (arr[0] == -1 || arr[1] == -1) {
            return null;
            //return empty List?
        }

        return arr;
    }

}