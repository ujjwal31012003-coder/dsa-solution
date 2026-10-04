class Solution {
    public boolean checkValidString(String s) {
    int minOpen = 0;
    int maxOpen = 0;
    for(char ch : s.toCharArray()){
        if (ch == '(') {
            minOpen++;
            maxOpen++;
        }
        else if (ch == ')') {
            minOpen--;
            maxOpen--;
        }
        else {
            minOpen--;
            maxOpen++;
        }
        minOpen = Math.max(minOpen,0);
        if (maxOpen < 0) {
            return false;
        }
        }
    return minOpen == 0;  
    }
}