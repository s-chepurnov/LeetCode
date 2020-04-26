package com.amazon;

public class Search2DMatrixII {

    public static void main(String[] args) {

//        int[][] matrix = {
//                            {1,   4,  7, 11, 15},
//                            {2,   5,  8, 12, 19},
//                            {3,   6,  9, 16, 22},
//                            {10, 13, 14, 17, 24},
//                            {18, 21, 23, 26, 30}
//                         };
//        //int target = 5; // true;
//        int target = 20; // false;


//        int[][] matrix = { {-5} };
//        int target = -5; // true

        int[][] matrix = {{}};
        int target = 1; // true

        SolutionSearch2DMatrixII sl = new SolutionSearch2DMatrixII();
        boolean find = sl.searchMatrix(matrix, target);

        System.out.println("# " + find);

    }
}
/* a little bit better than brute force, but even better to use binary search */
class SolutionSearch2DMatrixII {

    public boolean searchMatrix(int[][] matrix, int target) {

        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;


        for (int i = 0; i < matrix.length; ++i) {

            int length = matrix[0].length;
            if (matrix[i][0] > target || matrix[i][length-1] < target)
                continue;

            for (int j = 0; j < length; ++j) {
                //System.out.println(i + " " +j);


                if (matrix[i][j] == target)
                    return true;
                else if (matrix[i][j] > target)
                    break;
            }
        }

        return false;
    }
}
