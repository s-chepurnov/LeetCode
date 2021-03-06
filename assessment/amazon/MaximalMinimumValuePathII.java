package com.amazon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *   Given a two 2D integer array, find the max score of a path from the upper left cell to bottom right cell
 *   that doesn't visit any of the cells twice. The score of a path is the minimum value in that path.
 */
public class MaximalMinimumValuePathII {

    public static void main(String[] args) {

        /*
        //Expected: 3
        int[][] matrix = {
                {7,5,3},
                {2,0,9},
                {4,5,9}
        };
        */

        //Expected 0
        int matrix[][] = {{0, 1},{0, 2},{1, 3},{2, 3},{2, 5},{5, 6},{3, 4}};

        /*
        //Expected 1
        int[][] matrix = {  {1, 5, 3},
                            {2, 0, 9},
                            {4, 5, 9}
        };
        */

        SolutionPathII sl = new SolutionPathII();
        int maxScore = sl.maxPathScore(matrix);
        System.out.println("max score: " + maxScore);
    }

}

class SolutionPathII {

    public List<List<Integer>> result = new ArrayList<>();

    public int maxPathScore(int[][] matrix) {
        List<Integer> path = new ArrayList<>();
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];

        recursive(matrix, 0, 0, path, visited);
        //System.out.println("result.size: " + result.size());

        int maxScore = result.stream().map(list -> list.stream().min(Comparator.comparingInt(Integer::valueOf)).get())
                                      .max(Comparator.comparingInt(Integer::valueOf)).get();
        return maxScore;
    }

    public void recursive(int[][] input, int x, int y, List<Integer> path, boolean[][] visited) {

        // isValid point
        int x_size = input.length;
        int y_size = input[0].length;
        if (x < 0 || x >= x_size || y < 0 || y >= y_size || visited[x][y]) {
            return;
        }

        visited[x][y] = true;
        path.add(input[x][y]);

        //if touch the end point
        if (x == input.length - 1 && y == input[0].length - 1) {
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


    public void copy(boolean[][] src, boolean[][] dest) {

        for (int i = 0; i < src.length; ++i) {
            for (int j = 0; j < src[0].length; ++j) {
                dest[i][j] = src[i][j];
            }
        }
    }
}

