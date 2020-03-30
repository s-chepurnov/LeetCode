package org.algs4;

import java.util.Arrays;

public class MoviesOnFlight {

    public static void main(String[] args) {

        //Expected 95
        int[] movie_duration = {90, 85, 75, 60, 120, 150, 125};
        int d = 250;

        //Expected [-1, -1] or return empty ArrayList
//        int[] movie_duration = {90, 90, 90, 90, 90, 90, 90};
//        int d = 100;

        SolutionMoviesOnFlight sl = new SolutionMoviesOnFlight();
        int[] arr =  sl.solve(d, movie_duration);

        System.out.println("Movies on flight: " + Arrays.toString(arr));
    }
}

class SolutionMoviesOnFlight {

    public int[] solve(int d, int[] movie_duration) {

        int[] arr = new int[2];
        arr[0] = -1;
        arr[1] = -1;

        int maxDuration = d - 30;
        int maxWithMinDifference = Integer.MAX_VALUE;

        for (int i = 0; i < movie_duration.length; ++i) {
            for (int j = 0; j < movie_duration.length; ++j) {

                int localMax = movie_duration[i]+movie_duration[j];

                if (localMax == maxDuration) {
                    arr[0] = movie_duration[i];
                    arr[1] = movie_duration[j];

                    return arr;
                }

                if (localMax < maxDuration && (maxDuration - localMax) < maxWithMinDifference) {
                    arr[0] = movie_duration[i];
                    arr[1] = movie_duration[j];
                    maxWithMinDifference = (maxDuration - localMax);
                }

            }
        }

        if (arr[0] == -1 || arr[1] == -1) {
            //return smth special
        }

        return arr;
    }

}
