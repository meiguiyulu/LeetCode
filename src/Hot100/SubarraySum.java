package Hot100;

import java.util.HashMap;
import java.util.Map;

public class SubarraySum {

    /**
     * 和为 K 的子数组
     * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
     * 子数组是数组中元素的连续非空序列. O(n^2)的时间复杂度
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 前缀和 + 哈希表
     * 1.首先理解此处map是什么？key：不同前缀和的值，value：前缀和=key的个数
     * 2. 因为求的是 某个长前缀和 - 某个短前缀和 = k ，所以 某个长前缀和 - k = 某个短前缀和；
     * 所以我们边计算前缀和，边记录下map；
     */
    public int subarraySum2(int[] nums, int k) {
        // key：不同前缀和的值，value：前缀和=key的个数
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        int count = 0;
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

}
