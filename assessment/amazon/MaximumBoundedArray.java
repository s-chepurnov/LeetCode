package com.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
Maximum bounded array
https://aonecode.com/interview-question/maximum-bounded-array

brute force
 */
public class MaximumBoundedArray {

    public static void main(String[] args) {
        int n = 4;
        int lowerBound = 10;
        int upperBound = 12;

        SolutionMaximumBoundedArray solution = new SolutionMaximumBoundedArray();
        List<Integer> res = solution.solve(n, lowerBound, upperBound);

        System.out.println("# " + Arrays.toString(res.toArray()));
    }
}

class SolutionMaximumBoundedArray {

    public List<Integer> solve(int n, int lowerBound, int upperBound) {

        //generate all arrays with different middle
        List<List<Integer>> all = new ArrayList<List<Integer>>();

        for (int i=0; i < n; ++i) {
            int[] arr = new int[n];
            arr[i] = upperBound;

            ArrayList<Integer> list = IntStream.of(arr)
                    .boxed()
                    .collect(Collectors.toCollection(ArrayList::new));
            //fill out with all other values
            fill(list, i, n);
            all.add(list);
        }


        List<List<Integer>> processed = all.stream()
        //filter by bounds
        .filter(list -> {

            if( list.get(0) >= lowerBound &&
                list.get(0) <= upperBound &&
                list.get(list.size()-1) >= lowerBound &&
                list.get(list.size()-1) <= upperBound) {

                return true;
            }

            return false;
        //sort by rule 'from left to right'
        })
        .sorted((arr1,arr2) -> {
            for(int i = 0; i < arr1.size() || i < arr2.size(); ++i) {
                int cmp = arr1.get(i).compareTo(arr2.get(i));
                if(cmp == 0) continue;
                else return cmp;
            }

            return -1;
        })
        .collect(Collectors.toList());


        //return biggest one that is a last in this case
        return processed.get(processed.size()-1);
    }

    public void fill(List<Integer> list, int i, int n) {

        for(int lower = i - 1; lower >=0; --lower) {
            int next = list.get(lower+1);
            list.set(lower, next-1);
        }

        for(int upper = i + 1; upper < n; ++upper) {
            int previous = list.get(upper-1);
            list.set(upper, previous-1);
        }
    }
}
