package com.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parentheses {
    public static void main(String[] args) {

        int n = 3;
        //Expected:
//        [
//            "((()))",
//            "(()())",
//            "(())()",
//            "()(())",
//            "()()()"
//        ]

        SolutionParentheses sl = new SolutionParentheses();
        List<String> list = sl.generateParenthesis(n);

        System.out.println(Arrays.toString(list.toArray()));
    }
}

class SolutionParentheses {

    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }

    public void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current))
                result.add(new String(current));
        } else {
            current[pos] = '(';
            generateAll(current, pos+1, result);
            current[pos] = ')';
            generateAll(current, pos+1, result);
        }
    }

    public boolean valid(char[] current) {
        int balance = 0;
        for (char c: current) {
            if (c == '(') balance++;
            else balance--;
            if (balance < 0) return false;
        }
        return (balance == 0);
    }

}
