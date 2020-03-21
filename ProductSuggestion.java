package org.algs4;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given an array of strings products and a string searchWord. We want to design a system that suggests at most three
 * product names from products after each character of searchWord is typed.
 * Suggested products should have common prefix with the searchWord.
 *
 * If there are more than three products with a common prefix return the three lexicographically minimums products.
 *
 * Return list of lists of the suggested products after each character of searchWord is typed.
 */
public class ProductSuggestion {

    public static void main(String[] args) {
        String[] products = {"mobile","mouse","moneypot","monitor","mousepad"};
        String searchWord = "mouse";

        List<String> list = Arrays.asList(products);

        List<List<String>> result = solve(list, searchWord);

        System.out.println(Arrays.toString(result.toArray()));
    }

    public static List<List<String>> solve(List<String> products, String searchWord) {

        List<List<String>> result = new ArrayList<>();

        for (int i = 1; i <= searchWord.length(); ++i) {

            final int index = i;
            String entered = searchWord.substring(0, index);
            List<String> beginWith = products.stream()
                                                    .filter(word -> word.length() >= index &&
                                                            word.substring(0, index).equals(entered))
                                                    .collect(Collectors.toList());


            int limit = 3;

            beginWith = beginWith.stream()
                        .sorted((s1, s2) -> s1.compareTo(s2))
                        .limit(limit)
                        .collect(Collectors.toList());


            result.add(beginWith);

        }

        return result;
    }
}

