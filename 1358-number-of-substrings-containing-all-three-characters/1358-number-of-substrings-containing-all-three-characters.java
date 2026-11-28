class Solution {
    public int numberOfSubstrings(String s) {
      int[] freq = new int[3];

      int left = 0;
      int count = 0;

      for(int right = 0; right < s.length(); right++){
        int index = s.charAt(right) - 'a';
        freq[index]++;

          while (freq[0] > 0 && freq[1] > 0 && freq[2] > 0) {

                count += s.length() - right;

                int leftIndex = s.charAt(left) - 'a';
                freq[leftIndex]--;

                left++;
      } 
      }
      return count; 
    }
}