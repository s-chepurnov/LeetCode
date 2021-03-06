package com.amazon;

import java.util.*;
import java.util.stream.Collectors;

public class AmazonFulfilmentBuilder {

    public static void main(String[] args) {

        AmazonFulfilmentBuilderSolution solution = new AmazonFulfilmentBuilderSolution();

        int arr[] = {8,4,6,12};
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());

        int n = solution.solve(list);
        System.out.println("# " + n);
    }

}


class AmazonFulfilmentBuilderSolution {

    public int solve(List<Integer> list) {

        int summa = 0;

        if (list == null || list.size() == 0) {
            return summa;
        }

        //brute force
        while (list.size() > 1) {

            int min1 = list.stream().min(Comparator.comparing(Integer::valueOf)).get();
            list.remove(Integer.valueOf(min1));

            int min2 = list.stream().min(Comparator.comparing(Integer::valueOf)).get();
            list.remove(Integer.valueOf(min2));

            int sum = min1 + min2;

            list.add(sum);
            summa+=sum;
        }

        return summa;
    }
}
