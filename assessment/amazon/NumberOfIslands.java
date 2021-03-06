package com.amazon;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 */

public class NumberOfIslands {

    public static void main(String[] args) {
        SolutionNumberOfIslands solution = new SolutionNumberOfIslands();

        char[][] grid = {   {1,1,1,1,0},
                            {1,1,0,1,0},
                            {1,1,0,0,0},
                            {0,0,0,0,0}
                        };

        int n = solution.numIslands(grid);

        System.out.println("# " + n);
    }
}

class SolutionNumberOfIslands {

    public int numIslands(char[][] grid) {

        int x = grid.length;
        if(x == 0) {
            return 0;
        }

        int y = grid[0].length;

        int numIslands=0;

        for(int i=0; i<x; ++i) {
            for(int j=0; j<y; ++j) {

                if(grid[i][j]=='0') {
                    continue;
                }

                dfs(grid, i , j);

                numIslands++;
            }
        }

        return numIslands;
    }


    public void dfs(char[][] grid, int i, int j) {

        int x = grid.length;
        int y = grid[0].length;

        if (i<0 || i>=x || j<0 || j>=y || grid[i][j]=='0') {
            return;
        }

        grid[i][j]='0';

        dfs(grid, i+1, j);
        dfs(grid, i-1, j);
        dfs(grid, i, j+1);
        dfs(grid, i, j-1);
    }
}

