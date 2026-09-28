import java.util.*;
class Solution {
    public String frequencySort(String s) {
     int[] frequency = new int[256];

     for(int i = 0; i < s.length(); i++) {
        char ch = s.charAt(i);
        frequency[ch]++;
     }  
     StringBuilder answer = new StringBuilder();
     for(int count = s.length(); count >= 1; count--) {
        for(char ch = 0; ch < 256; ch++) {
            if(frequency[ch] == count) {
                for(int i = 0; i < frequency[ch]; i++) {
                    answer.append(ch);
                }
            }
        }
     } 
     return answer.toString();
    }
}