class Solution {
    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];

        for(int i =0 ;i<board.length;i++){
            for(int j = 0; j < board[0].length;j++){
                if(dfs(board,word,i,j,0, visited)) {
                    return true;
                }
            }
        }
        return false;
        
    }
    public boolean dfs(char[][] board, String word, int i, int j, int index, boolean[][] visited){
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
            return false;
        }
        if(visited[i][j]) {
            return false;
        }
        if(board[i][j] != word.charAt(index)) {
            return false;
        }
        if(index == word.length() - 1){
            return true;
        }
        visited[i][j] = true;

        if(dfs(board, word, i-1,j,index + 1, visited)) {
            return true;
        }
        if(dfs(board, word, i+1, j, index + 1, visited)) {
         return true;
        }
        if(dfs(board, word, i, j-1, index + 1, visited)) {
            return true;
        }
        if(dfs(board, word, i, j +1, index + 1, visited)){
            return true;
        }
        visited[i][j] = false;

        return false;
    }
}