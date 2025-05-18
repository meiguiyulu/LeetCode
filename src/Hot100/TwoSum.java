package Hot100;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    /**
     * 两数之和
     * 使用哈希表空间换时间的思想
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[0];
    }

}
