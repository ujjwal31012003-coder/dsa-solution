import java.util.*;
class Solution {
    public int[] findEvenNumbers(int[] digits) {
        
        Set<Integer> answer = new TreeSet<>();
        for (int i = 0; i < digits.length; i++){
            if(digits[i] == 0){
                continue;
            }
            for(int j = 0; j < digits.length; j ++){
                for(int k = 0; k < digits.length; k++){
                    if(i == j || k == j || k == i){
                        continue;
                    }
                    if(digits[k] % 2 != 0){
                        continue;
                    }
                    int number = digits[i] * 100 + digits[j] * 10 + digits[k];
                    answer.add(number);
                }
            }
        }
        int[] result = new int[answer.size()];
      int index = 0;
        for(int number : answer){
            result[index] = number;
            index++;
        }
        return result;
    }
}