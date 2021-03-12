package com.amazon;

import java.util.*;

public class StorageOptimization {
    public static void main(String[] args) {

        int n = 6;
        int m = 6;
        int[] h = {4};
        int[] v = {2};
        //4

//        int n = 3;
//        int m = 2;
//        int[] h = {1, 2, 3};
//        int[] v = {1, 2};
        //12

        SolutionStorageOptimization solution = new SolutionStorageOptimization();
        int biggest = solution.solve(n,m,h,v);
        System.out.println("The volume of the biggest storage space is: " + biggest);
    }
}

class SolutionStorageOptimization {

    public int solve(int n, int m, int[] h, int[] v) {

        int[][] arr = new int[n+1][m+1];

        int cnt = 0;
        for(int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[0].length; ++j) {
                arr[i][j] = cnt++;
            }
        }

        //remove vertical
        for (int vv : v) {
            removeVertical(arr, vv);
        }

        //remove horizontal
        for (int hh : h) {
            removeHorizontal(arr, hh);
        }

        //save in map:
        //number, counter
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[0].length; ++j) {

                Integer counter = map.get(arr[i][j]);
                if(counter == null) {
                    map.put(arr[i][j], 1);
                } else {
                    map.put(arr[i][j], ++counter);
                }
            }
        }

        Collection<Integer> counters = map.values();
        Integer max = counters.stream()
                .max(Comparator.comparingInt(Integer::valueOf)).get();

//print
//        for(int i = 0; i < arr.length; ++i) {
//            System.out.println(Arrays.toString(arr[i]));
//        }

        return max;
    }

    public void removeVertical(int[][] arr, int v) {
        for(int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[0].length; ++j) {

                if(j == v) {
                    int fill = arr[i][j-1];
                    arr[i][j-1] = fill;
                    arr[i][j] = fill;
                }
            }
        }
    }

    public void removeHorizontal(int[][] arr, int h) {
        for(int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[0].length; ++j) {

                if(i == h) {
                    int fill = arr[i-1][j];
                    arr[i-1][j] = fill;
                    arr[i][j] = fill;
                }
            }
        }
    }
}
