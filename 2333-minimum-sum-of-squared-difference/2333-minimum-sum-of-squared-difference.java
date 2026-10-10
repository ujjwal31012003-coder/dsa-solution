class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
     long[] freq = new long[100001];
     long operations = (long)k1 + k2;
     int maxDiff = 0;
     for(int i = 0; i < nums1.length; i++) {
     
        int diff = Math.abs(nums1[i] - nums2[i]);
        freq[diff]++;
        maxDiff = Math.max(maxDiff,diff);
     }  
     for (int d = maxDiff; d > 0 && operations > 0; d--) {
        if (freq[d] == 0) {
            continue;
        }
        long count = freq[d];
        long use = Math.min(operations, count);

        freq[d] -= use;
        freq[d - 1] += use;
        operations -= use;
     } 

     if (operations > 0) {
        return 0;
     }
     long answer = 0;

       for(int d = 1; d <= maxDiff; d++) {
        answer += freq[d] * d * d;
       }
       return answer;
    }
}