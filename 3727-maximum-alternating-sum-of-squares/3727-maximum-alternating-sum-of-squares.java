
import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;

        // Square every number
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] * nums[i];
        }

        // Sort the squared values
        Arrays.sort(nums);

        long ans = 0;
        int mid = n / 2;

        // Subtract the smallest half
        for (int i = 0; i < mid; i++) {
            ans -= nums[i];
        }

        // Add the remaining, larger half
        for (int i = mid; i < n; i++) {
            ans += nums[i];
        }

        return ans;
    }
}
