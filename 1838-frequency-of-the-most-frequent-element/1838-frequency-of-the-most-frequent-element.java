import java.util.Arrays;
class Solution {
    public int maxFrequency(int[] nums, int k) {
    Arrays.sort(nums);

    int left = 0;
    long sum = 0;
    int answer = 1;
    for(int right = 0; right < nums.length; right++){
        sum += nums[right];
        int windowSize = right - left + 1;
        long cost = (long)nums[right] * windowSize - sum;
        while (cost > k){
            sum -= nums[left];
            left++;

            windowSize = right - left + 1;
            cost = (long) nums[right] * windowSize - sum;
        }
        answer = Math.max(answer,windowSize);
    }
    return answer;    
    }
}