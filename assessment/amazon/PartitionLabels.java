package com.amazon;
import java.util.*;


/**
 * Greedy approach.
 *
 * We need an array last[char] -> index of S where char occurs last.
 * Then, let anchor and j be the start and end of the current partition.
 * If we are at a label that occurs last at some index after j, we'll extend the partition j = last[c].
 * If we are at the end of the partition (i == j) then we'll append a partition size to our answer,
 * and set the start of our new partition to i+1.
 */

public class PartitionLabels {
    public static void main(String[] args) {

        String s = "ababcbacadefegdehijhklij";
        //Output: [9,7,8]

        SolutionPartitionLabels sl = new SolutionPartitionLabels();
        List<Integer> partitions = sl.partitionLabels(s);

        System.out.println(Arrays.toString(partitions.toArray()));
    }
}

class SolutionPartitionLabels {

    public List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        for (int i = 0; i < s.length(); ++i)
            last[s.charAt(i) - 'a'] = i;

        int j = 0, anchor = 0;
        List<Integer> ans = new ArrayList();
        for (int i = 0; i < s.length(); ++i) {
            j = Math.max(j, last[s.charAt(i) - 'a']);
            if (i == j) {
                ans.add(i - anchor + 1);
                anchor = i + 1;
            }
        }
        return ans;
    }

}