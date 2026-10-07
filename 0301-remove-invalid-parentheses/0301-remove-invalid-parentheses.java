class Solution {

    public List<String> removeInvalidParentheses(String s) {

        List<String> result = new ArrayList<>();

        remove(s, 0, 0, new char[]{'(', ')'}, result);

        return result;
    }

    private void remove(
            String s,
            int last_i,
            int last_j,
            char[] pair,
            List<String> result) {

        int balance = 0;

        for (int i = last_i; i < s.length(); i++) {

            if (s.charAt(i) == pair[0]) {
                balance++;
            }

            if (s.charAt(i) == pair[1]) {
                balance--;
            }

            // Too many closing brackets
            if (balance < 0) {

                for (int j = last_j; j <= i; j++) {

                    if (s.charAt(j) == pair[1] &&
                        (j == last_j || s.charAt(j - 1) != pair[1])) {

                        remove(
                            s.substring(0, j) + s.substring(j + 1),
                            i,
                            j,
                            pair,
                            result
                        );
                    }
                }

                return;
            }
        }

        // No extra ')' found.
        // Now check for extra '(' by reversing the string.
        String reversed = new StringBuilder(s).reverse().toString();

        if (pair[0] == '(') {

            remove(
                reversed,
                0,
                0,
                new char[]{')', '('},
                result
            );

        } else {

            result.add(reversed);
        }
    }
}