package com.amazon;

import java.util.*;

public class TreasureIslandII {

    public static void main(String[] args) {

        //Expected 3
        char input[][] = {
                {'S','O','O','S','S'},
                {'D','O','D','O','D'},
                {'O','O','O','O','X'},
                {'X','D','D','O','O'},
                {'X','D','D','D','O'},
                {'X','D','D','D','O'},
        };

        // input from "TreasureIsland I"
        /*
        char[][] input =   {{'S', 'O', 'O', 'O'},
                            {'D', 'O', 'D', 'O'},
                            {'O', 'O', 'O', 'O'},
                            {'X', 'D', 'D', 'O'}};
        */

        SolutionTreasureIslandII sl = new SolutionTreasureIslandII();
        int n = sl.solve(input);

        System.out.println("# " + n);
    }
}

class SolutionTreasureIslandII {

    private final int[][] DIRS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int solve(char[][] input) {

        List<Point> starts = new ArrayList();
        for (int i=0; i < input.length; ++i) {
            for (int j = 0; j < input[0].length; ++j) {
                if(input[i][j] == 'S') {
                    starts.add(new Point(i,j));
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (Point point : starts) {

            int x_size = input.length;
            int y_size = input[0].length;

            boolean[][] visited = new boolean[x_size][y_size];

            int count = resolveOne(input, point, visited);
            result.add(count);
        }

        return result.stream()
                .sorted(Comparator.naturalOrder())
                .findFirst().get();
    }

    public int resolveOne(char[][] input, Point start, boolean[][] visited) {

        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);
        visited[start.x][start.y] = true;

        int steps = 1;
        while ( !queue.isEmpty()) {

            for (int count = queue.size(); count > 0; --count) {

                Point p = queue.poll();

                for (int dirs[] : DIRS) {
                    int r  = p.x + dirs[0];
                    int c  = p.y + dirs[1];


                    if( valid(input, r, c, visited)) {

                        if(input[r][c] == 'X') {
                            return steps;
                        }

                        visited[r][c] = true;
                        queue.offer(new Point(r,c));

                    }
                }

            }
            ++steps;
        }

        return steps;
    }

    public boolean valid(char[][] input, int x, int y, boolean[][] visited) {

        int x_size = input.length;
        int y_size = input[0].length;

        if (x >= 0 && x < x_size && y >= 0 && y < y_size && input[x][y] != 'D' && !visited[x][y]) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }
}

