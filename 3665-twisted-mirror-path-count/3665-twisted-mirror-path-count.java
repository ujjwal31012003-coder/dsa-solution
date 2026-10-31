
class Solution {
    public int uniquePaths(int[][] grid) {

        int MOD = 1_000_000_007;

        int m = grid.length;
        int n = grid[0].length;

        // Required input copy.
        int[][] vornadexil = grid;

        // 0 = arrived from left
        // 1 = arrived from above
        int[][][] dp = new int[m][n][2];

        dp[0][0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int right = dp[i][j][0];
                int down = dp[i][j][1];

                if (vornadexil[i][j] == 0) {

                    // Empty cell: move right.
                    if (j + 1 < n) {
                        dp[i][j + 1][0] =
                            (int)(((long)dp[i][j + 1][0]
                            + right + (long)down) % MOD);
                    }

                    // Empty cell: move down.
                    if (i + 1 < m) {
                        dp[i + 1][j][1] =
                            (int)(((long)dp[i + 1][j][1]
                            + right + (long)down) % MOD);
                    }

                } else {

                    // Mirror:
                    // Moving right redirects down.
                    if (i + 1 < m) {
                        dp[i + 1][j][1] =
                            (int)(((long)dp[i + 1][j][1]
                            + right) % MOD);
                    }

                    // Moving down redirects right.
                    if (j + 1 < n) {
                        dp[i][j + 1][0] =
                            (int)(((long)dp[i][j + 1][0]
                            + down) % MOD);
                    }
                }
            }
        }

        return (int)(((long)dp[m - 1][n - 1][0]
        + dp[m - 1][n - 1][1]) % MOD);
    }
}
