package LeetcodeAgain;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

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
     * 32. 最长有效括号
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int length = s.length();
        int ans = 0;
        int[] dp = new int[length];
        for (int i = 1; i < length; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    // ()型
                    dp[i] = (i - 2 > 0 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    // ))型
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public int longestValidParentheses2(String s) {
        // 栈
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    ans = Math.max(ans, i - stack.peek());
                }
            }
        }
        return ans;
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
        for (int i = 1; i < length; i++) {
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    /**
     * 55. 跳跃游戏
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int nextPosition = 0;
        int maxPosition = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > nextPosition) {
                return false;
            }
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == nextPosition) {
                nextPosition = maxPosition;
            }
        }
        return true;
    }

    /**
     * 62. 不同路径
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 63. 不同路径2
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rows = obstacleGrid.length, columns = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        int[][] dp = new int[rows][columns];
        dp[0][0] = 1;
        for (int i = 1; i < rows; i++) {
            if (obstacleGrid[i][0] != 1) {
                dp[i][0] = dp[i - 1][0];
            } else {
                dp[i][0] = 0;
            }
        }
        for (int j = 1; j < columns; j++) {
            if (obstacleGrid[0][j] != 1) {
                dp[0][j] = dp[0][j - 1];
            } else {
                dp[0][j] = 0;
            }
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                if (obstacleGrid[i][j] != 1) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return dp[rows - 1][columns - 1];
    }

    /**
     * 64. 最小路径和
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int[][] dp = new int[rows][cols];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < rows; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }
        for (int j = 1; j < cols; j++) {
            dp[0][j] = grid[0][j] + dp[0][j - 1];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[rows - 1][cols - 1];
    }

    /**
     * 70. 爬楼梯
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        /**
         * 动态规划
         *         int[] dp = new int[n + 1];
         *         dp[0] = 1;
         *         dp[1] = 1;
         *         for (int i = 2; i <= n; i++) {
         *             dp[i] = dp[i - 1] + dp[i - 2];
         *         }
         *         return dp[n];
         */

        /*下方代码超时*/
        if (n == 0 || n == 1) {
            return 1;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    /**
     * 72. 编辑距离
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        /*动态规划*/
        int length1 = word1.length(), length2 = word2.length();
        int[][] dp = new int[length1 + 1][length2 + 1];
        for (int i = 0; i <= length1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= length2; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                int temp = Math.min(dp[i - 1][j], dp[i][j - 1]);
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = Math.min(temp + 1, dp[i - 1][j - 1]);
                } else {
                    dp[i][j] = Math.min(temp, dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[length1][length2];
    }

    /**
     * 91. 解码方法
     *
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        int length = s.length();
        int[] dp = new int[length + 1];
        dp[0] = 1;
        for (int i = 1; i <= length; i++) {
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != '0' && (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[length];
    }

    /**
     * 96. 不同的二叉搜索树
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }

    /**
     * 95. 不同的二叉搜索树 II
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<TreeNode>();
        }
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> trees = new ArrayList<>();
        if (start > end) {
            trees.add(null);
            return trees;
        }
        for (int i = start; i <= end; i++) {
            // 左子树
            List<TreeNode> leftTrees = generateTrees(start, i - 1);
            // 右子树
            List<TreeNode> rightTrees = generateTrees(i + 1, end);

            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode treeNode = new TreeNode(i);
                    treeNode.left = left;
                    treeNode.right = right;
                    trees.add(treeNode);
                }
            }

        }
        return trees;
    }

    /**
     * 97. 交错字符串
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int length1 = s1.length(), length2 = s2.length(), length3 = s3.length();
        if (length1 + length2 != length3) {
            return false;
        }
        boolean[][] dp = new boolean[length1 + 1][length2 + 1];
        dp[0][0] = true;
        for (int i = 0; i <= length1; i++) {
            // s1的第i个元素
            for (int j = 0; j <= length2; j++) {
                // s2的第j个元素
                int curr = i + j;
                if (i > 0) {
                    dp[i][j] = dp[i][j] || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(curr - 1));
                }
                if (j > 0) {
                    dp[i][j] = dp[i][j] || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(curr - 1));
                }
            }
        }
        return dp[length1][length2];
    }

    /**
     * 118. 杨辉三角
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    list.add(1);
                } else {
                    list.add(ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));
                }
            }
            ans.add(list);
        }
        return ans;
    }

    /**
     * 119. 杨辉三角 II
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    list.add(1);
                } else {
                    list.add(ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));
                }
            }
            ans.add(list);
        }
        return ans.get(rowIndex);
    }

    /**
     * 120. 三角形最小路径和
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        int[][] dp = new int[size][size];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < size; i++) {
            List<Integer> list = triangle.get(i);
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + list.get(j);
                } else if (j == i) {
                    dp[i][j] = dp[i - 1][j - 1] + list.get(j);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + list.get(j);
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            ans = Math.min(ans, dp[size - 1][i]);
        }
        return ans;
    }

    /**
     * 121. 买卖股票的最佳时机
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int ans = 0, minValue = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minValue) {
                minValue = prices[i];
            } else {
                ans = Math.max(ans, prices[i] - minValue);
            }
        }
        return ans;
    }

    /**
     * 122. 买卖股票的最佳时机 II
     *
     * @param prices
     * @return
     */
    public int maxProfitII(int[] prices) {
        /**
         * 动态规划
         *         int[][] dp = new int[prices.length][2];
         *         dp[0][0] = 0;
         *         dp[0][1] = -prices[0];
         *         for (int i = 1; i < prices.length; i++) {
         *             dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
         *             dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
         *         }
         *         return dp[prices.length][0];
         */

        /* 贪心 */
        int ans = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                ans += prices[i + 1] - prices[i];
            }
        }
        return ans;
    }


    /**
     * 123. 买卖股票的最佳时机 III
     *
     * @param prices
     * @return
     */
    public int maxProfitIII(int[] prices) {
        int length = prices.length;
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;

        for (int i = 1; i < length; i++) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }

    /**
     * 124. 二叉树中的最大路径和
     *
     * @param root
     * @return
     */
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    private Integer maxGain(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftGain = Math.max(maxGain(root.left), 0);
        int rightGain = Math.max(maxGain(root.right), 0);

        int currPath = root.val + leftGain + rightGain;

        maxSum = Math.max(maxSum, currPath);

        return root.val + Math.max(leftGain, rightGain);
    }

    /**
     * 131. 分割回文串
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        List<String> list = new ArrayList<>();
        int length = s.length();
        boolean[][] dp = new boolean[length][length];
        for (int i = 0; i < length; i++) {
            Arrays.fill(dp[i], true);
        }

        for (int i = length - 1; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && dp[i + 1][j - 1];
            }
        }

        dfsForPartition(dp, s, ans, list, 0);
        return ans;

    }

    private void dfsForPartition(boolean[][] dp, String s, List<List<String>> ans, List<String> list, int curr) {
        if (curr == s.length()) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = curr; i < s.length(); i++) {
            if (dp[curr][i]) {
                list.add(s.substring(curr, i + 1));
                dfsForPartition(dp, s, ans, list, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * 132. 分割回文串 II
     *
     * @param s
     * @return
     */
    public int minCut(String s) {
        int length = s.length();
        boolean[][] dp = new boolean[length][length];
        for (int i = 0; i < length; i++) {
            Arrays.fill(dp[i], true);
        }

        for (int i = length - 1; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && dp[i + 1][j - 1];
            }
        }

        int[] ans = new int[length];
        Arrays.fill(ans, Integer.MAX_VALUE);
        for (int i = 0; i < length; i++) {
            if (dp[0][i]) {
                ans[i] = 0;
            } else {
                for (int j = 0; j < i; j++) {
                    if (dp[j + 1][i]) {
                        ans[i] = Math.min(ans[i], ans[j] + 1);
                    }
                }
            }
        }
        return ans[length - 1];
    }

    /**
     * 139. 单词拆分
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int length = s.length();
        boolean[] dp = new boolean[length + 1];
        dp[0] = true;
        for (int i = 1; i <= length; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[length];
    }

    /**
     * 198. 打家劫舍
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        /**
         *         int length = nums.length;
         *         if (length == 1) {
         *             return nums[0];
         *         }
         *         if (length == 2) {
         *             return Math.max(nums[0], nums[1]);
         *         }
         *         int[] dp = new int[length];
         *         dp[0] = nums[0];
         *         dp[1] = Math.max(nums[1], nums[0]);
         *         for (int i = 2; i < length; i++) {
         *             dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
         *         }
         *         return dp[length - 1];
         */
        /* 滚动数组优化 */
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int first = nums[0], second = Math.max(nums[1], nums[0]);
        for (int i = 2; i < length; i++) {
            int temp = second;
            second = Math.max(second, first + nums[i]);
            first = temp;
        }
        return second;
    }

    /**
     * 213. 打家劫舍 II
     *
     * @param nums
     * @return
     */
    public int robII(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        } else if (length == 2) {
            return Math.max(nums[0], nums[1]);
        } else {
            return Math.max(myRob(0, length - 2, nums), myRob(1, length - 1, nums));
        }
    }

    private int myRob(int start, int end, int[] nums) {
        int first = nums[start], second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int temp = second;
            second = Math.max(second, first + nums[i]);
            first = temp;
        }
        return second;
    }


    public static void main(String[] args) {
        DynamicProgramming programming = new DynamicProgramming();
/*        System.out.println(programming.isInterleave("", "abc", "abc"));
        for (int i = 1; i <= 5; i++) {
            System.out.println(programming.generate(i));
        }*/
        List<Integer> list1 = Arrays.asList(new Integer[]{-1});
        List<Integer> list2 = Arrays.asList(new Integer[]{2, 3});
        List<Integer> list3 = Arrays.asList(new Integer[]{1, -1, -3});
        List<List<Integer>> list = new ArrayList<>();
        list.add(list1);
        list.add(list2);
        list.add(list3);
        System.out.println(programming.minimumTotal(list));
    }
}
