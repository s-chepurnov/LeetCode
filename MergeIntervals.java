package com.amazon.oa;

import java.util.ArrayList;
import java.util.Arrays;

public class MergeIntervals {

    public static void main(String[] args) {

        //int[][] intervals= {{1,3},{2,6},{8,10},{15,18}};
        //Output: [[1,6],[8,10],[15,18]];

        int[][] intervals = {{1,4}, {4,5}};
        //Output: [[1,5]]
        //Explanation: Intervals [1,4] and [4,5] are considered overlapping.

        SolutionMergeIntervals sl = new SolutionMergeIntervals();
        int[][] result = sl.merge(intervals);

        //print
        for (int[] interval : result) {
            System.out.println(Arrays.toString(interval));
        }

    }

}

class SolutionMergeIntervals {

    public int[][] merge(int[][] intervals) {

        if (intervals.length <= 1)
            return intervals;
        // Sort by ascending starting point
        Arrays.sort(intervals,(a,b)->(a[0]-b[0]));
        ArrayList<int[]> list = new ArrayList<>();
        // add the first interval
        list.add(intervals[0]);
        //start from next interval
        for(int i=1;i<intervals.length;i++){
            //if the interval can be mergered update the end
            // example
            //[1,5] [4,9]
            //so the above can be mergered so compare the max of end and update
            //[1,9]
            if(list.get(list.size()-1)[1]>=intervals[i][0]){
                list.get(list.size()-1)[1] = Math.max( list.get(list.size()-1)[1],intervals[i][1]);
            }
            // else add the current interval to the result list
            else{
                list.add(intervals[i]);
            }
        }
        // convert the list to array
        int[][]result = new int[list.size()][2];
        for(int i=0;i<list.size();i++){
            result[i][0] = list.get(i)[0];
            result[i][1] = list.get(i)[1];

        }
        return result;

    }

}
