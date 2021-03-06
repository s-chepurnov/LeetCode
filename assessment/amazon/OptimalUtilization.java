package com.amazon;

import java.util.*;

/**
 * Your task is to find an element from a and an element form b such that the sum of their values
 * is less or equal to target and as close to target as possible. Return a list of ids of selected elements.
 */
public class OptimalUtilization {
    public static void main(String[] args) {

        //Expected: [[2, 1]]
//        int[][] a = {{1, 2}, {2, 4}, {3, 6}};
//        int[][] b = {{1, 2}};
//        int target = 7;

        //Expected: [[2, 4], [3, 2]]
//        int[][] a = {{1, 3}, {2, 5}, {3, 7}, {4, 10}};
//        int[][] b = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
//        int target = 10;

        //Output: [[3, 1]]
        int[][] a = {{1, 8}, {2, 7}, {3, 14}};
        int[][] b = {{1, 5}, {2, 10}, {3, 14}};
        int target = 20;

        SolutionOptimalUtilization sl = new SolutionOptimalUtilization();
        int[][] result = sl.solve(a, b, target);

        for (int[] arr : result) {
            System.out.println("# " + Arrays.toString(arr));
        }
    }
}

class SolutionOptimalUtilization {

    public int[][] solve(int[][] a, int[][] b, int target) {


        int minDiff = Integer.MAX_VALUE;


        //count min difference
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {

                int elemA = a[i][1];
                int elemB = b[j][1];

                int sum = (elemA + elemB);

                if(sum == target) {
                    minDiff = target - sum;
                } else if (sum < target && minDiff > (target - sum)) {
                    minDiff = target - sum;
                } else {
                    continue;
                }
            }
        }

        //collect elements with min difference
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {

                //values
                int elemA = a[i][1];
                int elemB = b[j][1];

                int sum = (elemA + elemB);

                if(minDiff == (target - sum)) {
                    int[] arr = new int[2];
                    //ids
                    arr[0] = a[i][0];
                    arr[1] = b[j][0];

                    result.add(arr);
                }
            }
        }

        //convert List to Array
        int[][] answer = new int[result.size()][2];

        for (int k = 0; k<result.size(); ++k) {
            answer[k][0] = result.get(k)[0];
            answer[k][1] = result.get(k)[1];
        }


        return answer;
    }

}


