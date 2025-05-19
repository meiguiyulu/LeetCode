package Hot100;

public class SubarraySum {

    /**
     * 和为 K 的子数组
     * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
     * 子数组是数组中元素的连续非空序列. O(n^2)的时间复杂度
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum  =0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public int subarraySum2(int[] nums, int k) {
    }

}
