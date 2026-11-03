class Solution {
    public boolean isPalindrome(int x) {
        int temp = x;
        int reverse = 0;
        while(x > 0){
            int digit =  x % 10;
            reverse =  reverse * 10 + digit;
            x = x/10;
        }
        if (reverse == temp){
            return true;
        
        }
        return false;
    }
    
}