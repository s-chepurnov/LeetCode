package com.amazon;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

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
        int min = solution.solve(threshold, productRatings);

        System.out.println("# solve: " + min);

        int[][] productRatings2 = {{4,4}, {1,2}, {3,6}};
        int min2 = solution.fiveStarReviews(productRatings2, threshold);
        System.out.println("# fiveStarReviews: " + min2);

    }

}

class FiveStarSellersSolution {

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

}
