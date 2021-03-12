package com.amazon;

//solved by recursion
//Multiprocessor System OR Schedule tasks
//https://aonecode.com/interview-question/schedule-tasks

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MultiprocessorSystem {

    public static void main(String[] args) {

        int n = 5;
        int[] power = {4,2,8,3,5};
        int tasks = 19;

        SolutionMultiprocessorSystem solution = new SolutionMultiprocessorSystem();
        int min = solution.solve(n, power, tasks);

        System.out.println("# " + min);
    }

}

class SolutionMultiprocessorSystem {

    List<List<Integer>> result = new ArrayList();

    public int solve(int n, int[] power, int tasks) {

        if (tasks == 0)
            return 0;

        if (n == 0 || power == null || power.length == 0)
            return 0;

        List<Integer> list = IntStream.of(power)
                            .boxed()
                            .collect(Collectors.toList());

        f(tasks, list, new ArrayList<>());

        List<List<Integer>> res = result.stream()
                .sorted(Comparator.comparingInt(List::size))
                .collect(Collectors.toList());

        return res.get(0).size();
    }

    //recursively take every combination that gives us a sum of provided value (tasks)
    public void f(int tasks, List<Integer> power, List<Integer> selected) {
        if(tasks < 0) {
            return;
        }

        if(tasks == 0) {
            result.add(new ArrayList<>(selected));
            return;
        }

        for (int i = 0 ; i < power.size(); ++i) {

            int poweri = power.get(i);
            selected.add(poweri);
            power = new ArrayList<>(power);
            power.set(i, poweri/2);

            //remove zeros
            if(poweri/2 == 0 ) {
                power.remove(i);
            }

            f(tasks - poweri, power, selected);

            selected.remove(selected.size()-1);

        }
    }

}



