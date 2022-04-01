package LeetcodeAgain;

public class DynamicProgramming {

    /**
     * 5. 最长回文子串
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            String curr1 = currLength(s, i, i);
            if (curr1.length() > builder.length()) {
                builder.delete(0, builder.length());
                builder.append(curr1);
            }
            if (i + 1 < s.length()) {
                String curr2 = currLength(s, i, i + 1);
                if (curr2.length() > builder.length()) {
                    builder.delete(0, builder.length());
                    builder.append(curr2);
                }
            }
        }
        return builder.toString();
    }

    private String currLength(String s, int prev, int next) {
        int left = prev, right = next;
        while (left >=0 && right<s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return s.substring(left+1, right);
    }
}
