package com.amazon.oa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Log File
 *
 * You have been given a task of recording some data 40M a log file.
 * In the log file, every line is a space-delimited list of strings.
 * All lines begin with an alphanumeric identifier.
 * There will be no lines consisting only of an identifier.
 * After the alphanumeric identifier a line will consist of either:
 * - a list of words using only lowercase English letters
 * - or list of only integers.
 *
 * You have to reorder the data such that all of the lines with words are at the top of the log file.
 * The lines with words are ordered lexicographically. ignoring the identifier except in the case of ties
 * In the case of ties (if there are two lines that are identical except for the identifier)
 * the identifier is used to order lexicographically. Alphanumeric should be sorted in ASCII order (numbers come before letters)
 * The identifiers most still be pan of the lines in the output Strings.
 * Lines with integers must be left in the original order they were in the file.
 * Write an algorithm to reorder the data in the log file, according to the rules above.
 *
 * Input
 * The input to the function/method consists of two argument
 * - logFileSize, an integer representing the number of log lines.
 * - logLines, a list of strings representing the log file.
 *
 * Output
 * Return a list of strings representing the reordered log file data.
 *
 * Note
 * Identifier consists of only lower case english character and numbers.
 *
 * Example
 *
 * Input
 * logFileSize = 5
 * logLines =
 *  [a1 9 2 3 1]
 *  [g1 act car]
 *  [zo4 4 7]
 *  [ab1 off key dog]
 *  [a8 act zoo]
 *
 * Output
 * [g1 act car]
 * [a8 act zoo]
 * [ab1 off key dog]
 * [zo4 4 7]
 * [a1 9 2 3 1]
 */
public class LogFile {
    public static void main(String[] args) {
        int logFileSize = 5;
        String lines[] = {  "a1 9 2 3 1",
                            "g1 act car",
                            "zo4 4 7",
                            "ab1 off key dog",
                            "a8 act zoo"  };


        List<String> input = Arrays.asList(lines);
        List<List<String>> output = solve(logFileSize, input);
        System.out.println(output.toString());
    }

    public static List<List<String>> solve(int logFileSize, List<String> input) {

        List<String> alphaSortedList = input.stream()
                .filter(f -> {
                    String arr[] = f.split(" ");
                    return (Character.isAlphabetic(arr[1].charAt(0)));
                })
                .sorted((s1,s2) -> {
                    String arr1[] = s1.split(" ");
                    int i1 = s1.indexOf(arr1[1]);
                    String alpha1 = s1.substring(i1);

                    String arr2[] = s2.split(" ");
                    int i2 = s2.indexOf(arr2[1]);
                    String alpha2 = s2.substring(i2);

                    return alpha1.compareTo(alpha2);
                })
                .collect(Collectors.toList());

        List<String> nums = input.stream()
                .filter(f -> {
                    String arr[] = f.split(" ");
                    return (!Character.isAlphabetic(arr[1].charAt(0)));
                })
                .collect(Collectors.toList());


        alphaSortedList.addAll(nums);             // List<String> list; // alpha + nums

        return alphaSortedList.stream().map(m-> { // List<List<String>> list; // alpha + nums
            List<String> list = new ArrayList<>();
            list.add(m);
            return list;
        }).collect(Collectors.toList());
    }
}

