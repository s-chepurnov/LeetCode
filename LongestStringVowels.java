package com.amazon.oa;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * Given a string of lower charasters, remove at most two substrings of any length from the given string such that the remaining string contains vowels('a','e','i','o','u') only.
 *
 * Your aim is to maximise the length of the remaining string. Output the length of remaining string after removal of at most two substrings.
 *
 * NOTE: The answer may be 0, i.e. removing the entire string.
 * Example1:
 * Input:
 *  earthproblem
 * Output:
 *  2
 *
 * Example2:
 * Input:
 *  letsgosomewhere
 * Output:
 *  3
 *
 */
public class LongestStringVowels {

    public static void main(String[] args) {
        SolutionVowels sv = new SolutionVowels();

        int n = sv.solution("earthproblem");
        System.out.println("# " + n);
    }



}


class SolutionVowels {
    static Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    public int solution(String S) {
        int left = 0, right = S.length() - 1, res = 0;
        while (left < right) {
            if (vowels.contains(S.charAt(left))) {
                left++;
                res++;
            } else if (vowels.contains(S.charAt(right))) {
                right--;
                res++;
            } else break;
        }

        res += longestVowelSubstring(S, left, right);

        return res;
    }

    public int longestVowelSubstring(String S, int left, int right) {
        int res = 0, max = 0;
        for (int k = left + 1; k < right; k++) {
            if (vowels.contains(S.charAt(k))) {
                if (res == 0) res = 1;
                if (vowels.contains(S.charAt(k - 1))) {
                    res++;
                }
                max = Integer.max(max, res);
            } else {
                res = 0;
            }
        }
        return max;
    }
}
