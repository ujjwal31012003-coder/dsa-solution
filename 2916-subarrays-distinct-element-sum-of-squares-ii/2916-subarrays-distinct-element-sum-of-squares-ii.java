
class Solution {
    private static final long MOD = 1_000_000_007L;

    private int n;
    private long[] sum;
    private long[] sumSq;
    private long[] lazy;

    public int sumCounts(int[] nums) {
        n = nums.length;

        sum = new long[4 * n];
        sumSq = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long answer = 0;

        for (int r = 0; r < n; r++) {
            int left = last[nums[r]] + 1;

            update(1, 0, n - 1, left, r, 1);

            last[nums[r]] = r;

            // Sum of squared distinct counts
            // for all subarrays ending at r
            answer = (answer + sumSq[1]) % MOD;
        }

        return (int) answer;
    }

    private void update(int node, int start, int end,
                        int left, int right, long value) {

        if (left > end || right < start) {
            return;
        }

        if (left <= start && end <= right) {
            apply(node, start, end, value);
            return;
        }

        push(node, start, end);

        int mid = start + (end - start) / 2;

        update(node * 2, start, mid, left, right, value);
        update(node * 2 + 1, mid + 1, end,
               left, right, value);

        sum[node] =
            (sum[node * 2] + sum[node * 2 + 1]) % MOD;

        sumSq[node] =
            (sumSq[node * 2] + sumSq[node * 2 + 1]) % MOD;
    }

    private void apply(int node, int start, int end,
                       long value) {

        long length = end - start + 1L;

        sumSq[node] = (
            sumSq[node]
            + 2L * value % MOD * sum[node] % MOD
            + value * value % MOD * length % MOD
        ) % MOD;

        sum[node] =
            (sum[node] + value * length) % MOD;

        lazy[node] = (lazy[node] + value) % MOD;
    }

    private void push(int node, int start, int end) {
        if (lazy[node] == 0 || start == end) {
            return;
        }

        int mid = start + (end - start) / 2;

        apply(node * 2, start, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, end, lazy[node]);

        lazy[node] = 0;
    }
}
