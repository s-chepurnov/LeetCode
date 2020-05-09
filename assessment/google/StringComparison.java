package com.google;

import java.util.Arrays;
import java.util.Comparator;

//https://leetcode.com/discuss/interview-question/352458/
public class StringComparison {

    public static void main(String[] args) {

        String A = "abcd, aabc, bd";
        String B = "aaa, aa";

        SolutionStringComparison sl = new SolutionStringComparison();
        int[] result = sl.solve(A, B);
        System.out.println(Arrays.toString(result));
    }

}

class SolutionStringComparison {

    public int[] solve(String A, String B) {

        String[] a = A.split(", ");
        String[] b = B.split(", ");

        int[] result = new int[b.length];

        for (int i = 0; i < b.length; ++i) {
            int num = compareOneWord(b[i], a);
            result[i] = num;
        }

        return result;
    }

    public int compareOneWord(String word, String[] arr) {

        int counter = 0;
        for (int i = 0; i < arr.length; ++i) {

            StringComparator stringComparator = new StringComparator();
            int cmp = stringComparator.compare(word, arr[i]);
            if(cmp > 0) {
                ++counter;
            }
        }

        return counter;
    }
}

class StringComparator implements Comparator<String> {

    @Override
    public int compare(String s1, String s2) {

        int[] alphabetS1 = new int[26];
        int[] alphabetS2 = new int[26];

        //init dictionaries
        char[] arrS1 = s1.toCharArray();
        for (int i = 0; i < arrS1.length; ++i) {
            char curr = arrS1[i];
            alphabetS1[curr-'a'] = alphabetS1[curr-'a'] + 1;
        }

        char[] arrS2 = s2.toCharArray();
        for (int i = 0; i < arrS2.length; ++i) {
            char curr = arrS2[i];
            alphabetS2[curr-'a'] = alphabetS2[curr-'a'] + 1;
        }

        //find smallest element
        int smallest1 = 0;
        for (int i = 0; i < alphabetS1.length; ++i) {
            if (alphabetS1[i] > 0) {
                smallest1 = alphabetS1[i];
                break;
            }
        }

        int smallest2 = 0;
        for (int i = 0; i < alphabetS2.length; ++i) {
            if (alphabetS2[i] > 0) {
                smallest2 = alphabetS2[i];
                break;
            }
        }

        //compare
        if(smallest1 > smallest2)
            return 1;
        else if (smallest1 < smallest2)
            return -1;
        else
            return 0;
    }
}
