    package com.amazon;

    import java.util.HashSet;
    import java.util.Objects;
    import java.util.Set;

    /**
     * Input: nums = [1, 5, 1, 5], target = 6
     * Output: 1
     * Explanation:
     * [1, 5] and [5, 1] are considered the same.
     */
    public class TwoSumUniquePairs {

        public static void main(String[] args) {
            /*
            int[] nums = {1, 1, 2, 45, 46, 46};
            int target = 47;
            //Output: 2

            int[] nums = {1, 5, 1, 5};
            int target = 6;
            //Output: 1

            int[] nums = {1, 1};
            int target = 2;
            //Output: 1
            */

            int[] nums = {1, 1, 2, 45, 46, 46};
            int target = 47;
            //Output: 2

            SolutionTwoSumUniquePairs sl = new SolutionTwoSumUniquePairs();
            int n = sl.solve(nums, target);
            System.out.println("# " + n);
        }
    }

    class SolutionTwoSumUniquePairs {

        public int solve(int[] nums, int target) {

            if(nums == null || nums.length == 0)
                return 0;

            Set<SimmetricPoint> set = new HashSet<>();

            for (int i = 0; i < nums.length; ++i) {
                for (int j = 0; j < nums.length; ++j) {

                    if (i == j) continue;

                    if (nums[i] + nums[j] == target) {
                        set.add(new SimmetricPoint(nums[i], nums[j]));
                    }

                }
            }

            return set.size();
        }
    }

    class SimmetricPoint {

        public int x;
        public int y;

        public SimmetricPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SimmetricPoint that = (SimmetricPoint) o;

            //simmetric version 1-5 == 5-1
            return (x == that.x && y == that.y) || (x == that.y && y == that.x);
        }

        @Override
        public int hashCode() {
            //simmetric version 1-5 == 5-1
            return Objects.hash(x, y) + Objects.hash(y, x);
        }

    }

