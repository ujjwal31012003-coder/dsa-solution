class Solution {
    public int reversePairs(int[] nums) {
      return mergeSort(nums, 0, nums.length - 1);

    }
    private int mergeSort(int[] nums , int left, int right) {
        if(left >= right) {
            return 0;
            }
            int mid = (left + right) / 2;
            int count =0;

            count += mergeSort(nums, left, mid);
            count += mergeSort(nums, mid + 1, right);


            int j = mid + 1;
            for(int i = left; i <= mid; i++){
                while(j <= right && nums[i] > 2L * nums[j]) {
                    j++;
                }
                count += j - (mid + 1);
            }
            int [] temp = new int[right - left + 1];
            int i = left;
            j = mid + 1;
            int k = 0;

            while(i <= mid && j <= right) {
                if(nums[i] <= nums[j]) 
                {
                    temp[k] = nums[i];
                    i++;

                }else{
                    temp[k] = nums[j];
                    j++;
                }
                k++;
            }
            while (i <= mid) {
                temp[k] = nums[i];
                i++;
                k++;
            }
            while (i <= mid){
                temp[k] = nums[i];
                i++;
                k++;
            }
            while(j <= right){
                temp[k] = nums[j];
                j++;
                k++;
            }
            for(int x =0; x < temp.length; x++){
                nums[left + x] = temp[x];
            }
    return count;
    }
}