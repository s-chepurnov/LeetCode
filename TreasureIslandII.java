package org.algs4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TreasureIslandII {

    public static List<List<Point>> result = new ArrayList<>();

    public static void main(String[] args) {

        char input[][] = {
                {'S','O','O','S','S'},
                {'D','O','D','O','D'},
                {'O','O','O','O','X'},
                {'X','D','D','O','O'},
                {'X','D','D','D','O'},
                {'X','D','D','D','O'},
        };
        solve(input);

    }

    public static void solve(char[][] input) {


    }

    public static boolean valid(char[][] input, int x, int y, List<Point> path) {

        int x_size = input.length;
        int y_size = input[0].length;

        Point p = new Point(x,y);
        if (x >= 0 && x < x_size && y >= 0 && y < y_size && input[x][y] != 'D' && !path.contains(p)) {
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
