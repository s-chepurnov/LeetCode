package com.amazon;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PrimeAirRoute {
    public static void main(String[] args) {

        //TODO: change input arrays to List objects, validate answer
        int maxTravelDist = 7000;
        int forwardRouteArr[][] = {{1, 2000}, {2, 4000}, {3, 6000}};
        int backRouteArr[][] = {{1, 2000}};

        SolutionPrimeAirRoute sl = new SolutionPrimeAirRoute();
        List<SolutionPrimeAirRoute.ResultRoutes> output = sl.solve(maxTravelDist, forwardRouteArr, backRouteArr);

        System.out.println(output.toString());
    }

}

//O(n) = (Forward_n * Back_n) + NlogN + (Forward_n+Back_n)
//Forward_n = forwardRouteArr.length
//Back_n = backRouteArr.length
//N = Forward_n*Back_n
class SolutionPrimeAirRoute {

    public List<ResultRoutes> solve(int maxTravelDist, int forwardRouteArr[][], int backRouteArr[][]) {
        //init
        List<Dist> forwardList = new ArrayList<>();
        List<Dist> backList = new ArrayList<>();

        for(int i = 0; i < forwardRouteArr.length; i++) {
            forwardList.add(new Dist(forwardRouteArr[i][0], forwardRouteArr[i][1]));
        }

        for(int i = 0; i < forwardRouteArr.length; i++) {
            backList.add(new Dist(backRouteArr[i][0], backRouteArr[i][1]));
        }

        //solve
        List<ResultRoutes> allCombinations = new ArrayList<>();

        for(Dist forward : forwardList) {
            for(Dist back : backList) {
                allCombinations.add(new ResultRoutes(forward.id, back.id, forward.dist + back.dist));
            }
        }

        allCombinations = allCombinations.stream()
                .filter(f -> f.sum <= maxTravelDist)
                .sorted((r1, r2) -> r2.sum.compareTo(r1.sum))
                .collect(Collectors.toList());

        int max = allCombinations.get(0).sum;
        int index = 0;
        for(int i = 0; i < allCombinations.size(); ++i) {
            int current = allCombinations.get(i).sum;

            if(current < max) {
                index = i;
                break;
            }

        }

        return allCombinations.subList(0, index);
    }

    class Dist {
        public final int id;
        public final int dist;

        public Dist(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return "[" + id + ", " + dist + "]";
        }
    }

    class ResultRoutes {
        public final int idF;
        public final int idB;
        public final Integer sum;

        public ResultRoutes(int idF, int idB, int sum) {
            this.idF = idF;
            this.idB = idB;
            this.sum = sum;
        }

        @Override
        public String toString() {
            return "[" + idF + ", " + idB + "]";
        }
    }

}
