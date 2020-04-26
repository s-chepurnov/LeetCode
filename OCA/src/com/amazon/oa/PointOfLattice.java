package com.amazon.oa;

public class PointOfLattice {

    public static void main(String[] args) {
        SolutionPointOfLattice sl = new SolutionPointOfLattice();

        int ax = -1, ay = 3;
        int bx = 3, by = 1;
        //Expected: 2,-1

        String result = sl.solve(ax, ay, bx, by);

        System.out.println(result);
    }

}

class SolutionPointOfLattice {

    public String solve(int ax, int ay, int bx, int by) {
        int dx = bx - ax, dy = by - ay;

        // rotate 90
        int rx = dy, ry = -dx;

        // reduce
        //we are interested in finding the minimum vector ( from Bx, By) which is still an integer(for it to be on a lattice point).
        //GCD is that property we are looking for
        int gcd = Math.abs(gcd(rx, ry));
        rx /= gcd;
        ry /= gcd;

        //return new int[]{bx + rx, by + ry};
        return (bx + rx) + "," + (by + ry);
    }

    //O(log (min(x, y))
    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }

}
