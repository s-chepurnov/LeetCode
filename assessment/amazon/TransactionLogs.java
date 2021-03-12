package com.amazon;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class TransactionLogs {

    public static void main(String[] args) {

        String[] logs = {"88 99 200", "88 99 300", "99 32 100", "12 12 15"};
        int threshold = 2;

        SolutionTransactionLogs solution = new SolutionTransactionLogs();

        String[] arr = solution.processLogFile(logs, threshold);

        System.out.println("asc order: " + Arrays.toString(arr));
    }

}


class SolutionTransactionLogs {

    public String[] processLogFile(String[] logs, int threshold) {

        if (logs == null || logs.length == 0)
            return new String[0];


        Map<String, Integer> map = new HashMap<>();
        for (String transaction : logs) {

            String[] ids = transaction.split(" ");
            String sender = ids[0];
            String recipient = ids[1];

            Integer sndCount = map.get(sender);
            if (sndCount == null) {
                map.put(sender, 1);
            } else {
                map.put(sender, sndCount + 1);
            }

            //case : 12 12 15
            if ( !recipient.equals(sender)) {
                Integer rcpCount = map.get(recipient);
                if (rcpCount == null) {
                    map.put(recipient, 1);
                } else {
                    map.put(recipient, rcpCount + 1);
                }
            }

        }

        final List<String> res = map.entrySet().stream()
                .filter(entry -> entry.getValue() >= threshold)
                .sorted(Map.Entry.comparingByValue())
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());

        //convert to array
        int s = res.size();
        String[] answer = new String[s];
        for (int i = 0; i < s; ++i) {
            answer[i] = res.get(i);
        }


        return answer;

    }
}