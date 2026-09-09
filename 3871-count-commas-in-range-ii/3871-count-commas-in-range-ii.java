class Solution {
    public long countCommas(long n) {
     long answer = 0;
     long start = 1000;
     long commas = 1;
     while(start <= n){
        long end = Math.min(n, start * 1000 -1);
        long count = end - start + 1;
        answer += count * commas;
        start = start * 1000;
        commas++;
     } 
     return answer;
    }
}