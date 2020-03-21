package org.algs4;

import java.util.*;
import java.util.stream.Collectors;

public class TopNBuzzwords {

    public static void main(String[] args) {

        int numToys = 6;
        int topToys = 2;
        int numQuotes = 6;
        String[] toys = {"elmo", "elsa", "legos", "drone", "tablet", "warcraft"};

        String[] quotes = {
                "Elmo is the hottest of the season! Elmo will be on every kid's wishlist!",
                "The new Elmo dolls are super high quality",
                "Expect the Elsa dolls to be very popular this year, Elsa!",
                "Elsa and Elmo are the toys I'll be buying for my kids, Elsa is good",
                "For parents of older kids, look into buying them a drone",
                "Warcraft is slowly rising in popularity ahead of the holiday season"
        };

        SolutionBuzzwords solution = new SolutionBuzzwords();
        List<String> result = solution.solve(numToys, topToys, toys, numQuotes, quotes);

        //System.out.println("result: " + Arrays.toString(result.toArray()));

    }
}

class SolutionBuzzwords {

    public List<String> solve(int numToys, int topToys, String[] toys, int numQuotes, String[] quotes) {
        List<String> toysList = Arrays.asList(toys).stream()
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());

        List<String> quotesList = Arrays.asList(quotes);

        //create dictionary
        HashMap<String, Integer> map = new HashMap<>();

        for(String quote : quotesList) {

            char[] arr = quote.toCharArray();
            for (int i = 0; i < arr.length; ++i) {
                if( !Character.isLetter(arr[i])) {
                    arr[i] = ' ';
                }
            }

            String line = new String(arr);
            String[] words = line.split(" ");

            for(String word : words) {

                if (map.containsKey(word)) {
                    Integer counter = map.get(word);
                    map.put(word, ++counter);
                } else {
                    map.put(word, 1);
                }

            }
        }

        //process dictionary
        Set<Map.Entry<String, Integer>> set = map.entrySet();
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(set);

        List<String> result = list.stream()
                .filter(i -> toysList.contains(i.getKey().toLowerCase()))
                .sorted((o1, o2) -> {return o2.getValue().compareTo(o1.getValue());})
                .map(i -> {return i.getKey();})
                .map(String::toLowerCase)
                .limit(topToys)
                .collect(Collectors.toList());

        return result;
    }
}
