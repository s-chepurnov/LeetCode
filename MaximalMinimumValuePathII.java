package org.algs4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 *   Given a two 2D integer array, find the max score of a path from the upper left cell to bottom right cell
 *   that doesn't visit any of the cells twice. The score of a path is the minimum value in that path.
 *
 */
public class MaximalMinimumValuePathII {

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
        boolean[][] visited = new boolean[input.length][input[0].length];

        recursive(input, 0, 0, path, visited);
        System.out.println("result.size: " +  result.size());
        int maxScore = result.stream().map(list -> list
                                                   .stream()
                                                   .min(Comparator.comparingInt(Integer::valueOf)).get())
                                      .max(Comparator.comparingInt(Integer::valueOf)).get();

        return maxScore;
    }

    public static void recursive(int[][] input, int x, int y, List<Integer> path, boolean[][] visited) {

        // isValid point
        int x_size = input.length;
        int y_size = input[0].length;
        if (x < 0 || x >= x_size || y < 0 || y >= y_size || visited[x][y]) {
            return;
        }

        visited[x][y] = true;
        path.add(input[x][y]);

        //if touch the end point
        if(x == input.length-1 && y == input[0].length-1) {
            result.add(path);
        }

        //traverse from start to end
        boolean[][] a = new boolean[visited.length][visited[0].length];
        copy(visited, a);

        recursive(input, x + 1, y, new ArrayList<>(path), a);
        recursive(input, x - 1, y, new ArrayList<>(path), a);
        recursive(input, x, y + 1, new ArrayList<>(path), a);
        recursive(input, x, y - 1, new ArrayList<>(path), a);

    }


    public static void copy(boolean[][] src, boolean[][] dest) {

        for (int i = 0; i < src.length; ++i) {
            for (int j = 0; j < src[0].length; ++j) {
                   dest[i][j] = src[i][j];
            }
        }
    }


}
