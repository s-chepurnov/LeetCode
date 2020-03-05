package org.algs4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 *
 * recursive decision, but it is better to do it iteratively (dfs or bfs) with stack or queue
 *
 */
public class TreasureIslandI {

    public static List<List<Point>> result = new ArrayList<>();

    public static void main(String[] args) {

        String input[][] = {
                {"O","O","O","O"},
                {"D","O","D","O"},
                {"O","O","O","O"},
                {"X","D","D","O"}
        };
        solve(input);

        //System.out.println("Number of all possible paths: " + result.size());

        List<Point> list = result
                .stream()
                .min(Comparator.comparing(List::size))
                .orElse(new ArrayList<>());

        System.out.println("The minimum route takes steps : " + (list.size() - 1) );
    }

    public static void solve(String[][] input) {

        recursive(input, 0, 0, new ArrayList<>());

    }

    public static void recursive(String[][] input, int x, int y, List<Point> path) {

        if ( !valid(input, x, y,path)) {
            return;
        }

        path.add(new Point(x,y));

        if (input[x][y].equals("X")) {
            result.add(path);
            return;
        }

        recursive(input, x-1, y, new ArrayList<>(path));
        recursive(input, x+1, y, new ArrayList<>(path));
        recursive(input, x, y-1, new ArrayList<>(path));
        recursive(input, x, y+1, new ArrayList<>(path));

    }


    public static boolean valid(String[][] input, int x, int y, List<Point> path) {

        int x_size = input.length;
        int y_size = input[0].length;

        Point p = new Point(x,y);
        if (x >= 0 && x < x_size && y >= 0 && y < y_size && !input[x][y].equals("D") && !path.contains(p)) {
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
