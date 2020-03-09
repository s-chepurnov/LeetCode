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

    public static int DIRS[][] = {{1,0}, {0,1}, {-1,0}, {0, -1}};

    public static void main(String[] args) {

        int[][] arr = {{0, 1, 1, 0, 1},
                     {0, 1, 0, 1, 0},
                     {0, 0, 0, 0, 1},
                     {0, 1, 0, 0, 0}};


        int days = 0;
        do {
            ++days;
            solve(arr);
        } while ( !check(arr));

        System.out.println("# days: " + days);

    }


    public static void solve(int arr[][]) {

        List<Point> stepPoints = new ArrayList<>();

        //save all Zombie positions
        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[0].length; ++j) {

                if (arr[i][j] == 1) {
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

                if (valid(arr,r,c)) {
                    arr[r][c] = 1;
                }
            }
        }

        /* represent
        for(int s = 0 ; s < arr.length; ++s) {
            System.out.println(Arrays.toString(arr[s]));
        }

        System.out.println("");
        */

    }

    public static boolean check (int[][] arr) {

        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[0].length; ++j) {

                if (arr[i][j] == 0) {
                    return false;
                }
            }
        }

        return true;

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

class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
