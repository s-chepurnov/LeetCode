package com.amazon;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ConnectRopes {

    public static void main(String[] args) {

        //Expected: 58
        //int[] ropes = {8, 4, 6, 12};

        //Expected: 54
        //int[] ropes = {20, 4, 8, 2};

        //Expected: 224
        //int[] ropes = {1, 2, 5, 10, 35, 89};

        //Expected: 20
        int[] ropes = {2, 2, 3, 3};

        SolutionConnectRopes sl = new SolutionConnectRopes();
        int n = sl.connectRopes(ropes);

        System.out.println("# " + n);

    }
}

class SolutionConnectRopes {

    public int connectRopes(int[] ropes) {

        List<Integer> list = Arrays.stream(ropes).boxed().collect(Collectors.toList());

        int summa = 0;
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
