package com.leetcode;

class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String answer = solution.convertToTitle(1);
        System.out.println(answer);

    }

    public String convertToTitle(int n) {
        if(n < 0) return "";

        StringBuilder builder = new StringBuilder();

        while(n > 0) {

            n--; // because 'A' + r already start from 1
            int r = n%26;
            builder.append(String.valueOf((char)('A'+r)));

            n = n/26;
        }

        return builder.reverse().toString();
    }

}
