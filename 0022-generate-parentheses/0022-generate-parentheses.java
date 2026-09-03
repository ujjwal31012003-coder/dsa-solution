class Solution {
    public List<String> generateParenthesis(int n) {
     List<String> result = new ArrayList<>();
     solve(result, "",0,0,n);
     return result;   
    }
    public void solve(List<String> result, String str, int open, int close, int n ) {
      if (str.length() == 2*n) {
        result.add(str);
        return;
      }  
      if (open < n){
        solve(result,str +"(",open +1,close,n);
      }
      if(close<open){
        solve(result,str + ")",open,close+1,n);
      }
    }
}