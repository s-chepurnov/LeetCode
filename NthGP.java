package com.amazon.oa;

/**
 * Given 2nd and 3rd term of a Geometric Progression find the nth term of it and round it off upto 3 decimal places.
 */
public class NthGP {

    public static void main(String[] args) {
        int input1 = 1, input2 = 2, input3 = 4;

        SolutionNthGP sl = new SolutionNthGP();
        double result = sl.nthTerm(input1, input2, input3);
/* just info
        //char to double
        char four = '4';
        double d1 = (double) (four - '0');
        System.out.println(d1);

        //double to char
        double dten = 10d;
        char chten = (char) dten;
        System.out.println(chten); // nothing to print
        System.out.println(chten - '0');
*/

        System.out.println((char)result);


    }
}

class SolutionNthGP {

    public double nthTerm(double input1, double input2, int input3) {

        //input1 == b2
        //input2 == b3
        //input3 == n

        double q = input2/input1;
        double b1 = input1/q;

        // bn = b1 * q^n-1;
        double bn = b1 * (Math.pow(q, (input3 - 1)));


        // to char array

        return bn;
    }
}
