package com.amazon;

import java.util.Arrays;

/**
 * Given a string s and an int k, return an int representing the number of substrings (not unique) of s
 * with exactly k distinct characters. If the given string doesn't have k distinct characters, return 0.
 */
public class SubstringsWithExactlyKDistinctChars {

    public static void main(String[] args) {
        String s = "pqpqs";
        int k = 2;
        //Output: 7

        SolutionSubstringsWithExactlyKDistinctChars sl = new SolutionSubstringsWithExactlyKDistinctChars();
        int n = sl.solve(s, k);
        System.out.println("# " + n);
    }

}

class SolutionSubstringsWithExactlyKDistinctChars {

    public int solve(String s, int k) {

        int result = 0;
        int cnt[] = new int[26];

        // Consider all substrings beginning with str[i]
        for (int i = 0; i < s.length(); ++i) {
            int distCount = 0;
            Arrays.fill(cnt, 0);

            // Consider all substrings between str[i..j]
            for (int j = i; j < s.length(); ++j) {
                // If this is a new character for this substring, increment distCount.
                if (cnt[s.charAt(j) - 'a'] == 0)
                    distCount++;

                // Increment count of current character
                cnt[s.charAt(j) - 'a']++;

                // If distinct character count becomes k, then increment result.
                if (distCount == k)
                    result++;
            }
        }

        return result;
    }
}
