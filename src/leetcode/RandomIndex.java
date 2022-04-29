package leetcode;

import java.util.*;

/**
 * 398. 随机数索引
 */
public class RandomIndex {

    Map<Integer, List<Integer>> map;
    Random random;

    public RandomIndex(int[] nums) {
        random = new Random();
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = map.getOrDefault(nums[i], new ArrayList<>());
            list.add(i);
            map.put(nums[i], list);
        }
    }

    public int pick(int target) {
        List<Integer> list = map.get(target);
        return list.get(random.nextInt(list.size()));
    }
}
