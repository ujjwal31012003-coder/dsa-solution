class Solution {
    public int maxPalindromes(String s, int k) {
    int n = s.length();
    boolean[][] palindrome = new boolean[n][n];

    for (int i = n -1; i >= 0; i--) {
    palindrome[i][i] = true;
    for(int j = i + 1; j < n; j++) {
        palindrome[i][j] =  s.charAt(i) == s.charAt(j) && (j - i <= 2 || palindrome[i + 1][j - 1]);
    }    
    }
    int[] dp = new int[n + 1];

    for(int i = n - 1; i >= 0; i--) {
        dp[i] = dp[i + 1];

        for (int j = i; j < n; j++){
            if (palindrome[i][j] && j - i + 1 >= k) {
            dp[i] = Math.max(dp[i], 1 + dp[j + 1]);
        }
    } 
    }
return dp[0];
}
    }