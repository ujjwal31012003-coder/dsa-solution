class Solution {
    public int maxSubArray(int[] nums) {
     int bestSum= nums[0];
     int currentSum = nums[0];

     for(int i =  1; i < nums.length;i++){
        currentSum = Math.max(currentSum + nums[i] , nums[i]);
        bestSum = Math.max(currentSum,bestSum);
         
     }  
     return bestSum; 
    }
}