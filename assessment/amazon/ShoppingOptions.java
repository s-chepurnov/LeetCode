package com.amazon;

import java.util.ArrayList;
import java.util.List;

public class ShoppingOptions {

    public static void main(String[] args) {

        int[] priceOfJeans = {2, 3};
        int[] priceOfShoes = {4};
        int[] priceOfSkirts = {2, 3};
        int[] priceOfTops = {1, 2};
        int budgeted = 10;

        SolutionShoppingOptions solution = new SolutionShoppingOptions();
        int ways = solution.solve(priceOfJeans, priceOfShoes, priceOfSkirts, priceOfTops, budgeted);

        System.out.println("# " + ways);
    }
}

class SolutionShoppingOptions {

    public int solve(int[] priceOfJeans, int[] priceOfShoes, int[] priceOfSkirts, int[] priceOfTops, int budgeted) {

        if(budgeted <=0)
            return 0;
        if(priceOfJeans == null || priceOfShoes == null || priceOfSkirts == null || priceOfTops == null)
            return 0;
        if(priceOfJeans.length == 0 || priceOfShoes.length == 0 || priceOfSkirts.length == 0 || priceOfTops.length == 0)
            return 0;

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> items = new ArrayList();
        int sum = 0;
        for (int jeans : priceOfJeans) {
            for (int shoes: priceOfShoes) {
                for (int skirts : priceOfSkirts) {
                    for (int tops : priceOfTops) {
                        sum = jeans + shoes + skirts + tops;
                        if(sum <= budgeted) {
                            items.add(jeans);
                            items.add(shoes);
                            items.add(skirts);
                            items.add(tops);

                            result.add(items);
                        }

                        sum = 0;
                        items = new ArrayList();
                    }
                }
            }
        }


        int res = result.size();

        return res;
    }

}
