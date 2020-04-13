package com.amazon.oa;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SpiralMatrixII {

    public static void main(String[] args) {

        int input = 3;
//        Output:
//                [
//                 [ 1, 2, 3 ],
//                 [ 8, 9, 4 ],
//                 [ 7, 6, 5 ]
//                ]
//
        SolutionSpiralMatrixII sl = new SolutionSpiralMatrixII();
        int[][] matrix = sl.solve(input);

        //print
        for (int[] a : matrix) {
            System.out.println(Arrays.toString(a));
        }
    }
}

class SolutionSpiralMatrixII {

    public int[][] solve(int input) {

        int[][] squareMatrix = new int[input][input];

        int start = 1;
        int end = input*input;
        List<Integer> numbers = IntStream.rangeClosed(start, end)
                                         .boxed()
                                         .sorted(Comparator.reverseOrder())
                                         .collect(Collectors.toList());

        //calculate the quantity of 'spirals' (e.g.: n = 4, then 2 'spirals'; n = 5, then 3 'spirals')
        for (int offset = 0; offset < Math.ceil(0.5 * squareMatrix.length); ++offset) {
            matrixInClockwise(squareMatrix, offset, numbers);
        }

        return squareMatrix;
    }

    public void matrixInClockwise(int[][] squareMatrix, int offset, List<Integer> numbers) {

        if (offset == squareMatrix.length - offset - 1) { // 2 == 5 - 2 - 1
            // squareMatrix has odd dimension, and we are at its center.
            //System.out.println("center [" + offset + ", " + (squareMatrix.length - offset - 1) + "] = " + numbers.get(numbers.size()-1));
            squareMatrix[offset][squareMatrix.length - offset - 1] = numbers.get(numbers.size()-1);
            numbers.remove(numbers.size() - 1);
            return;
        }

        //up horizontal
        for (int j = offset; j < squareMatrix.length - offset - 1; ++j) {
            //System.out.println("[" + offset + "][" + j + "] = " + numbers.get(numbers.size()-1));
            squareMatrix[offset][j] = numbers.get(numbers.size() - 1);
            numbers.remove(numbers.size() - 1);
        }

        //right vertical
        for (int i = offset; i < squareMatrix.length - offset - 1; ++i) {
            //System.out.println("[" + i + "][" + (squareMatrix.length - offset - 1) + "] = " + numbers.get(numbers.size()-1));
            squareMatrix[i][squareMatrix.length - offset - 1] = numbers.get(numbers.size()-1);
            numbers.remove(numbers.size() - 1);
        }

        //bottom horizontal
        for (int j = squareMatrix.length - offset - 1 ; j > offset; --j) {
            //System.out.println("[" + (squareMatrix.length - offset - 1) + "][" + (j) + "] = " + numbers.get(numbers.size()-1));
            squareMatrix[squareMatrix.length - offset - 1][j] = numbers.get(numbers.size()-1);
            numbers.remove(numbers.size() - 1);
        }

        //left vertical
        for (int i = squareMatrix.length - offset - 1 ; i > offset; --i) {
            //System.out.println("[" + i + "][" + (squareMatrix.length - offset - 1) + "] = " + numbers.get(numbers.size()-1));
            squareMatrix[i][offset] = numbers.get(numbers.size()-1);
            numbers.remove(numbers.size() - 1);
        }

    }

}