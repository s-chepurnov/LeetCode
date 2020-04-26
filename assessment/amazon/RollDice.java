package com.amazon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RollDice {

    public static void main(String[] args) {

        int[] a = {1, 2, 3};

        SolutionRollDice sl = new SolutionRollDice();
        int n = sl.solve(a);

        System.out.println("# " + n);
    }

}

class SolutionRollDice {

    public int solve(int[] dices) {
        List<Integer> list = new ArrayList<>();

        //turn dice into face i (1...6)
        int sides = 6;
        for (int face = 1; face <= sides; ++face) {

            //current dice
            int sum = 0;
            for (int j = 0; j < dices.length; ++j) {
                int moves = numOfMovesToTurnDiceToFace(dices[j], face);
                sum+=moves;
            }

            list.add(sum);
        }

        int min = list.stream()
                .min(Comparator.comparing(Integer::valueOf))
                .get();

        return min;
    }

    public int numOfMovesToTurnDiceToFace(int currentPipe, int requiredPipe) {
        int oppositePipe = 7 - currentPipe;

        if (currentPipe == requiredPipe)
            return 0;
        else if(requiredPipe == oppositePipe)
            return 2;
        else
            return 1;

    }
}
