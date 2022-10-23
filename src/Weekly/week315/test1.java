package Weekly.week315;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author LiuYunJie
 * @Date 2022/10/23 14:36
 **/
public class test1 {

    /**
     * 2441. 与对应负数同时存在的最大正整数
     *
     * @param nums
     * @return
     */
    public int findMaxK(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int ans = -1;
        for (int num : nums) {
            if (set.contains(-num)) {
                ans = Math.max(ans, Math.abs(num));
            }
            set.add(num);
        }
        return ans;
    }
}
