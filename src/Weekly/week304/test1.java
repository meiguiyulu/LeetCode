package Weekly.week304;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author LiuYunJie
 * @Date 2022/8/6 14:03
 **/
public class test1 {

    /**
     * 2357. 使数组中所有元素都等于零
     *
     * @param nums
     * @return
     */
    public int minimumOperations(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (num > 0 && !set.contains(num)) {
                set.add(num);
            }
        }
        return set.size();
    }

}
