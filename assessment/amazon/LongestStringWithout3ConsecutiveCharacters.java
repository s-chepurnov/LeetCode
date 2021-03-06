package com.amazon;

import java.util.*;

public class LongestStringWithout3ConsecutiveCharacters {

    public static void main(String[] args) {
        //int A = 1, B = 1, C = 6;
        //Output: "ccbccacc"

        //int  A = 1, B = 2, C = 3;
        //Output: "acbcbc"

        int A = 5, B = 2, C = 0;
        //Output: 7 characters

        SolutionLongestStringWithout3ConsecutiveCharacters sl = new SolutionLongestStringWithout3ConsecutiveCharacters();
        String result = sl.solve(A,B,C);

        System.out.println(result);
        System.out.println(result.length());
    }

}

class SolutionLongestStringWithout3ConsecutiveCharacters {

    public String solve(int a, int b, int c) {

        Map<Character, Integer> map = new HashMap<>();
        map.put('a', a);
        map.put('b', b);
        map.put('c', c);
        return findString(map);
    }

    public String findString(Map<Character, Integer> map){

        //We store a heap of (count, letter).
        //We pop the top two elements from the heap (representing different letters with positive remaining count),
        // and then write the most frequent one that isn't the same as the most recent one written.

        //last characters
        Queue<NodeLetter> pq = new PriorityQueue<>((a, b) -> b.val - a.val);

        for (Character l : map.keySet()) {
            if (map.get(l) != 0) {
                NodeLetter n = new NodeLetter(map.get(l) ,l);
                pq.offer(n);
            }
        }

        StringBuilder res = new StringBuilder();
        while (!pq.isEmpty()) {
            NodeLetter first = pq.poll();

            // System.out.println(res.toString());
            //if the last character is equal to character that we want to put
            if (res.length() != 0 && res.charAt(res.length() - 1) == first.letter) {

                if (pq.isEmpty()) {
                    return res.toString();
                } else {
                    //take second character from pq because 'first' equals to last character tat we used
                    NodeLetter second = pq.poll();
                    res.append(second.letter);
                    second.val--;

                    if (second.val != 0) {
                        pq.offer(second);
                    }
                    pq.offer(first);
                }

            } else {
                int limit = Math.min(2, first.val); //available number of letter

                for (int i = 0; i < limit; i++) {
                    res.append(first.letter);
                    first.val--;
                }

                if(first.val != 0) {
                    pq.offer(first);
                }
            }
        }

        return res.toString();
    }

}

class NodeLetter {

    int val;
    char letter;

    public NodeLetter(int val, char c) {
        this.val = val;
        this.letter = c;
    }

}
