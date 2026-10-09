class Solution {
    public int minInsertions(String s) {
      int insertions = 0;
      int balance = 0;
      for(int i = 0; i < s.length(); i++){
         char ch = s.charAt(i);

         if(ch == '('){
            balance += 2;
         
         if (balance % 2 != 0 ){
            insertions ++;
            balance --;
         }
         }
        
      else {
        balance --;
        if(balance < 0){
            insertions ++;
            balance = 1;
        }
      }
    }
    return insertions + balance;
}
}