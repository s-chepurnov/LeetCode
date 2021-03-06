package com.amazon;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MusicPairsDivisibleBy60 {

    public static void main(String[] args) {

        MusicPairsDivisibleBy60Solution solution = new MusicPairsDivisibleBy60Solution();
        int arr[] = {30, 20, 150, 100, 40};
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        int n = solution.solve(list);

        System.out.println("# " + n);
    }
}

class MusicPairsDivisibleBy60Solution {

    /* optimal O(n)
    public int solve(List<Integer> time) {

        int remainders[] = new int[60];
        int count = 0;

        for (int t: time) {

            if (t % 60 == 0) { // check if a%60==0 && b%60==0
                count += remainders[0];
            } else { // check if a%60+b%60==60
                count += remainders[60 - t % 60];
            }
            remainders[t % 60]++; // remember to update the remainders
        }

        return count;
    }
    */

    //brute force O(n^2)
    public int solve(List<Integer> time) {

        int count = 0, n = time.size();

        for (int i = 0; i < n; i++) {
            // j starts with i+1 so that i is always to the left of j
            // to avoid repetitive counting
            for (int j = i + 1; j < n; j++) {
                if ((time.get(i) + time.get(j)) % 60 == 0) {
                    count++;
                }
            }
        }

        return count;
    }

}
