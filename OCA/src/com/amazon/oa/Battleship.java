package com.amazon.oa;

import java.util.ArrayList;
import java.util.List;

public class Battleship {

    public static void main(String[] args) {

        int N = 4;
        String S = "1B 2C,2D 4D"; // positions of each ship - top-left and bottom-right corner cells
        String T = "2B 2D 3D 4D 4A"; // sequence of hits
        //Expected:
        //"1,1"
        // that means [sunked, hit but not sunk]


//        int N = 3;
//        String S = "1A 1B,2C 2C";
//        String T = "1B";
        //Expected:
        // "0,1"

//        int N = 12;
//        String S = "1A 2A,12A 12A";
//        String T = "12A";
        //Expected "1,0",

        SolutionBattleship sl = new SolutionBattleship();
        String answer = sl.solution(4, S, T);

        //print
        System.out.println(answer);
    }

}

// Sn - nums of ships (S)
// Tn - nums of hits (T)
// O(n) = Sn + Tn*Sn + Sn
class SolutionBattleship {

    public String solution(int N, String S, String T) {

        //init ships from S
        List<Ship> ships = new ArrayList<>();//contains all ships

        String[] coordinates = S.split(",");
        for (String point : coordinates) {

            String[] updown = point.split(" ");
            Ship ship = new Ship(updown[0], updown[1]);
            ships.add(ship);
        }

        //apply all hits from T to each Ship
        String[] hits = T.split(" ");
        for (String hitPoint : hits) {
            hitShip(hitPoint, ships);
        }

        //count sunked and hited ships
        int sunked = 0;
        int hited = 0;

        for (Ship ship : ships) {
            if (ship.isHited())
                hited++;
            if(ship.isSunked()) {
                sunked++;
            }
        }

        String result = sunked + "," + hited;
        return result;
    }

    public void hitShip(String hitPoint, List<Ship> ships) {

        for (Ship ship : ships) {
            ship.hit(hitPoint);
        }

    }
}

class Ship {
    String topleft;
    String bottomright;

    int type = 0; // from 1 to 4

    List<String> points = new ArrayList<>();

    public Ship(String topleft, String bottomright) {
        this.topleft = topleft;
        this.bottomright = bottomright;

        //fill out all points of the ship and set the type of the ship (1,2,3,4)
        int l1 = topleft.length()-1;
        int x1 = Integer.valueOf(topleft.substring(0, l1));
        int y1 = topleft.charAt(topleft.length()-1);

        int l2 = bottomright.length()-1;
        int x2 = Integer.valueOf(bottomright.substring(0, l2));
        int y2 = bottomright.charAt(bottomright.length()-1);

        for (int i = x1; i <= x2; ++i) {
            for (int j = y1; j <= y2; ++j) {
                String point = i+ ""+(char)j;
                points.add(point);
            }
        }

        type = points.size();
    }

    public void hit(String hitPoint) {
        if (points.contains(hitPoint)) {
            points.remove(hitPoint);
        }
    }

    public boolean isSunked() {
        return points.isEmpty();
    }

    public boolean isHited() {
        //points.size() != type - means that someone hit it (remove some points)
        return !points.isEmpty() && points.size() != type;
    }

}
