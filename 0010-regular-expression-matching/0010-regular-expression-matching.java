class Solution {
    public boolean solve(String s, String p, int i, int j) {
       if (j == p.length()){
        return i == s.length();
       }
       boolean firstMatch = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

       if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
        return solve (s, p, i, j + 2) || (firstMatch && solve(s, p, i + 1, j));
       }
       return firstMatch && solve(s, p, i + 1, j + 1);
    }
    public boolean isMatch(String s, String p) {
    return solve(s, p, 0, 0);
}
}