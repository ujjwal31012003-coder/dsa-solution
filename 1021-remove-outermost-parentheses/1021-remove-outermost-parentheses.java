class Solution {
    public String removeOuterParentheses(String s) {
      String result = "";
      int depth = 0;
      for(char c : s.toCharArray()) {
        if(c == '('){
            if(depth > 0){
                result += c;
            }
            depth++;
        }
        else{
            depth--;
            if(depth >0){
                result += c;
            }
        }
      }  
       return result; 
    }
}