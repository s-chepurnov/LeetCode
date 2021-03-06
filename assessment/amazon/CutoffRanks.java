package com.amazon;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CutoffRanks {
    public static void main(String[] args) {

        int cutOffRank = 4;
        int num=5;
        int[] scores = {2,2,3,4,5};
        //Output: 5


        CutoffRanksSolution solution = new CutoffRanksSolution();
        int n = solution.cutOffRank(cutOffRank, num, scores);

        System.out.println("# " + n);
    }
}

class CutoffRanksSolution {

    public int cutOffRank(int cutOffRank, int num, int[] scores) {

        /*
        List<Integer> scoresList = new ArrayList<Integer>(scores.length);
        for (int i : scores){
            scoresList.add(i);
        }
        */

        List<IndexScoreRank> list = new ArrayList<>();
        for (int i = 0; i < scores.length; ++i) {
            IndexScoreRank indexScoreRank = new IndexScoreRank(i, scores[i], 0);
            list.add(indexScoreRank);
        }

        //sort by score
        List<IndexScoreRank> sortedList = list.stream()
                .sorted( (o1,o2) -> {
                    return o1.score.compareTo(o2.rank);
                }).collect(Collectors.toList());

        //set rankes


        //count players that could level up their characters

        return 0;
    }

}

class IndexScoreRank {
    Integer index;
    Integer score;
    Integer rank;

    public IndexScoreRank(Integer index, Integer score, Integer rank) {
        this.index = index;
        this.score = score;
        this.rank = rank;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

}
