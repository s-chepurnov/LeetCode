package com.amazon;

import java.util.Arrays;

public class RoverControl {

    public static void main(String[] args) {

        //int n = 4;
        //String[] commands = {"RIGHT", "UP", "DOWN", "LEFT", "DOWN", "DOWN"};
        //12

        int n = 0;
        String[] commands = {"RIGHT", "DOWN", "LEFT", "LEFT", "DOWN"};
        //8


        int arr[][] = { {0,1,2,3},
                        {4,5,6,7},
                        {8,9,10,11},
                        {12,13,14,15}};

        System.out.println(Arrays.toString(arr[0]));
        SolutionRoverControl solution = new SolutionRoverControl();
        int finalPosition = solution.solve(n, arr, commands);
        System.out.println("# " + finalPosition);

    }
}

class SolutionRoverControl {
                                 //DOWN   RIGHT   UP       LEFT
    private final int[][] DIRS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int solve(int n, int arr[][], String[] commands) {

        //move to start position
        int cnt = 0;
        int k = 0,l = 0;
        for(int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[0].length; ++j) {

                if(cnt == n) {
                    k = i;
                    l = j;
                }

                cnt++;
            }
        }
        //System.out.println("i: " + k + " j: " + l + " arr[n] = " + n);

        //move by command
        for (String cmd : commands) {

            int[] dir = {};
            if(cmd.equals("DOWN")) {
                dir = DIRS[0];
            }
            if(cmd.equals("RIGHT")) {
                dir = DIRS[1];
            }
            if(cmd.equals("UP")) {
                dir = DIRS[2];
            }
            if(cmd.equals("LEFT")) {
                dir = DIRS[3];
            }

            if(k + dir[0] >= 0 && k + dir[0] <= arr.length &&
                    l + dir[1] >= 0 && l + dir[1] <= arr[0].length) {
                k = k + dir[0];
                l = l + dir[1];
            }

        }

        //return final position
        int value = arr[k][l];

        return value;
    }
}
