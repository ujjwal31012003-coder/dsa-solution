class Solution {
    public int maxProfit(int[] prices) {
      int minimumPrice = prices[0];
      int bestProfit = 0;
      for(int i = 0; i < prices.length; i++){
        if(prices[i] < minimumPrice){
            minimumPrice = prices[i];
        }
        int currentProfit = prices[i] - minimumPrice;
        bestProfit = Math.max(currentProfit,bestProfit);
    
       
      }
      return bestProfit;  
    }
}