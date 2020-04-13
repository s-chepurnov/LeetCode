package com.amazon.oa;

import java.util.*;
import java.util.stream.Collectors;

public class FavoriteGenresII {

    public static void main(String[] args) {

        //Expected:
        //"David": ["Rock", "Techno"],
        //"Emma":  ["Pop"]
        Map<String, List<String>> userMap = new HashMap<>();
        userMap.put("David", Arrays.asList("song1", "song2", "song3", "song4", "song8"));
        userMap.put("Emma", Arrays.asList("song5", "song6", "song7"));

        Map<String, List<String>> genreMap = new HashMap<>();
        genreMap.put("Rock", Arrays.asList("song1", "song3"));
        genreMap.put("Dubstep", Arrays.asList("song7"));
        genreMap.put("Techno", Arrays.asList("song2", "song4"));
        genreMap.put("Pop", Arrays.asList("song5", "song6"));
        genreMap.put("Jazz", Arrays.asList("song8", "song9"));
/*

        Map<String, List<String>> userMap = new HashMap<>();
        userMap.put("David", Arrays.asList("song1", "song2"));
        userMap.put("Emma", Arrays.asList("song3", "song4"));

        Map<String, List<String>> genreMap = new HashMap<>();
*/
//        Expected:
//        Output: {
//            "David": [],
//            "Emma":  []
//        }

        SolutionFavoriteGenresII sl = new SolutionFavoriteGenresII();
        Map<String, List<String>> result = sl.solve(userMap, genreMap);

        //toString
        System.out.println("username and a list of favorite genres: ");
        result.entrySet().stream().forEach(x-> System.out.println(x.getKey() +" "+ x.getValue()));
    }
}

class SolutionFavoriteGenresII {

    public Map<String, List<String>> solve(Map<String, List<String>> userMap, Map<String, List<String>> genreMap) {

        //Denormalize Table for each user
        List<List<DenormilizeTable>> tables = userMap.entrySet().stream().map( i -> {
            final String username = i.getKey();
            List<String> songs = i.getValue();

            List<DenormilizeTable> row = songs.stream().map(song -> {
                String genre = findGenreBySong(song, genreMap);
                return new DenormilizeTable(username, song, genre, 1L);
            }).collect(Collectors.toList());

            return row;
        } ).collect(Collectors.toList());


        Map<String, List<String>> result = new HashMap<>();
        List<String> favoriteGenres = new ArrayList<>();

        for (List<DenormilizeTable> table : tables) {

            //Grouping by genre
            //<Genre, Count>
            Map<String, Long> map = table
                    .stream()
                    .collect(Collectors.groupingBy(DenormilizeTable::getGenre, Collectors.counting()));

            //find max(favorite) genre
            Long max = map.entrySet().stream()
                    .map(Map.Entry::getValue)
                    .max(Comparator.comparingLong(Long::valueOf))
                    .orElse(Long.MIN_VALUE);

            String username = table.stream().findFirst().get().username;

            //List of favorite genres (with max count)
            List<String> genres = map.entrySet()
                    .stream()
                    .filter(i->i.getValue().equals(max))
                    .map(Map.Entry::getKey)
                    .sorted(Comparator.naturalOrder())
                    .collect(Collectors.toList());

            result.put(username, genres);
        }


        return result;
    }

    public String findGenreBySong(String song, Map<String, List<String>> genreMap) {

        String genre = genreMap.entrySet().stream()
                .filter(i->i.getValue().contains(song))
                .map(Map.Entry::getKey)
                .findFirst().orElse("");

        return genre;
    }

}

class DenormilizeTable {
    String username;
    String song;
    String genre;
    Long count;

    public DenormilizeTable(String username, String song, String genre, Long count) {
        this.username = username;
        this.song = song;
        this.genre = genre;
        this.count = count;
    }

    public String getUsername() {
        return username;
    }

    public String getSong() {
        return song;
    }

    public String getGenre() {
        return genre;
    }

    public Long getCount() {
        return count;
    }

}
