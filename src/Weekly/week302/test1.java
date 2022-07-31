package Weekly.week302;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author LiuYunJie
 * @Date 2022/7/31 13:22
 **/
public class test1 {

    public int[] numberOfPairs(int[] nums) {
        int totalNum = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int num = 0;
        for (Integer value : map.values()) {
            num += value / 2;
        }
        return new int[]{num, totalNum - num * 2};
    }

}
