package com.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParenthesesEasy {
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

        SolutionParenthesesEasy sl = new SolutionParenthesesEasy();
        List<String> list = sl.generateParenthesis(n);

        System.out.println(Arrays.toString(list.toArray()));
    }
}

//O(n^2) time
//O(n^2) space
class SolutionParenthesesEasy {

    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList();
        dfs(0,0, "", combinations, n);
        return combinations;
    }

    public void dfs(int l, int r, String str, List<String> combinations, int n) {

        if(l == n && r == n)
            combinations.add(str);

        if(l<r)
            return;

        if(l<n)
            dfs(l+1,r,new String(str+"("), combinations, n);

        if(r<l)
            dfs(l,r+1,new String(str+")"), combinations, n);

        return;
    }


}
