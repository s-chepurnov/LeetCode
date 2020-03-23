package org.algs4;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Input:
 * matrix, a 2D integer array where a[i][j] = 1 represents a zombie on the cell and a[i][j] = 0 represents a human on the cell.
 *
 * Output:
 * Return an integer represent how many days does it take to infect all humans.
 * Return -1 if no zombie exists.
 *
 */

public class ZombieMatrix {

    public static void main(String[] args) {


        int[][] arr = {{0, 1, 1, 0, 1},
                     {0, 1, 0, 1, 0},
                     {0, 0, 0, 0, 1},
                     {0, 1, 0, 0, 0}};


        /*
        int[][] arr = {{0}};
        */
/*
        int[][] arr = {{1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}};
*/
        SolutionZombie sl = new SolutionZombie();
        int n = sl.humanDays(arr);
        System.out.println("# days: " + n);
    }

}

class SolutionZombie {

    public int DIRS[][] = {{1,0}, {0,1}, {-1,0}, {0, -1}};

    public int humanDays(int[][] matrix) {
        if (matrix == null || noZombieAtAll(matrix)) {
            return -1; // no Zombie exist
        }

        int days = 0;
        while( !isAllElementsAreZombie(matrix)) {
            solve(matrix);
            ++days;
        }

        return days;
    }

    public void solve(int[][] matrix) {
        List<Point> stepPoints = new ArrayList<>(1000);

        //save all Zombie positions
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {

                if (matrix[i][j] == 1) {
                    Point point = new Point(i, j);
                    stepPoints.add(point);
                }
            }
        }

        //turn all adj humans into zombie
        for (Point p : stepPoints) {

            for (int[] dir : DIRS) {
                int r = p.x + dir[0];
                int c = p.y + dir[1];

                //if valid coordinates
                int x_size = matrix.length;
                int y_size = matrix[0].length;

                if (r >= 0 && r < x_size && c >= 0 && c < y_size) {
                    matrix[r][c] = 1;
                }
            }
        }

    }

    public boolean isAllElementsAreZombie(int[][] arr) {

        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[0].length; ++j) {
                if (arr[i][j] == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean noZombieAtAll(int[][] arr) {

        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[0].length; ++j) {
                if (arr[i][j] == 1) {
                    return false;
                }
            }
        }

        return true;
    }

}

class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
