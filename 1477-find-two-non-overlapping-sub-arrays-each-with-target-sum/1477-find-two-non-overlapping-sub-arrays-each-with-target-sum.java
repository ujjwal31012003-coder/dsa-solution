class Solution {
    public int minSumOfLengths(int[] arr, int target) {

        int n = arr.length;
        int INF = Integer.MAX_VALUE / 2;

        // best[i] = shortest subarray with sum target
        // ending before index i
        int[] best = new int[n];
        for (int i = 0; i < n; i++) {
            best[i] = INF;
        }

        int answer = INF;
        int sum = 0;
        int left = 0;
        int bestLength = INF;

        for (int right = 0; right < n; right++) {

            sum += arr[right];

            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            if (sum == target) {

                int length = right - left + 1;

                // Check if there was a previous non-overlapping subarray
                if (left > 0 && best[left - 1] != INF) {
                    answer = Math.min(answer,
                            length + best[left - 1]);
                }

                bestLength = Math.min(bestLength, length);
            }

            best[right] = bestLength;
        }

        return answer == INF ? -1 : answer;
    }
}