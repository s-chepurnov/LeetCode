package com.amazon;

import java.util.*;
import java.util.stream.Collectors;

public class FavoriteGenres {

    public static void main(String[] args) {
/*
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
*/
        Map<String, List<String>> userMap = new HashMap<>();
        userMap.put("David", Arrays.asList("song1", "song2"));
        userMap.put("Emma", Arrays.asList("song3", "song4"));

        Map<String, List<String>> genreMap = new HashMap<>();

//        Expected:
//        Output: {
//            "David": [],
//            "Emma":  []
//        }

        SolutionFavoriteGenres sl = new SolutionFavoriteGenres();
        Map<String, List<String>> result = sl.solve(userMap, genreMap);

        //toString
        System.out.println("username and a list of favorite genres: ");
        result.entrySet().stream().forEach(x-> System.out.println(x.getKey() +" "+ x.getValue()));
    }

}

class SolutionFavoriteGenres {

    public Map<String, List<String>> solve(Map<String, List<String>> userMap, Map<String, List<String>> genreMap) {

        List<List<Glue>> allUsersGlue = userMap.entrySet()
                .stream()
                .map(user -> {

                    if (user.getValue() == null)
                        return new ArrayList<Glue>();

                    List<Glue> oneUserGlue = user.getValue().stream()
                                               .map( song -> {
                                                  String genre = findGenre(song, genreMap);
                                                  return new Glue(user.getKey(), genre, 1L);
                                               }).collect(Collectors.toList());

                    return oneUserGlue;
                }).collect(Collectors.toList());

        //[
        //  [David song1 Rock 1, David song2 Techno 1, David song3 Rock 1, David song4 Techno 1, David song8 Jazz 1],
        //  [Emma song5 Pop 1, Emma song6 Pop 1, Emma song7 Dubstep 1]
        //]

        //Group by genre
        List<List<Glue>> groupByGenre = allUsersGlue.stream()
                .filter(Objects::nonNull)
                .filter(i-> !i.isEmpty())
                .map(
                        userList -> {
                            //<Genre,count>
                            Map<String, Long> genreCount = userList.stream()
                                                                   .collect(Collectors.groupingBy(Glue::getGenre, Collectors.counting()));

                            //get username
                            String username = userList.stream().findFirst().get().user;

                            //David Rock 1, David Pop 10, David Techno 2
                            List<Glue> list = genreCount.entrySet().stream()
                                                        .map(x-> new Glue(username, x.getKey(), x.getValue()))
                                                        .collect(Collectors.toList());

                            return list;
                        }
                ).collect(Collectors.toList());

        // [
        //  [David Rock 1, David Pop 10, David Techno 2],
        //  [Emma Rock 3, Emma Pop 8, Emma Techno 4]
        // ]


        Map<String, List<String>> result = new HashMap<>();

        for (List<Glue> userList : groupByGenre) {
            //find max
            Glue maxGlue = userList.stream().max(Comparator.comparing(o -> o.count)).get();
            long max = maxGlue.count;

            //list only favorite genres
            List<String> maxUserList = userList.stream()
                    .filter(glue-> glue.count == max)
                    .map(glue->glue.genre)
                    .sorted(Comparator.naturalOrder())
                    .collect(Collectors.toList());

            String username = userList.stream().findFirst().get().user;
            result.put(username, maxUserList);
        }

        return result;
    }

    public String findGenre(String song, Map<String, List<String>> genreMap) {

        String genre = genreMap.entrySet()
                .stream()
                .filter(Objects::nonNull)
                .map(x-> {
                            if(x.getValue() == null)
                                return "";

                            boolean contains = x.getValue().contains(song);

                            if (contains)
                                return x.getKey();
                            else
                                return "";
                         })
                .filter(x -> x != null && !x.isEmpty())
                .findFirst().orElse("");

        return genre;
    }
}


class Glue {

    public String user;
    public String genre;
    public Long count;

    public Glue(String user, String genre, Long count) {
        this.user = user;
        this.genre = genre;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Glue glue = (Glue) o;
        return Objects.equals(user, glue.user) &&
                Objects.equals(genre, glue.genre) &&
                Objects.equals(count, glue.count);
    }

    @Override
    public int hashCode() {

        return Objects.hash(user, genre, count);
    }

    @Override
    public String toString() {
        return user + " " + genre + " " + count;
    }

    public String getUser() {
        return user;
    }

    public String getGenre() {
        return genre;
    }

}
