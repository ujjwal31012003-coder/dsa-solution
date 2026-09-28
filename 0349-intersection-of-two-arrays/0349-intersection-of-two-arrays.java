class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
     Set<Integer> set = new HashSet<>();
     
     for(int num : nums1) {
       
            set.add(num);
     }
            Set<Integer> answer = new HashSet<>();
            for(int num : nums2){
           if(set.contains(num)) {
            answer.add(num);
           }
        }
        int[] result = new int[answer.size()];
        int i = 0;
        for(int num : answer) {
            result[i] = num;
            i++;
        }
     return result;
     
    }
}