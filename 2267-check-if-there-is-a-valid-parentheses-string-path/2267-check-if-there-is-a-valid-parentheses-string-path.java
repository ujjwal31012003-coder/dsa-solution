class Solution {
    int m;
     int n;
     Boolean[][][] memo;
    public boolean hasValidPath(char[][] grid) {
     
     m = grid.length;
     n = grid[0].length;
     if((m + n  - 1) % 2 != 0){
        return false;
     }  
     int balance;
     if(grid[0][0] == '(') {
        balance = 1;
     } else {
        balance = -1;
     }
     if (balance < 0) {
        return false;
     }
     memo = new Boolean[m][n][m + n];
     return dfs(0, 0, balance, grid);
    }
    private boolean dfs(int row, int col, int balance, char[][] grid) {
        if (balance < 0) {
            return false;
        }
          int remaining = (m - row - 1) + (n - col - 1);

        if (balance > remaining) {
            return false;
        }
        if (row == m - 1 && col == n -1) {
            return balance == 0;
        }
        if(memo[row][col][balance] != null) {
            return memo[row][col][balance];
        }
        boolean answer = false;
        if (row + 1 < m) {
            int nextBalance;
        

            if(grid[row + 1][col] == '(') {
             nextBalance = balance + 1;   
            } else {
                nextBalance = balance - 1;
            }
            answer = dfs(row + 1, col, nextBalance, grid);
        }
        if(!answer && col + 1 < n){
            int nextBalance;
            if (grid[row][col + 1] == '(') {
                nextBalance = balance + 1;

             } else 
                
            {
                nextBalance = balance - 1;
            }
            answer = dfs(row, col + 1, nextBalance,grid);
            }
            memo[row][col][balance] = answer;
            return answer;
            
        
        
    }
}