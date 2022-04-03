package LeetcodeAgain;

import java.util.ArrayList;
import java.util.List;

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
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return s.substring(left + 1, right);
    }

    /**
     * 10. 正则表达式匹配
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2]; // *可以代表0个
                    if (matches(s, p, i, j - 1)) {
                        dp[i][j] = dp[i - 1][j] || dp[i][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[m][n];
    }

    private boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    /**
     * 22. 括号生成
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        dfsForGenerate(ans, new StringBuilder(), n, n);
        return ans;
    }

    private void dfsForGenerate(List<String> ans, StringBuilder builder, int left, int right) {
        if (left < 0 || right < left) {
            return;
        }
        if (left == 0 && right == 0) {
            ans.add(builder.toString());
        }
        if (left > 0) {
            builder.append("(");
            dfsForGenerate(ans, builder, left - 1, right);
            builder.deleteCharAt(builder.length() - 1);
        }
        if (right > left) {
            builder.append(")");
            dfsForGenerate(ans, builder, left, right - 1);
            builder.deleteCharAt(builder.length() - 1);
        }
    }

    /**
     * 45. 跳跃游戏 II
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int length = nums.length;
        int nextPosition = 0;
        int maxPosition = 0;
        int step = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == nextPosition) {
                step++;
                nextPosition = maxPosition;
            }
        }
        return step;
    }

    /**
     * 53. 最大子数组和
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int length = nums.length;
        int ans = nums[0];
        int[] dp = new int[length];
        dp[0] = nums[0];
        for (int i=1;i<length;i++) {
            dp[i] = Math.max(nums[i], nums[i] + dp[i-1]);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        DynamicProgramming programming = new DynamicProgramming();
//        System.out.println(programming.jump(new int[]{2, 3, 0, 1, 4}));
        System.out.println(programming.generateParenthesis(3));
    }
}
