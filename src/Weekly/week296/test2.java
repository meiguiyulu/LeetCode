package Weekly.week296;

/**
 * @Author LiuYunJie
 * @Date 2022/6/10 19:33
 **/

import java.util.Arrays;

/**
 * 2294. 划分数组使最大差为 K
 * 给你一个整数数组 nums 和一个整数 k 。你可以将 nums 划分成一个或多个 子序列 ，
 * 使 nums 中的每个元素都 恰好 出现在一个子序列中。
 * 在满足每个子序列中最大值和最小值之间的差值最多为 k 的前提下，返回需要划分的 最少 子序列数目。
 * 子序列 本质是一个序列，可以通过删除另一个序列中的某些元素（或者不删除）但不改变剩下元素的顺序得到。
 */
public class test2 {

    public int partitionArray(int[] nums, int k) {
        int ans = 1, length = nums.length;
        Arrays.sort(nums);
        int minValue = nums[0];
        for (int i = 0; i < length; i++) {
            if (nums[i] - minValue > k) {
                minValue = nums[i];
                ans++;
            }
        }
        return ans;
    }

}
