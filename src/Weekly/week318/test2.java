package Weekly.week318;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author LiuYunJie
 * @Date 2022/11/13 14:52
 **/
public class test2 {

    /**
     * 2461. 长度为 K 子数组中的最大和
     */
    public int subarraySum(int[] nums, int k) {
        long ans = 0, temp = 0;
        int n = nums.length;
        Map<Integer, Integer> hashmap = new HashMap<>();
        for(int i = 0; i < k; i ++)
        {
            temp += nums[i];
            hashmap.put(nums[i], hashmap.getOrDefault(nums[i], 0) + 1);
        }
        if(hashmap.size() == k)
            ans = temp;
        for(int i = k; i < n; i ++)
        {
            temp -= nums[i - k];
            hashmap.put(nums[i - k], hashmap.get(nums[i - k]) - 1);

            if(hashmap.get(nums[i - k]) == 0)
                hashmap.remove(nums[i - k]);

            temp += nums[i];
            hashmap.put(nums[i], hashmap.getOrDefault(nums[i], 0) + 1);
            if(hashmap.size() == k)
                ans = Math.max(ans, temp);
        }
        return (int) ans;
    }
}
