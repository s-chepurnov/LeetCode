package com.amazon;

import java.util.*;
import java.util.stream.Collectors;

/*
Returns:

int: the minimum number of additional five-star reviews the company needs to meet the threshold ratingsThreshold
 */
public class FiveStarSellers {

    public static void main(String[] args) {

        List<List<Integer>> productRatings = new ArrayList<>();

        List<Integer> oneProduct = new ArrayList<>();
        oneProduct.add(4); //# five stars
        oneProduct.add(4); //# total stars
        productRatings.add(oneProduct);

        List<Integer> twoProduct = new ArrayList<>();
        twoProduct.add(1);
        twoProduct.add(2);
        productRatings.add(twoProduct);

        List<Integer> threeProduct = new ArrayList<>();
        threeProduct.add(3);
        threeProduct.add(6);
        productRatings.add(threeProduct);

        int threshold = 77;

        FiveStarSellersSolution solution = new FiveStarSellersSolution();
        //list
//        int min = solution.solve(threshold, productRatings);
//        System.out.println("# solve: " + min);

        //arr
        int[][] productRatings2 = {{4,4},{1,2},{3,6}};
//        int threshold = 90;
//
//        int min2 = solution.fiveStarReviews(productRatings2, threshold);
//        System.out.println("# fiveStarReviews: " + min2);

        //brute force
        //int min3 = solution.solve(threshold, productRatings);
        int min3 = solution.fiveStarReviews(productRatings2, threshold);
        System.out.println("# fiveStarReviews: " + min3);
    }

}

class FiveStarSellersSolution {

    List<Integer> numbers = new ArrayList<>();

    //list
    public int solve(int threshold, List<List<Integer>> productRatings) {

        //remove to avoid {4,4}
        int removed = removeEquals(productRatings);

        f(threshold, productRatings, removed, 0);

        List<Integer> sorted = numbers.stream()
                .sorted(Comparator.comparingInt(Integer::valueOf))
                .collect(Collectors.toList());

        //because we limit the deep of recursion
        int res = 1;
        if( !sorted.isEmpty())
            res = sorted.get(0);

        return res;
    }

    //arr
    public int fiveStarReviews(int[][] productRatings, int ratingsThreshold) {

        //convert array to list
        List<List<Integer>> ratings = new ArrayList<>();

        for (int[] arr : productRatings) {
            List<Integer> rating = new ArrayList<>();
            rating.add(arr[0]);
            rating.add(arr[1]);
            ratings.add(rating);
        }

        return solve(ratingsThreshold, ratings);
    }

    public void f(int threshold, List<List<Integer>> productRatings, int removed, int numberAdd) {

        //to avoid STACKOVERFLOW exception we limit the deep of recursion
        if (numberAdd > 99)
            return;

        //calculate rating
        double rate = 0;
        int amount = removed;
        for (int i =0; i< productRatings.size(); ++i) {
            List<Integer> rating = productRatings.get(i);
            rate += Double.valueOf(rating.get(0))/Double.valueOf(rating.get(1));
            amount++;
        }

        rate += removed;
        rate = rate / (double) amount;
        double thresholdD = threshold/100.0;

        //System.out.println("number:" + numberAdd + "; " + Arrays.toString(productRatings.toArray()));

        if(thresholdD <= rate) {
            numbers.add(numberAdd);
            return;
        }

        for (int i =0; i< productRatings.size(); ++i) {
            List<Integer> rating = productRatings.get(i);

            int r0 = rating.get(0)+1;
            int r1 = rating.get(1)+1;

            rating.set(0, r0);
            rating.set(1, r1);


            f(threshold, productRatings, removed, numberAdd+1);

            //back
            r0 = rating.get(0)-1;
            r1 = rating.get(1)-1;

            rating.set(0, r0);
            rating.set(1, r1);

        }

    }

    public int removeEquals(List<List<Integer>> productRatings) {

        int removed = 0;
        for (int i =0; i< productRatings.size(); ++i) {
            List<Integer> rating = productRatings.get(i);
            if(rating.get(0) == rating.get(1)) {
                productRatings.remove(i);
                removed++;
            }
        }

        return removed;
    }

    /* copy paste from leetcode
    //arr
    public int fiveStarReviews(int[][] products, int threshold) {

        int total = products.length;
        double curPercent = 0.0;
        double dThreshold = threshold / 100.0 * total;

        PriorityQueue<int[]> pq = new PriorityQueue<>((first, second) -> {
            int a = first[1], b = first[0];
            int c = second[1], d = second[0];
            // First element is b/a, second element is d/c
            // To build a max Heap we need (1 - d/c)(1 / (c + 1)) - (1 - b/a)(1 / (a + 1))
            // After transformation and we only need numerator, we get following equation
            return a * c * (a - c) - a * d * (d + 1) + b * c * (c + 1);
        });

        for (int[] p : products) {
            curPercent += ((double) p[0] / p[1]);
            if (p[1] > p[0]) pq.offer(p);
        }

        int ret = 0;
        while (curPercent < dThreshold) {
            int[] p = pq.poll();
            ret++;
            curPercent += (1 - (double)p[0] / p[1]) * (1.0 / (p[1] + 1));
            p[0]++;
            p[1]++;
            pq.offer(p);
        }
        return ret;
    }


    //list
    public int solve(int threshold, List<List<Integer>> productRatings) {

        int total = productRatings.size();
        double curPercent = 0.0;
        double dThreshold = threshold / 100.0 * total;
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>((first, second) -> {
            int a = first.get(1), b = first.get(0);
            int c = second.get(1), d = second.get(0);
            // First element is b/a, second element is d/c
            // To build a max Heap we need (1 - d/c)(1 / (c + 1)) - (1 - b/a)(1 / (a + 1))
            // After transformation and we only need numerator, we get following equation
            return a * c * (a - c) - a * d * (d + 1) + b * c * (c + 1);
        });

        for (List<Integer> p : productRatings) {
            curPercent += ((double) p.get(0) / p.get(1));
            if (p.get(1) > p.get(0)) {
                pq.offer(p);
            }
        }

        int ret = 0;
        while (curPercent < dThreshold) {
            List<Integer> p = pq.poll();
            ret++;
            curPercent += (1 - (double)p.get(0) / p.get(1)) * (1.0 / (p.get(1) + 1));

            //p[0]++;
            //p[1]++;

            int p0 = p.get(0);
            p0++;
            p.set(0, p0);
            int p1 = p.get(1);
            p1++;
            p.set(1, p1);

            pq.offer(p);
        }

        return ret;
    }
    */

}
