package com.amazon;

public class NumberDiceRollsWithTargetSum {
    public static void main(String[] args) {

        int d = 1, f = 6, target = 3;
        //Output: 1

        SolutionNumberDiceRollsWithTargetSum sl = new SolutionNumberDiceRollsWithTargetSum();

        int n = sl.numRollsToTarget(d,f,target);

        System.out.println("# " + n);
    }
}

class SolutionNumberDiceRollsWithTargetSum {

    public int numRollsToTarget(int d, int f, int target) {

        int mod = 1_000_000_007;

        int[] dpCur = new int[target + 1], dpNext = new int[target + 1];

        for (int i = 1; i <= Math.min(f, target); i++) {
            dpCur[i] = 1;
        }

        for (int dd = 1; dd < d; dd++) {
            int sum = 0;
            for (int t = 1; t <= target; t++) {
                sum += dpCur[t - 1];
                if (t - f - 1 >= 0) {
                    sum-=dpCur[t - f - 1];
                    if (sum < 0)
                        sum += mod;
                }
                sum %= mod;
                dpNext[t] = sum;
            }
            dpCur = dpNext;
            dpNext = new int[target + 1];
        }

        return dpCur[target];
    }

}
