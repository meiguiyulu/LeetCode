package Weekly.week295;

import java.util.Stack;

/**
 * @Author LiuYunJie
 * @Date 2022/6/9 20:37
 **/
public class test3 {

    public static void main(String[] args) {

    }

    public int totalSteps(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int length = nums.length;
        int ans = 0;
        int[] dp = new int[length];
        for (int i = length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                dp[i] = Math.max(dp[i] + 1, dp[stack.pop()]);
                ans = Math.max(ans, dp[i]);
            }
            stack.push(i);
        }
        return ans;
    }

}
