package com.amazon;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OptimizatingBoxWeight {

    public static void main(String[] args) {

        int  n = 6;
        int arr[] = {5, 3, 2, 4, 1, 2};

        SolutionOptimizatingBoxWeight solution = new SolutionOptimizatingBoxWeight();
        List listA = solution.solve(arr, n);

        System.out.println(Arrays.toString(listA.toArray()));
    }
}

class SolutionOptimizatingBoxWeight {

    public List<Integer> solve(int[] arr, int n) {
        List<Integer> ab = IntStream.of(arr)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        //count total sum
        int sum = 0;
        for (int num : ab) {
            sum+=num;
        }

//        System.out.println(Arrays.toString(ab.toArray()));
//        System.out.println("total sum " + sum);

        int sumA = 0;
        int sumB = sum;
        List<Integer> listA = new ArrayList<>();

        while (sumA <= sumB || ab.isEmpty()) {
            Integer max = ab.get(0);
            listA.add(max);
            sumA+=max;
            sumB-=max;

            ab.remove(0);
        }

        Collections.reverse(listA);
        return listA;
    }
}
