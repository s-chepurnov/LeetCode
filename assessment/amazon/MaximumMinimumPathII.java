package com.amazon;

import java.util.PriorityQueue;

/**
 * https://aonecode.com/path-with-maximum-minimum-value
 * ACCEPTED 125/125
 */
public class MaximumMinimumPathII {
    public static void main(String[] args) {

        //Expected: 1
        int[][] matrix = {  {1, 5, 3},
                            {2, 0, 9},
                            {4, 5, 9}};

        SolutionMMPII sl = new SolutionMMPII();
        int maxScore = sl.maxPathScore(matrix);

        System.out.println("max score: " + maxScore);

    }
}

class SolutionMMPII {
    public int maxPathScore(int[][] matrix) {

        int[][] DIRS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        int n = matrix.length;
        int m = matrix[0].length;
        boolean[][] visited = new boolean[n][m];

        //In each step of bfs we get maximum min value (PQ in reverse order)
        //PQ {row,col, maximum_min_value}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        pq.offer(new int[]{0, 0, matrix[0][0]});

        //bfs
        while (!pq.isEmpty()) {
            int[] arr = pq.poll();
            int r = arr[0];
            int c = arr[1];

            if (r == n - 1 && c == m - 1) {
                return arr[2];
            }

            visited[r][c] = true;

            for (int[] dir : DIRS) {
                int nextRow = r + dir[0];
                int nextCol = c + dir[1];

                //if not valid - skip it
                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m || visited[nextRow][nextCol])
                    continue;

                //compute the min value on this step, arr[2] - current min value
                pq.offer(new int[]{nextRow, nextCol, Math.min(arr[2], matrix[nextRow][nextCol])});
            }
        }
        return -1;
    }
}
