package com.google;

import java.util.Arrays;

/**
 * Given an Array A, find the minimum amplitude you can get after changing up to 3 elements.
 * Amplitude is the range of the array (basically difference between largest and smallest element).
 */
public class MinAmplitude {

    public static void main(String[] args) {

        int[] input = {-1, 3, -1, 8, 5, 4};
        // input[0] = 3
        // input[2] = 5
        // input[3] = 5
        //Output: 2

        //int[] input = {10, 10, 3, 4, 10};
        //Output: 0

        SolutionAmplitude sl = new SolutionAmplitude();
        int n = sl.solve(input);

        System.out.println("# minimum amplitude: " + n);
    }

}

class SolutionAmplitude {

    public int solve(int[] arr) {
        int upTo = 3; //up to 3 elements by requirements
        int minGlobal = Integer.MAX_VALUE;

        Arrays.sort(arr);

        // visualization of cases:
        // upTo = 3
        //
        // 0)
        // 1) *
        //       *
        // 2) **
        //    *  *
        //      **
        //
        // 3) ***
        //    **   *
        //    *   **
        //       ***
        for (int i = 0; i <= upTo; ++i) {
            int minCurrent = calcMinAmplitudeWithChangingKElements(arr, i);
            minGlobal = Math.min(minCurrent, minGlobal);
        }

        return minGlobal;
    }

    public int calcMinAmplitudeWithChangingKElements(int[] arr, int n) {
        int k = n+1; // numbers of cases that we need to consider
        int lastIndex = arr.length-1;

        int minInKScope = Integer.MAX_VALUE;

        int j = n;
        for (int i = 0; i < k; ++i) {

            int start  = i;
            int end = lastIndex - j;
            int сurrentAmplitude = calcMaxAmplitude(arr, start, end);
            minInKScope = Math.min(сurrentAmplitude, minInKScope);

            --j;
        }

        return minInKScope;
    }

    public int calcMaxAmplitude(int[] arr, int start, int end) {

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = start; i <= end; ++i) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }

        return max - min;
    }

}
