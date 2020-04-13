package com.amazon.oa;

import java.util.ArrayList;
import java.util.List;

/**
 * Amazon Fresh
 *
 */
public class AmazonFresh {
    public static void main(String[] args) {
        int m[][] = { {1, 2}, {3, 4}, {1, -1}}; // expected: [[1, -1], [1, 2]]

        //int m[][] = { {2, 4}, {3, 6}, {5, 3}}; // expected: [[2, 4], [5, 3]]

        List<Point> allLocations = new ArrayList<>();
        for(int i = 0; i < m.length; i++) {
            allLocations.add(new Point(m[i][0], m[i][1]));
        }

        int numDeliveries = 2;
        List<Point> output = f(3, allLocations, numDeliveries);
        System.out.println(output.toString());
    }

    public static List<Point> f(int numDestinations, List<Point> allLocations, int numDeliveries) {
        allLocations.sort((p1, p2) -> {
            final double THRESHOLD = .0001;

            double root1 = Math.sqrt(p1.x*p1.x + p1.y*p1.y);
            double root2 = Math.sqrt(p2.x*p2.x + p2.y*p2.y);
            if (Math.abs(root1-root2) < THRESHOLD) {
                return 0;
            } else {
                if(root1<root2) return -1;
                else return 1;
            }

        });

        return allLocations.subList(0, numDeliveries);
    }
}

/*
class Point {
    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "["  + x + ", " + y + "]";
    }
}
*/
