package com.google;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string S, we can split S into 2 strings: S1 and S2.
 * Return the number of ways S can be split such that the number of unique characters between S1 and S2 are the same.
 */
public class SplitStringUnique {

    public static void main(String[] args) {

        String input = "ababa";
        //Output: 2
        //Explanation: ab - aba, aba - ba

        SolutionSplitUnique sl = new SolutionSplitUnique();
        int n = sl.solve(input);

        System.out.println("# " + n);
    }

}

/**
 * O(n) time
 * O(n) memory
 */
class SolutionSplitUnique {

    public int solve(String input) {

        int nWays = 0;

        Map<Character, Integer> left = new HashMap<>();
        Map<Character, Integer> right = new HashMap<>();

        char[] arr = input.toCharArray();

        //initialize (Pointer is 0; Left is empty, Right contains all values)
        for (int i = 0; i < arr.length; ++i) {
            Character c = arr[i];

            Integer count = right.get(c);
            if (count != null) {
                right.put(c, ++count);
            } else {
                right.put(c, 1);
            }
        }

        //Move pointer from left to right.
        //reduce in left, add in right
        for (int i = 0; i < arr.length; ++i) {
            Character c = arr[i];

            //left+
            Integer lCount = left.get(c);
            if (lCount != null) {
                left.put(c, ++lCount);
            } else {
                left.put(c, 1);
            }

            //right-
            Integer rCount = right.get(c);
            if (rCount > 1) {
                right.put(c, --rCount);
            } else {
                right.remove(c);
            }

            int ls = left.size();
            int rs = right.size();

            if (ls == rs) {
                ++nWays;
            }

        }

        return nWays;
    }

}
