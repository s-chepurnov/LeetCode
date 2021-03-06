package com.amazon;

//https://aonecode.com/amazon-online-assessment-utilization-checks
public class UtilizationChecks {

    public static void main(String[] args) {

        //int instances = 2;
        //int[] averageUtil = {25, 23, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 76, 80};
        //out: 2

        int instances=5;
        int[] averageUtil = {30,5,4,8,19,89};
        //out: 3

        UtilizationChecksSolution solution = new UtilizationChecksSolution();
        int count = solution.finalInstances(instances, averageUtil);

        System.out.println("# " + count);
    }

}

class UtilizationChecksSolution {

    public int finalInstances(int instances, int[] averageUtil) {

        for (int i = 0; i < averageUtil.length; ++i) {
            int avg = averageUtil[i];

            if (avg < 25) {

                if (instances == 1) {
                    //take no action

                } else if (instances > 1) {
                    double dint = Math.ceil((double)instances/2.0);
                    instances = (int) dint;
                    i = i + 10;
                }

            } else if (avg >= 25 && avg <= 60) {
                //take no action

            } else if (avg > 60) {
                // 2 * 10^8 - 200 000 000

                if (instances < 200000000) {
                    int tmp  = instances;

                    instances = instances * 2;
                    i=i+10;

                    if (instances < tmp) {
                        //overflow
                        instances = tmp;
                        i = i - 10;//back;
                    }

                }
            }

        }

        return instances;
    }
}
