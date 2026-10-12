

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int MOD = 1_000_000_007;
        int[] bitCount = new int[30];

        // Count set bits
        for (int num : nums) {
            for (int bit = 0; bit < 30; bit++) {
                if ((num & (1 << bit)) != 0) {
                    bitCount[bit]++;
                }
            }
        }

        long ans = 0;

        // Construct k maximum numbers
        for (int i = 0; i < k; i++) {
            int value = 0;

            for (int bit = 0; bit < 30; bit++) {
                if (bitCount[bit] > 0) {
                    value |= (1 << bit);
                    bitCount[bit]--;
                }
            }

            ans = (ans + (long) value * value) % MOD;
        }

        return (int) ans;
    }
}
