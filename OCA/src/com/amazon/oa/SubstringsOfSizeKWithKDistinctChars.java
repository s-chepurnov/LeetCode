package com.amazon.oa;

import java.util.*;

public class SubstringsOfSizeKWithKDistinctChars {
    public static void main(String[] args) {

//        String s = "abcabc";
//        int k = 3;
        //Output: ["abc", "bca", "cab"]

        String s = "abacab";
        int k = 3;
        //Output: ["bac", "cab"]

        SolutionSubstringsOfSizeKWithKDistinctChars sl = new SolutionSubstringsOfSizeKWithKDistinctChars();
        List<String> output = sl.solve(s, k);
        System.out.println("# " + Arrays.toString(output.toArray()));
    }
}


class SolutionSubstringsOfSizeKWithKDistinctChars {

    public List<String> solve(String s, int k) {

        Set<Character> window = new HashSet<>();
        Set<String> result = new HashSet<>();
        for (int start = 0, end = 0; end < s.length(); end++) {
            for (; window.contains(s.charAt(end)); start++) {
                window.remove(s.charAt(start));
            }

            window.add(s.charAt(end));

            if (window.size() == k) {
                result.add(s.substring(start, end + 1));
                window.remove(s.charAt(start++));
            }
        }

        return new ArrayList<>(result);
    }
}