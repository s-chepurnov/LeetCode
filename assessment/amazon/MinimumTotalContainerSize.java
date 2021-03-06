package com.amazon;

/*
 You want to move N items in k days (N >= k)
 You have to move at least one item per day
 Given array P as the item sizes, find out the minimum total container size required to move all the items...
*/

import java.util.Arrays;

public class MinimumTotalContainerSize {

    public static void main(String[] args) {

        int[] p = {10,2,20,5,15,10,1};
        int d = 3;

        SolutionMinimumTotalContainerSize solution = new SolutionMinimumTotalContainerSize();
        int n = solution.solve(p, d);

        System.out.println("#n " + n);
    }

}

class SolutionMinimumTotalContainerSize {

    public int solve(int[] p, int d) {

        if(d > p.length) return -1;

        int[][] dp = new int[d+1][p.length];

        for(int[] arr : dp) {
            Arrays.fill(arr, -1);
        }

        return dfs(p, d, dp,0);
    }


    public int dfs(int[] p, int d, int[][] dp, int idx) {
        //base case
        if(d == 1) {

            int max = 0;
            for(int i = idx; i < p.length; ++i) {
                if(max < p[i]) {
                    max = p[i];
                }
            }

            return max;
        }
        //already calculated
        if(dp[d][idx] != -1) return dp[d][idx];

        //calculate
        int max = Integer.MIN_VALUE;
        int res = Integer.MAX_VALUE;

        for(int i = idx; i < p.length - d + 1; ++i) {
            max = Math.max(max, p[i]);
            res = Math.min(res, max + dfs(p, d - 1, dp, i + 1));
        }

        return dp[d][idx] = res;
    }
}
