package com.leetcode;

class Solution {

    public static void main(String[] args) {

        int[][] grid = {
                {1,1,1,1,0},
                {1,1,0,1,0},
                {1,1,0,0,0},
                {0,0,0,0,0}
        };

        Solution solution = new Solution();
        int n = solution.numIslands(grid);
        System.out.println("# " + n);
    }

    public int numIslands(int[][] grid) {

        int numIslands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                if(grid[i][j] == 1) {


                    dfs(grid, i, j);

                    numIslands++;
                }

            }
        }

        return numIslands;
    }


    public void dfs(int[][] grid, int i, int j) {

        if(i < 0 || j < 0) return;
        if(i >= grid.length || j >= grid[0].length) return;

        if(grid[i][j] == 1) {
            grid[i][j] = 0;


            dfs(grid, i + 1, j);
            dfs(grid, i - 1, j);
            dfs(grid, i, j + 1);
            dfs(grid, i, j - 1);
        }

    }
}
