package Weekly.week296;

/**
 * @Author LiuYunJie
 * @Date 2022/6/10 13:42
 **/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 极大极小游戏
 */
public class test1 {

    public int minMaxGame(int[] nums) {
        int length = nums.length;
        while (length > 1) {
            length /= 2;
            boolean flag = true; // true表示Min false表示Max
            for (int i = 0; i < length; i++) {
                if (flag) {
                    nums[i] = Math.min(nums[2 * i], nums[2 * i + 1]);
                } else {
                    nums[i] = Math.max(nums[2 * i], nums[2 * i + 1]);
                }
                flag = !flag;
            }
        }
        return nums[0];
    }

}
