class Solution {
    public int search(int[] nums, int target) {
        int left=0,right=nums.length-1;
        while(left<=right) {
            int mid=left+(right-left)/2;
            if(nums[mid]==target)
                return mid;
            if(nums[mid]>=nums[0]) {//The left side of mid is sorted
                if(target>=nums[0] && target<nums[mid]) {//Check if target is in the sorted range(left)
                    right=mid-1;
                }
                else
                    left=mid+1;
            }
            if(nums[mid]<=nums[nums.length-1]) {//the right side of mid is sorted
                if(target>nums[mid]&& target <=nums[nums.length-1]) {//check if the target is in the sorted range(right)
                    left=mid+1;
                }
                else
                    right=mid-1;
            }
        }
        return -1;
        
    }
}
