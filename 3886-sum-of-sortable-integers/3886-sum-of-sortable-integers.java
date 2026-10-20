
class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        int[] prefMax = new int[n + 1];
        int[] suffMin = new int[n + 1];
        int[] prefDrop = new int[n];

        // Prefix maximum
        for (int i = 0; i < n; i++) {
            prefMax[i + 1] = Math.max(prefMax[i], nums[i]);
        }

        // Suffix minimum
        suffMin[n] = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            suffMin[i] = Math.min(nums[i], suffMin[i + 1]);
        }

        // Count decreasing adjacent pairs
        for (int i = 1; i < n; i++) {
            prefDrop[i] = prefDrop[i - 1]
                    + (nums[i - 1] > nums[i] ? 1 : 0);
        }

        int answer = 0;

        // Check every divisor of n
        for (int k = 1; k <= n / k; k++) {
            if (n % k != 0) {
                continue;
            }

            if (isSortable(nums, k, prefMax, suffMin, prefDrop)) {
                answer += k;
            }

            int other = n / k;

            if (other != k &&
                isSortable(nums, other, prefMax, suffMin, prefDrop)) {
                answer += other;
            }
        }

        return answer;
    }

    private boolean isSortable(
            int[] nums, int k,
            int[] prefMax, int[] suffMin,
            int[] prefDrop) {

        int n = nums.length;

        for (int start = 0; start < n; start += k) {
            int end = start + k - 1;

            int drops = prefDrop[end] - prefDrop[start];

            // Check the circular connection from end to start
            if (nums[end] > nums[start]) {
                drops++;
            }

            // This block cannot be rotated into sorted order
            if (drops > 1) {
                return false;
            }

            // Check whether blocks can join in sorted order
            if (prefMax[start] > suffMin[start]) {
                return false;
            }
        }

        return true;
    }
}
