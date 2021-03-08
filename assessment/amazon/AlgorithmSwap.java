package com.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlgorithmSwap {

    public static void main(String[] args) {

        int[] arr = {5,1,4,2};
        //4

        //int[] arr = {7,1,2};
        //2

        SolutionAlgorithmSwap solution = new SolutionAlgorithmSwap();
        int n = solution.solve(arr);

        System.out.println("# swaps: " + n);
        System.out.println("sorted arr: " + Arrays.toString(arr));

    }
}

class SolutionAlgorithmSwap {

    public int solve(int[] arr) {

        int counter = 0;

        if(arr == null || arr.length == 0)
            return counter;

        for (int i = 0; i < arr.length; ++i) {
            for (int j = i; j < arr.length; ++j) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                    counter++;
                }
            }
        }

        return counter;
    }

    public void swap(int[] arr, int i, int j) {

        int swp = arr[i];
        arr[i] = arr[j];
        arr[j] = swp;

    }

}

