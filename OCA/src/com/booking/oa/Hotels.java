package com.booking.oa;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.groupingBy;

/*
You have rating (0-10) of the hotels per user in this format:

scores = [
    {'hotel_id': 1001, 'user_id': 501, 'score': 7},
    {'hotel_id': 1001, 'user_id': 502, 'score': 7},
    {'hotel_id': 1001, 'user_id': 503, 'score': 7},
    {'hotel_id': 2001, 'user_id': 504, 'score': 10},
    {'hotel_id': 3001, 'user_id': 505, 'score': 5},
    {'hotel_id': 2001, 'user_id': 506, 'score': 5}
]

Any given hotel might have more than one score.

Implement a function, get_hotels(scores, min_avg_score) that returns a
list of hotel ids that have average score equal to or higher than min_avg_score.

get_hotels(scores, 5) -> [1001, 2001, 3001]
get_hotels(scores, 7) -> [1001, 2001]
*/
public class Hotels {
    public static void main(String[] args) {
        Hotel[] scores = {
                new Hotel(1001, 501, 7),
                new Hotel(1001, 502, 7),
                new Hotel(1001, 503, 7),
                new Hotel(2001, 504, 10),
                new Hotel(3001, 505, 5),
                new Hotel(2001, 506, 5)
        };
        Double min_avg_score = 5.; //-> [1001, 2001, 3001]
        //Double min_avg_score = 7.; //-> [1001, 2001]

        List<Integer> output = get_hotels(scores, min_avg_score);
        System.out.println(output.toString());
    }

    public static List<Integer> get_hotels(Hotel[] scores, Double min_avg_score) {
        List<Hotel> hotels = Arrays.asList(scores);
        Map<Integer, Double> averages = hotels.stream()
                                              .collect(groupingBy(Hotel::getHotel_id, averagingInt(Hotel::getScore)));

        return averages
                .entrySet()
                .stream().filter(it -> it.getValue().compareTo(min_avg_score) >= 0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}

class Hotel {
    public int hotel_id;
    public int user_id;
    public int score;

    public Hotel(int hotel_id, int user_id, int score) {
        this.hotel_id = hotel_id;
        this.user_id = user_id;
        this.score = score;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

