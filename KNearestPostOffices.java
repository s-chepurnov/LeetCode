package org.algs4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class KNearestPostOffices {
    public static void main(String[] args) {

        //output: [[-1, 2], [0, 3], [4, 3]]
        int[] you = {0, 0};
        int[][] post_offices = {{-16, 5}, {-1, 2}, {4, 3}, {10, -2}, {0, 3}, {-5, -9}};
        int k = 3;

        SolutionKNearestPostOffices sl = new SolutionKNearestPostOffices();
        int[][] arr = sl.solve(you, post_offices, k);

        System.out.println("the k post offices located closest to you: ");
        for (int[] a : arr) {
            System.out.println(Arrays.toString(a));
        }


    }
}

class SolutionKNearestPostOffices {

    public int[][] solve(int[] you, int[][] post_offices, int k) {

        //your location
        int m = you[0];
        int n = you[1];

        List<Distance> result = new ArrayList<>();
        for (int i = 0; i < post_offices.length; ++i) {

            //location of post office
            int p = post_offices[i][0];
            int q = post_offices[i][1];

            double distance = Math.sqrt((m - p) * (m - p) + (n - q) * (n - q));

            result.add(new Distance(distance, p, q));
        }

        List<List<Integer>> distances = result.stream()
                .sorted( (o1,o2)-> {
                    Double d1 = Double.valueOf(o1.distance);
                    Double d2 = Double.valueOf(o2.distance);
                    return d1.compareTo(d2);
                })
                .limit(k)
                //(distance, p, q) -> (p,q)
                .map(i -> {List<Integer> list = new ArrayList<>();
                            list.add(i.p);
                            list.add(i.q);
                            return list;
                          })
                .collect(Collectors.toList());

        int[][] array = new int[distances.size()][2];

        int counter = 0;
        for (List<Integer> dis : distances) {
            array[counter][0] = dis.get(0);
            array[counter][1] = dis.get(1);
            ++counter;
        }

        return array;
    }
}

class Distance {
    public double distance;

    public int p;
    public int q;

    public Distance(double distance, int p, int q) {
        this.distance = distance;
        this.p = p;
        this.q = q;
    }
}

