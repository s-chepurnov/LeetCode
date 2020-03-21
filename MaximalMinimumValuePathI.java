package org.algs4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *  Max Of Min Altitudes
 *
 *  Input:
 *   [[5, 1],
 *   [4, 5]]
 *
 * Output: 4
 * Explanation:
 * Possible paths:
 * 5 → 1 → 5 => min value is 1
 * 5 → 4 → 5 => min value is 4
 * Return the max value among minimum values => max(4, 1) = 4.
 */
public class MaximalMinimumValuePathI {

    public static List<List<Integer>> result = new ArrayList<>();

    public static void main(String[] args) {

        int[][] input = {
                {7,5,3},
                {2,0,9},
                {4,5,9}
        };

        int maxScore = solve(input);

        System.out.println("max score: " + maxScore);
    }

    public static int solve(int[][] input) {

        List<Integer> path = new ArrayList<>();
        recursive(input, 0, 0, path);

        int maxScore = result.stream().map(list -> list
                                                   .stream()
                                                   .min(Comparator.comparingInt(Integer::valueOf)).get())
                                      .max(Comparator.comparingInt(Integer::valueOf)).get();

        return maxScore;
    }

    public static void recursive(int[][] input, int x, int y, List<Integer> path) {

        if( !valid(input, x, y)) {
            return;
        }

        path.add(input[x][y]);

        if(x == input.length-1 && y == input[0].length-1) {
            result.add(path);
        }

        recursive(input, x + 1, y, new ArrayList<>(path));
        recursive(input, x, y + 1, new ArrayList<>(path));

    }

    public static boolean valid(int[][] input, int x, int y) {

        int x_size = input.length;
        int y_size = input[0].length;

        if (x >= 0 && x < x_size && y >= 0 && y < y_size) {
            return true;
        }

        return false;
    }

}
