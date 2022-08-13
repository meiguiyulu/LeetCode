package Weekly.week305;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author LiuYunJie
 * @Date 2022/8/13 15:38
 **/
public class test1 {

    public int arithmeticTriplets(int[] nums, int diff) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int ans = 0;
        for (int num : nums) {
            if (set.contains(num + diff) && set.contains(num + 2 * diff)) {
                ++ans;
            }
        }
        return ans;
    }

}
