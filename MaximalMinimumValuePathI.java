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


    public static void main(String[] args) {
        /*
        //Expected: 4
        int[][] matrix = {{5,1},
                          {4,5}};
        */

        /*
        //Expected: 0
        int[][] matrix = {{0}};
        */

        /*
        //Expected: 3
        int[][] matrix = {
                {7,5,3},
                {2,0,9},
                {4,5,9}
        };
        */


        //Expected: 1
        int[][] matrix = {  {1, 5, 3},
                            {2, 0, 9},
                            {4, 5, 9}};


        SolutionPathI sl = new SolutionPathI();
        int maxScore = sl.maxPathScore(matrix);

        System.out.println("max score: " + maxScore);
    }

}

class SolutionPathI {

    public List<List<Integer>> result = new ArrayList<>();

    public int maxPathScore(int[][] matrix) {
        List<Integer> path = new ArrayList<>();
        recursive(matrix, 0, 0, path);

        int maxScore = result.stream().map(list -> list.stream().min(Comparator.comparingInt(Integer::valueOf)).get())
                                      .max(Comparator.comparingInt(Integer::valueOf)).get();

        return maxScore;
    }

    public void recursive(int[][] matrix, int x, int y, List<Integer> path) {

        //if not valid - return
        int x_size = matrix.length;
        int y_size = matrix[0].length;
        if (x < 0 || x >= x_size || y < 0 || y >= y_size) return;

        //if valid - add point to path
        path.add(matrix[x][y]);

        //if path is completed - add it to result
        if(x == matrix.length-1 && y == matrix[0].length-1) {
            result.add(path);
        }

        // go down and right
        recursive(matrix, x + 1, y, new ArrayList<>(path));
        recursive(matrix, x, y + 1, new ArrayList<>(path));

    }

}
