package leetcode;


import java.util.Random;

/**
 * 384. 打乱数组
 * <p>
 * 给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。
 * 实现 Solution class:
 * <p>
 * Solution(int[] nums) 使用整数数组 nums 初始化对象
 * int[] reset() 重设数组到它的初始状态并返回
 * int[] shuffle() 返回数组随机打乱后的结果
 */
public class Solution {
    private int arr[];
    private int length;
    Random random;

    public Solution(int[] nums) {
        length = nums.length;
        arr = nums;
        random = new Random();
    }

    public int[] reset() {
        return arr;
    }

    public int[] shuffle() {
        int[] ans = arr.clone();
        for (int i = 0; i < length; i++) {
            Swap(ans, i, i + random.nextInt(length - i));
        }
        return ans;
    }

    private void Swap(int[] ans, int i, int i1) {
        int temp = ans[i];
        ans[i] = ans[i1];
        ans[i1] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution(new int[]{1, 2, 3});
        System.out.println(solution.shuffle());
        System.out.println(solution.reset());
        System.out.println(solution.shuffle());
    }
}
