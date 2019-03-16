import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Amazon Prime Air Shipping Routes
 *
 * Amazon Prime Air is developing a system that divides shipping routes using flight optimization routing systems to a
 * cluster of aircraft that can fulfill these routes. Each shipping route is identified by a unique integer identifier,
 * requires a fixed non-zero amount of travel distance between airports, and is defined to be either a forward
 * shipping route or a return shipping route. Identifiers are guaranteed to be unique within their own route type,
 * but not across route types.
 *
 * Each aircraft should be assigned two shipping routes at once: one forward route and one return route.
 * Due to the complex scheduling of flight plans, all aircraft have a fixed maximum operating travel distance,
 * and cannot be scheduled to fly a shipping route that requires more travel distance than the prescribed maximum
 * operating travel distance. The goal of the system is to optimize the total operating travel distance of a given aircraft.
 * A forward/return shipping route pair is considered to be "optimal" if there does not exist another pair
 * that has a higher operating travel distance than this pair, and also has a total less than or equal to the maximum
 * operating travel distance of the aircraft.
 * For example, if the aircraft has a maximum operating travel distance of 3000 miles, a forward/return
 * shipping route pair using a total of 2900 miles would be optimal if there does not exist a pair that uses a total
 * operating travel distance of 3000 miles, but would not be considered optimal if such a pair did exist.
 * Your task is to write an algorithm to optimize the sets of forward/return shipping route pairs that allow
 * the aircraft to be optimally utilized, given a list of forward shipping routes and a list of return shipping routes.
 *
 * Input
 * The input to the function/method consists of three arguments:
 * maxTravelDist, an integer representing the maximum operating travel distance of the given aircraft;
 *
 * forwardRouteList, a list of pairs of integers where the first integer represents the unique identifier of a forward
 *      shipping route and the second integer represents the amount of travel distance required by this shipping route;
 *
 * returnRouteList, a list of pairs of integers where the first integer represents the unique identifier of a return
 *      shipping route and the second integer represents the amount of travel distance required by this shipping route.
 *
 * Output
 * Return a list of pairs of integers representing the pads of IDs of forward and return shipping routes that optimally
 * utilize the given aircraft. If no route is possible, return an empty list.
 *
 * Examples
 * Example 1: Input: maxTravelDist= 7000 forwardRouteList = [[1,2000],[2,4000],[3,6000]] returnRouteList = [[1,2000]]
 *
 * Output: [[2,1]]
 *
 * Explanation: There are only three combinations, [1,1], [2,1], and [3,1], which have a total
 * of 4000, 6000, and 8000 miles, respectively. Since 6000 is the largest use that does not exceed 7000,
 * [2,1] is the only optimal pair.
 *
 * Example 2: Input:
 * maxTravelDist= 10000
 * forwardRouteList = [[1, 3000], [2, 5000], [3, 7000], [4, 10000]]
 * returnRouteList= [[1, 2000], [2, 3000], [3, 4000], [4, 5000]]
 *
 * Output: [[2, 4], [3, 2]]
 *
 * Explanation: There are two pairs of forward and return shipping routes possible that optimally utilizes the given aircraft.
 * Shipping Route ID#2 from the forwardShippingRouteList requires 5000 miles travelled, and Shipping Route ID84 from
 * returnShippingRouteList also requires 5000 miles travelled. Combined, they add up to 10000 miles travelled.
 * Similarly, Shipping Route ID#3 from forwardShippingRouteList requires 7000 miles travelled, and Shipping Route ID#2
 * from returnShippingRouteList requires 3000 miles travelled.
 * These also add up to 10000 miles travelled. Therefore, the pairs of forward and return shipping routes that
 * optimally utilize the aircraft are [2, 4] and [3, 2].
 */
public class AmazonPrimeAirShippingRoutes {
    public static void main(String[] args) {
        int maxTravelDist = 10000;
        int forwardRouteArr[][] = {{1, 3000}, {2, 5000}, {3, 7000}, {4, 10000}};
        int backRouteArr[][] = {{1, 2000}, {2, 3000}, {3, 4000}, {4, 5000}};

        List<Dist> forwardList = new ArrayList<>();
        List<Dist> backList = new ArrayList<>();

        for(int i = 0; i < forwardRouteArr.length; i++) {
            forwardList.add(new Dist(forwardRouteArr[i][0], forwardRouteArr[i][1]));
        }

        for(int i = 0; i < forwardRouteArr.length; i++) {
            backList.add(new Dist(backRouteArr[i][0], backRouteArr[i][1]));
        }


        List<Result> output = solve(maxTravelDist, forwardList, backList);
        System.out.println(output.toString());
    }

    public static List<Result> solve(int maxTravelDist, List<Dist> forwardList, List<Dist> backList) {
        List<Result> allCombinations = new ArrayList<>();

        for(Dist forward : forwardList) {
            for(Dist back : backList) {
                allCombinations.add(new Result(forward.id, back.id, forward.dist + back.dist));
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

class Result {
    public final int idF;
    public final int idB;
    public final Integer sum;

    public Result(int idF, int idB, int sum) {
        this.idF = idF;
        this.idB = idB;
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "[" + idF + ", " + idB + "]";
    }
}