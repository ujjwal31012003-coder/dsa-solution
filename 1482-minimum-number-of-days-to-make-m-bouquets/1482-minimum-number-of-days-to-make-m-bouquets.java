class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
           if ((long) m * k > bloomDay.length) {
            return -1;
        }

        // Find minimum and maximum blooming day
        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;

        for (int day : bloomDay) {
            left = Math.min(left, day);
            right = Math.max(right, day);
        }

        // Binary search
        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (canMake(bloomDay, mid, m, k)) {

                // mid works, try an earlier day
                right = mid - 1;

            } else {

                // mid doesn't work, need more days
                left = mid + 1;
            }
        }

        return left;
    }
 private boolean canMake(int[] bloomDay, int day, int m, int k) {
   int  flowers = 0;
   int bouquets = 0;
   for (int i = 0; i < bloomDay.length; i++){


    if(bloomDay[i] <= day){
        flowers++;

        if(flowers == k){
            bouquets ++;
            flowers = 0;
        }
    }
        else{
            flowers =0;
        }
    }
   return bouquets >= m;  
    }
}
